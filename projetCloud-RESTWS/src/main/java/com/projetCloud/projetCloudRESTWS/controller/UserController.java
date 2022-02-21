package com.projetCloud.projetCloudRESTWS.controller;

import com.projetCloud.projetCloudRESTWS.model.SignalementDetails;
import com.projetCloud.projetCloudRESTWS.model.User;
import com.projetCloud.projetCloudRESTWS.service.SignalementsDetailsService;
import com.projetCloud.projetCloudRESTWS.service.UserService;
import com.projetCloud.projetCloudRESTWS.util.EmailValidator;
import com.projetCloud.projetCloudRESTWS.util.PasswordConstraintValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SignalementsDetailsService signalDetailsService;

    @PostMapping("/users/register")
    public ResponseEntity<?> registerAsSimpleUser(@ModelAttribute @Valid User user, BindingResult bdr){
        Pattern pattern = Pattern.compile(".*[çÇáéíóúýÁÉÍÓÚÝàèìòùÀÈÌÒÙãõñäëïöüÿÄËÏÖÜÃÕÑâêîôûÂÊÎÔÛ].*");
        Matcher matcher = pattern.matcher(user.getMotDePasse());
        try {
            if(matcher.matches())
                bdr.addError(new FieldError("user","password","Password must not contain accented characters."));
            boolean exist = userService.checkUserEmailExists(user.getEmail());
            if(userService.checkUserEmailExists(user.getEmail()))
                bdr.addError(new FieldError("user","email","Email already used!"));
            if(bdr.hasErrors())
                return ResponseEntity.badRequest().body(bdr.getFieldErrors());
            User userAccount = userService.saveUserWithUserRole(user);
            return ResponseEntity.ok(userAccount);
        }catch(Exception excp){
            return ResponseEntity.badRequest().body(excp);
        }
    }

    @RolesAllowed("ADMIN")
    @PostMapping("/users")
    public ResponseEntity<?> createUserWithRoles(@ModelAttribute User user,BindingResult bdr){
        Pattern pattern = Pattern.compile(".*[çÇáéíóúýÁÉÍÓÚÝàèìòùÀÈÌÒÙãõñäëïöüÿÄËÏÖÜÃÕÑâêîôûÂÊÎÔÛ].*");
        Matcher matcher = pattern.matcher(user.getMotDePasse());
        try {
            if(matcher.matches())
                bdr.addError(new FieldError("user","password","Password must not contain accented characters."));
            if(bdr.hasErrors())
                return ResponseEntity.badRequest().body(bdr.getFieldErrors());
            User savedUser = userService.saveUser(user);
            return ResponseEntity.ok(savedUser);
        }catch(Exception excp){
            return ResponseEntity.badRequest().body(excp);
        }
    }

    @RolesAllowed("USER")
    @GetMapping("/users/current/signalements")
    public List<SignalementDetails> getCurrentUserListSignalements(Principal principal){
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
        return signalDetailsService.getSignalementUtilisateur(user.getId());
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/users/{idUser}/signalements")
    public List<SignalementDetails> getUserListSignalements(@PathVariable("idUser") final Long idUser){
        return signalDetailsService.getSignalementUtilisateur(idUser);
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getUsers();
    }
}
