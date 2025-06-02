package ar.edu.udeci.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class UserRegisterDTO {
    private String username;
    private String password;
    private List<String> roles;
}