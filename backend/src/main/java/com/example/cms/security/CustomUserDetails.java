package com.example.cms.security;

import com.example.cms.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private UUID id;
    private String email;
    private String password;
    private String username;


    public static CustomUserDetails buildCustomUserDetails(User user) {
        return new CustomUserDetails(user.getId(), user.getEmail(), user.getPassword(), user.getUsername());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }


}
