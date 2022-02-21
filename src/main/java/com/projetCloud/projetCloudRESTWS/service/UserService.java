package com.projetCloud.projetCloudRESTWS.service;

import com.projetCloud.projetCloudRESTWS.model.Role;
import com.projetCloud.projetCloudRESTWS.model.User;
import com.projetCloud.projetCloudRESTWS.repository.RoleRepository;
import com.projetCloud.projetCloudRESTWS.repository.UserRepository;
import com.projetCloud.projetCloudRESTWS.util.PasswordConstraintValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> getUsers(){
        return userRepo.findAll();
    }

    public User getUserByEmail(String email){
        User user = null;
        Optional<User> userOptional = userRepo.findByEmail(email);
        if(userOptional.isPresent())
            user = userOptional.get();
        return user;
    }

    public boolean checkUserEmailExists(String email){
        if(this.getUserByEmail(email)!=null){
            return true;
        }
        return false;
    }

    public User saveUser(User user){
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        user.setMotDePasse(bcrypt.encode(user.getMotDePasse()));
        User savedUser = userRepo.save(user);
        return savedUser;
    }

    public User saveUserWithUserRole(User user){
        Optional<Role> optRole = roleRepository.findByNom("ROLE_USER");
        Role role = null;
        Set<Role> roles = new HashSet<Role>();
        if(optRole.isPresent()) {
            role = optRole.get();
            roles.add(role);
        }
        user.setRoles(roles);
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        user.setMotDePasse(bcrypt.encode(user.getMotDePasse()));
        User savedUser = userRepo.save(user);
        return savedUser;
    }
}
