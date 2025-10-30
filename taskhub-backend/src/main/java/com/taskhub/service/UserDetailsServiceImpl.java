package com.taskhub.service;

import com.taskhub.dao.UserDao;
import com.taskhub.dao.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userInfo = userDao.findByEmail(email);

        UserEntity user = userInfo.get();
        List<GrantedAuthority> authorities = user.getAllRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
                .collect(Collectors.toList());

        if (userInfo.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        System.out.println("Authoroties for user" + user.getEmail() + ":" + authorities);

        return userInfo.map((userDetail) -> new UserDetailsImpl(userDetail.getEmail(), userDetail.getUserPassword(), userDetail.getAllRoles()))
                .orElseThrow(() -> new UsernameNotFoundException(email + "Not found"));
    }
}
