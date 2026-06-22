package com.example.EmployeeManagement.dto;

public class EmployeeResponseDto {

    private Long id;
    private String name;
    private String departmentName;
    private double Salary;

    public EmployeeResponseDto() {
    }

    public EmployeeResponseDto(Long id, String name, String departmentName, double salary) {
        this.id = id;
        this.name = name;
        this.departmentName = departmentName;
        Salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }
}
