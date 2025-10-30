package com.taskhub.controller;

import com.taskhub.dao.UserDao;
import com.taskhub.dao.entity.UserEntity;
import com.taskhub.service.JwtService;
import com.taskhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController()
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;

    @PostMapping("/user/validate")
    public ResponseEntity<Map<String, Object>> validate(@RequestBody UserEntity user) {
        Map<String, Object> responseData = new HashMap<>();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getUserPassword())
            );

            UserEntity userInfo = userDao.findByEmail(user.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            String token = jwtService.generateToken(user.getEmail());

            // Construct user response manually
            Map<String, Object> userResponse = new HashMap<>();
            userResponse.put("userId", userInfo.getUserId());
            userResponse.put("username", userInfo.getUsername());
            userResponse.put("email", userInfo.getEmail());
            userResponse.put("createdAt", userInfo.getCreatedAt());
            userResponse.put("updatedAt", userInfo.getUpdatedAt());

            // Fix: Use new HashMap instead of Map.of()
            List<Map<String, Object>> roles = userInfo.getAllRoles().stream()
                    .map(role -> {
                        Map<String, Object> roleMap = new HashMap<>();
                        roleMap.put("roleId", role.getRoleId());
                        roleMap.put("roleName", role.getRoleName());
                        roleMap.put("roleDescription", role.getRoleDescription());
                        return roleMap;
                    })
                    .collect(Collectors.toList());
            userResponse.put("allRoles", roles);
            responseData.put("user", userResponse);
            responseData.put("token", token);
            return ResponseEntity.ok(responseData);

        } catch (Exception ex) {
            responseData.put("error", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        }
    }



}
