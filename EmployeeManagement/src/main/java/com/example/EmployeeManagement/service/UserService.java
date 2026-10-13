package com.example.EmployeeManagement.service;

import com.example.EmployeeManagement.dto.RegisterRequestDto;
import com.example.EmployeeManagement.dto.RegisterResponseDto;

public interface UserService {

    RegisterResponseDto registerUser(RegisterRequestDto dto);
}
