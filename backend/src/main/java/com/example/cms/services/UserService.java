package com.example.cms.services;


import com.example.cms.dto.UserDto;
import com.example.cms.models.User;
import com.example.cms.repositories.UserRepository;
import com.example.cms.requests.UserEditRequest;
import com.example.cms.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public UserDto getUserById (UUID id){
        User user = userRepository.getUserById(id);
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }
        return modelMapper.map(user, UserDto.class);
    }

    public void editUser(UserEditRequest user){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setId(userDetails.getId());
        User me = userRepository.getUserById(user.getId());
        if(user.getFirstname()==null){
            user.setFirstname(me.getFirstname());
        }
        if(user.getLastname()==null){
            user.setLastname(me.getLastname());
        }
        if(user.getDateOfBirth()==null){
            user.setDateOfBirth(me.getDateOfBirth());
        }
        userRepository.editUser(user.getFirstname(), user.getLastname(), user.getDateOfBirth(), user.getId());
    }
}
