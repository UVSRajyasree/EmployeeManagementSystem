package com.example.EmployeeManagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RegisterRequestDto {

    @NotBlank(message = "Username is required")
    private String username;


    @NotBlank(message = "password is required")
    @Size(min = 6, message = "minimum six characters required")
    private String password;
}
