package com.example.EmployeeManagement.dto;

public class DepartmentResponseDto {

    private Long id;
    private String departmentName;

    public DepartmentResponseDto() {
    }

    public DepartmentResponseDto(Long id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public Long getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
