package it21678.application.controller;

import it21678.application.entity.Role;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import it21678.application.repository.RoleRepository;

@Controller
public class AuthController {

    @Autowired
    RoleRepository roleRepository;

    @PostConstruct
    public void setup(){
        roleRepository.findByName("ROLE_ADMIN").orElseGet(() -> {
            roleRepository.save(new Role("ROLE_ADMIN"));
        return null;
        });
        roleRepository.findByName("ROLE_MODERATOR").orElseGet(() -> {
            roleRepository.save(new Role("ROLE_MODERATOR"));
            return null;
        });
        roleRepository.findByName("ROLE_USER").orElseGet(() -> {
            roleRepository.save(new Role("ROLE_USER"));
            return null;
        });
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}