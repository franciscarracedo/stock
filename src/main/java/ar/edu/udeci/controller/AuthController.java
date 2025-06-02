package ar.edu.udeci.controller;

import ar.edu.udeci.entity.auth.LoginRequest;
import ar.edu.udeci.entity.auth.RegisterRequest;
import ar.edu.udeci.entity.auth.Role;
import ar.edu.udeci.repository.RoleRepository;
import ar.edu.udeci.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserService userService;
    private final RoleRepository roleRepository;

    public AuthController(AuthenticationManager authManager, UserService userService, RoleRepository roleRepository) {
        this.authManager = authManager;
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        if (auth.isAuthenticated()) {
            return ResponseEntity.ok("Login exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login fallido");
        }
    }
}