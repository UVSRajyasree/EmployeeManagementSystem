package com.example.EmployeeManagement.dto;

import java.io.Serializable;
public class EmployeeResponseDto implements Serializable{

    private static final long serialVersionUID =1;
    private Long id;
    private String name;
    private String departmentName;
    private double Salary;
    private String profileImage;

    public EmployeeResponseDto() {
    }

    public EmployeeResponseDto(Long id, String name, String departmentName, double salary, String profileImage) {
        this.id = id;
        this.name = name;
        this.departmentName = departmentName;
        Salary = salary;
        this.profileImage = profileImage;
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

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
