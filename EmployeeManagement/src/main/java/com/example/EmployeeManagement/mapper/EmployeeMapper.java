package com.example.EmployeeManagement.mapper;

import com.example.EmployeeManagement.dto.EmployeeRequestDto;
import com.example.EmployeeManagement.dto.EmployeeResponseDto;
import com.example.EmployeeManagement.entity.Department;
import com.example.EmployeeManagement.entity.Employee;

public class EmployeeMapper {

    public static Employee toEntity(EmployeeRequestDto dto, Department department){
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setDepartment(department);
        employee.setSalary(dto.getSalary());
        return employee;
    }

    public static EmployeeResponseDto toDto(Employee employee){

        return new EmployeeResponseDto(
                employee.getId(),
                employee.getName(),
                employee.getDepartment().getDepartmentName(),
                employee.getSalary()
        );
    }
}


