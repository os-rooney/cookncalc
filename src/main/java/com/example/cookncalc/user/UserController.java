package com.example.cookncalc.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<UserDTO> users() {
        return this.userService.findAll()
                .stream()
                .map(UserDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{userId}")
    public UserDTO userById(@PathVariable Long userId) {
        return UserDTO.fromEntity(this.userService.findById(userId));
    }

    @GetMapping("/current")
    public UserDTO current(Principal principal) {
        if (principal != null) {
            return UserDTO.fromEntity(this.userService.findByName(principal.getName()).orElseThrow());
        }
        return null;
    }

}
