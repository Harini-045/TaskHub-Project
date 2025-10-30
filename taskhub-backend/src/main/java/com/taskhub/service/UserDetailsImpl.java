package com.taskhub.service;

import com.taskhub.dao.entity.RoleEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private String email; // Changed from 'name' to 'email' for clarity
    private String user_password;
    private List<SimpleGrantedAuthority> allRoles;

    public UserDetailsImpl(String email , String user_password, List<RoleEntity> allRoles) {
        this.email = email; // Use email as username
        this.user_password = user_password;
        this.allRoles = allRoles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName())).toList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return allRoles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return user_password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
