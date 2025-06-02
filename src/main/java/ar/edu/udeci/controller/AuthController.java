package ar.edu.udeci.controller;

import ar.edu.udeci.dto.UserRegisterDTO;
import ar.edu.udeci.entity.auth.LoginRequest;
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
import java.util.stream.Collectors;

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
    public ResponseEntity<String> register(@RequestBody UserRegisterDTO request) {
        // Si no se envían roles, asignamos por defecto ROLE_USER
        Set<Role> roles;
        if (request.getRoles() == null || request.getRoles().isEmpty()) {
            Role defaultRole = roleRepository.findById("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Rol por defecto no encontrado"));
            roles = Set.of(defaultRole);
        } else {
            roles = request.getRoles().stream()
                    .map(roleName -> roleRepository.findById(roleName)
                            .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + roleName)))
                    .collect(Collectors.toSet());
        }

        userService.registerUser(request.getUsername(), request.getPassword(), roles);
        return ResponseEntity.ok("Usuario registrado");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        return ResponseEntity.ok("Login exitoso");
    }
}