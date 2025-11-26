package com.example.cms.services;


import com.example.cms.exceptions.ALreadyExistsException;
import com.example.cms.models.User;
import com.example.cms.repositories.UserRepository;
import com.example.cms.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public String signup(User user){
        Boolean exists = userRepository.checkUserExistByUsername(user.getUsername());
        if(exists){
            throw new ALreadyExistsException("Username already exists");
        }
        exists = userRepository.checkUserExistByEmail(user.getEmail());
        if(exists){
            throw new ALreadyExistsException("Email already exists");
        }
        int rows= userRepository.addUser(user.getUsername(), user.getEmail(), passwordEncoder.encode(user.getPassword()));
        System.out.println(rows);
        if(rows==0){
            throw new RuntimeException("Bad request");
        }
        return "Success";
    }

    public String signin(User user){
        User userDB = userRepository.getUserByEmail(user.getEmail());
        if(userDB==null){
            throw new UsernameNotFoundException("Email not found");
        }
        if(!passwordEncoder.matches(user.getPassword(), userDB.getPassword())){
            throw new RuntimeException("Wrong password");
        }
        return jwtUtils.generateToken(userDB.getUsername(), userDB.getId());
    }

}
