package com.example.EmployeeManagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class EmployeeRequestDto {

    @NotBlank(message = "Name should not be empty")
    private String name;
    private Long departmentId;
    @Positive(message = "Salary should be positive")
    private Double salary;

    public EmployeeRequestDto() {
    }

    public EmployeeRequestDto(String name, Long departmentId, Double salary) {
        this.name = name;
        this.departmentId = departmentId;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
