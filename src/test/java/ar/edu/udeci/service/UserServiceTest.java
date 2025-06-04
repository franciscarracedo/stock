package ar.edu.udeci.service;

import ar.edu.udeci.entity.auth.Role;
import ar.edu.udeci.entity.auth.User;
import ar.edu.udeci.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void testRegisterUser() {
        String rawPassword = "password";
        String encodedPassword = "encoded";

        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        Role role = new Role("ROLE_USER");
        userService.registerUser("test", rawPassword, Set.of(role));

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertEquals("test", savedUser.getUsername());
        assertEquals(encodedPassword, savedUser.getPassword());
        assertTrue(savedUser.isEnabled());
        assertTrue(savedUser.getRoles().stream()
                .anyMatch(r -> r.getName().equals("ROLE_USER")));
    }
}

