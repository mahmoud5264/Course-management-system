package com.example.cms.services;


import com.example.cms.models.User;
import com.example.cms.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public User getUserById (UUID id){
        User user = userRepository.getUserById(id);
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
