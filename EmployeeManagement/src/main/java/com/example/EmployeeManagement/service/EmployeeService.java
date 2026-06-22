package com.example.EmployeeManagement.service;

import com.example.EmployeeManagement.dto.EmployeeRequestDto;
import com.example.EmployeeManagement.dto.EmployeeResponseDto;
import com.example.EmployeeManagement.entity.Employee;
import org.springframework.data.domain.Page;


import java.util.List;

public interface EmployeeService{

    //Create Employee
    EmployeeResponseDto saveEmployee(EmployeeRequestDto dto);

    List<EmployeeResponseDto> getAllEmployees();

    EmployeeResponseDto getEmployeeById(Long id);

    EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto dto);

    void deleteEmployee(Long id);

    Page<EmployeeResponseDto> getEmployeeWithPaginationAndSorting(
            int page,
            int size,
            String field);

    List<EmployeeResponseDto> findByName(String name);

    List<EmployeeResponseDto> findByDepartment(String departmentName);

    List<EmployeeResponseDto> findBySalaryGreaterThan(Double salary);

    List<EmployeeResponseDto> findBySalaryBetween(Double minSalary, Double maxSalary);


}
