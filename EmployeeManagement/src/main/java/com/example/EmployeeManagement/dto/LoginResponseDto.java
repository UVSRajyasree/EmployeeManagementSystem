package com.example.EmployeeManagement.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LoginResponseDto {

    private String message;

    private String accessToken;

    private String refreshToken;

}
