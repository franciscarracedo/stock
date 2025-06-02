package ar.edu.udeci.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private boolean enabled;
    private Set<String> roles;
}
