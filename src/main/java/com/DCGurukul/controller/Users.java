package com.DCGurukul.controller;

import com.DCGurukul.config.JwtUtil;
import com.DCGurukul.service.UserService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class Users {
    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam  @NotBlank(message = "Parameter 'username' is required") String username, @RequestParam @NotBlank(message = "Parameter 'password' is required") String password) {
        boolean isValid = userService.validateUser(username, password);
        if (isValid) {
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid Credentials");
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<com.DCGurukul.entity.Users>> getAllUsers() {
        List<com.DCGurukul.entity.Users> users = userService.getAllUsers();
        return ResponseEntity.ok(users); // Return 200 OK with the list of users
    }

}

