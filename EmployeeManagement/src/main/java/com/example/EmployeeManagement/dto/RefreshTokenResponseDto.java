package com.example.EmployeeManagement.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RefreshTokenResponseDto {

    private String accessToken;
}
