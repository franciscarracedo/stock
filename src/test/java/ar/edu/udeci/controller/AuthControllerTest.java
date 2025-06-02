package ar.edu.udeci.controller;

import ar.edu.udeci.dto.UserRegisterDTO;
import ar.edu.udeci.entity.auth.LoginRequest;
import ar.edu.udeci.entity.auth.Role;
import ar.edu.udeci.repository.RoleRepository;
import ar.edu.udeci.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ar.edu.udeci.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.sql.init.mode=never")
class AuthControllerTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authManager;

    @MockBean
    private UserService userService;

    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        Mockito.when(roleRepository.findById("ROLE_USER"))
                .thenReturn(Optional.of(new Role("ROLE_USER")));

        ar.edu.udeci.entity.auth.User fakeUser = new ar.edu.udeci.entity.auth.User();
        fakeUser.setUsername("testuser");
        fakeUser.setPassword(new BCryptPasswordEncoder().encode("testpass"));
        fakeUser.setEnabled(true);
        fakeUser.setRoles(Set.of(new Role("ROLE_USER")));

        Mockito.when(userRepository.findByUsername("testuser"))
                .thenReturn(Optional.of(fakeUser));
    }

    @Test
    void testRegister_Success() throws Exception {
        UserRegisterDTO dto = new UserRegisterDTO();
        dto.setUsername("nuevoUsuario");
        dto.setPassword("1234");
        dto.setRoles(List.of("ROLE_USER"));

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void testLogin_Success() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUsername("testuser");
        request.setPassword("testpass");

        // Mock de autenticación exitosa
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );

        Mockito.when(authManager.authenticate(Mockito.any()))
                .thenReturn(authentication);

        mockMvc.perform(post("/auth/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }


}
