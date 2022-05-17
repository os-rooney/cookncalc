package com.example.cookncalc.security;


import com.example.cookncalc.security.auth.AuthService;
import com.example.cookncalc.security.auth.RegistrationDTO;
import com.example.cookncalc.user.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SetupComponent implements ApplicationListener<ApplicationReadyEvent> {

    private final UserRepository userRepository;
    private final AuthService authService;

    public SetupComponent(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (this.userRepository.count() == 0) { // only create users if the database is empty
            RegistrationDTO bob = new RegistrationDTO("bob", "bob123");
            this.authService.register(bob);

            RegistrationDTO hodor = new RegistrationDTO("hodor", "hodor123");
            this.authService.registerAdmin(hodor);
        }
    }
}

