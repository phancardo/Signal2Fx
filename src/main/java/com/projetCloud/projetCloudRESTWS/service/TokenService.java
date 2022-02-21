package com.projetCloud.projetCloudRESTWS.service;

import com.projetCloud.projetCloudRESTWS.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;
}
