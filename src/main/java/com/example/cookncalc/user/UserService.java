package com.example.cookncalc.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow();
    }

    public Optional<User> findByName(String username) {
        return this.userRepository.findByUsernameIgnoreCase(username);
    }
}
