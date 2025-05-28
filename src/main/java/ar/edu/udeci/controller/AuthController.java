package ar.edu.udeci.controller;

import ar.edu.udeci.entity.auth.RegisterRequest;
import ar.edu.udeci.entity.auth.Role;
import ar.edu.udeci.repository.RoleRepository;
import ar.edu.udeci.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public AuthController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

        Role role = roleRepository.findById("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        userService.registerUser(request.getUsername(), request.getPassword(), Set.of(role));
        return ResponseEntity.ok("Usuario registrado");
    }
}
