package it21678.application.controller;

import it21678.application.entity.Role;
import it21678.application.entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import it21678.application.repository.RoleRepository;
import it21678.application.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AuthController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void setup() {
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
        userRepository.findByUsername("admin").orElseGet(() -> {
            User user = new User("admin", "admin@gmail.com", passwordEncoder.encode("admin"));
            Role role = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            user = userRepository.save(user);
            return null;
        });

        userRepository.findByUsername("user").orElseGet(() -> {
            User user = new User("user", "user@gmail.com", passwordEncoder.encode("user"));
            Role role = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            user = userRepository.save(user);
            return null;
        });

        userRepository.findByUsername("eforia").orElseGet(() -> {
            User user = new User("eforia", "eforia@gmail.com", passwordEncoder.encode("eforia"));
            Role role = roleRepository.findByName("ROLE_MODERATOR")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            user = userRepository.save(user);
            return null;
        });
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}