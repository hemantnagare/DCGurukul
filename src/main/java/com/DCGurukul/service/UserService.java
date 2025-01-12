package com.DCGurukul.service;

import com.DCGurukul.entity.Users;
import com.DCGurukul.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UsersRepository usersRepository;

    public boolean validateUser(String username, String password) {
        Users users = usersRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (password.equals(users.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
}
