package com.example.EmployeeManagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RefreshTokenRequestDto {

    @NotBlank(message = "Refresh Token is required")
    private String refreshToken;

}
