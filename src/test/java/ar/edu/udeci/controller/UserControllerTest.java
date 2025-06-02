package ar.edu.udeci.controller;

import ar.edu.udeci.dto.UserDTO;
import ar.edu.udeci.entity.auth.User;
import ar.edu.udeci.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("$2a$10$7Q0VLZEM0r0KtW6vFd3nGuZ0zYddjJdFrh5NruztTO3ZBjxGnpCZS");
        admin.setEnabled(true);

        userRepository.save(admin);
    }


    @Test
    @org.springframework.security.test.context.support.WithMockUser(username = "admin", roles = {"ADMIN"})
    void testGetAllUsers_Authorized() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1)) // debería haber un solo usuario
                .andExpect(jsonPath("$[0].username").value("admin"));
    }

    @Test
    @org.springframework.security.test.context.support.WithMockUser(username = "admin", roles = {"ADMIN"})
    void testGetUserById_Found() throws Exception {
        User user = userRepository.findAll().get(0);

        mockMvc.perform(get("/users/" + user.getUsername()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(user.getUsername()));
    }

    @Test
    @org.springframework.security.test.context.support.WithMockUser(username = "admin", roles = {"ADMIN"})
    void testGetUserById_NotFound() throws Exception {
        mockMvc.perform(get("/users/999999"))
                .andExpect(status().isNotFound());
    }
}
