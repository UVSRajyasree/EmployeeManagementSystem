package com.example.EmployeeManagement.service;

import com.example.EmployeeManagement.dto.DepartmentRequestDto;
import com.example.EmployeeManagement.dto.DepartmentResponseDto;

import java.util.List;

public interface DepartmentService {
    DepartmentResponseDto saveDepartment(DepartmentRequestDto dto);

    List<DepartmentResponseDto> getAllDepartments();

    DepartmentResponseDto updateDepartment(Long id, DepartmentRequestDto dto);

    void deleteDepartment(Long id);
}
