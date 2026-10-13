package com.example.EmployeeManagement.dto;

import lombok.*;
import org.hibernate.annotations.SecondaryRow;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RegisterResponseDto {

    private String message;
}
