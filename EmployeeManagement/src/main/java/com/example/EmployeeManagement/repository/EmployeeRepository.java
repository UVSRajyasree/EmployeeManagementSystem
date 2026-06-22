package com.example.EmployeeManagement.repository;

import com.example.EmployeeManagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long > {

    List<Employee> findByName(String name);

    List<Employee> findByDepartment_DepartmentName(String departmentName);

    List<Employee> findBySalaryGreaterThan(Double salary);

    List<Employee> findBySalaryBetween(Double minSalary, Double maxSalary);


}
