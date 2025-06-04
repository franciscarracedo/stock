package ar.edu.udeci.service;

import ar.edu.udeci.entity.auth.Role;
import ar.edu.udeci.entity.auth.User;
import ar.edu.udeci.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService userDetailsService;

    @Test
    void testLoadUserByUsername_UserExists() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("encoded");
        user.setEnabled(true);
        Role role = new Role("ROLE_USER");
        user.setRoles(Set.of(role));

        when(userRepository.findByUsername("test")).thenReturn(Optional.of(user));

        UserDetails result = userDetailsService.loadUserByUsername("test");

        assertEquals("test", result.getUsername());
        assertEquals("encoded", result.getPassword());
        assertTrue(result.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByUsername("missing")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () ->
                userDetailsService.loadUserByUsername("missing"));
    }
}
