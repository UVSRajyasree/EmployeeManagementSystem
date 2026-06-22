package com.example.EmployeeManagement.dto;

public class DepartmentRequestDto {

    private String departmentName;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public DepartmentRequestDto(String departmentName) {
        this.departmentName = departmentName;
    }
}
