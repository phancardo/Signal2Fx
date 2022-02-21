package com.projetCloud.projetCloudRESTWS.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class CustomJwtFilter extends OncePerRequestFilter {

    private Collection<String> excludedUrls = new ArrayList<String>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/api/v1/login") || request.getServletPath().equals("/api/v1/users/register") || request.getServletPath().equals("/test")){
            filterChain.doFilter(request,response);
        }else{
            String authorization = request.getHeader(AUTHORIZATION);
            String bearer = "Bearer ";
            try{
                if(authorization != null && authorization.startsWith(bearer)){
                    String token = authorization.substring(bearer.length());
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                    JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = jwtVerifier.verify(token);
                    String email = decodedJWT.getSubject();
                    String[]roles = decodedJWT.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
                    stream(roles).forEach(role -> {
                        authorities.add(new SimpleGrantedAuthority(role));
                    });
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email,null,authorities);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    filterChain.doFilter(request,response);
                }else {
                    response.sendError(UNAUTHORIZED.value(),UNAUTHORIZED.getReasonPhrase());
                }
            }catch(Exception exception){
                logger.error("Error logging in: {%s}",exception);
                response.setHeader("error",exception.getMessage());
                response.sendError(FORBIDDEN.value());
            }
        }
    }
//
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        excludedUrls.add("/api/v1/login");
//        excludedUrls.add("/test");
//        AntPathMatcher pathMatcher = new AntPathMatcher();
//        return excludedUrls.stream().anyMatch(p -> pathMatcher.match(p, request.getServletPath()));
//    }
}
