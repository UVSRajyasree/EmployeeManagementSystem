package com.example.EmployeeManagement.controller;

import com.example.EmployeeManagement.dto.EmployeeRequestDto;
import com.example.EmployeeManagement.dto.EmployeeResponseDto;
import com.example.EmployeeManagement.entity.Employee;
import com.example.EmployeeManagement.service.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService){
        this.employeeService = employeeService;
    }

    //Create Employees
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public EmployeeResponseDto saveEmployee(@Valid @RequestBody EmployeeRequestDto dto){
        return employeeService.saveEmployee(dto);
    }

    //Get Employee
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    @GetMapping("/get")
    public List<EmployeeResponseDto> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    //Get Employee By ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public EmployeeResponseDto getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    //Update Employee
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public EmployeeResponseDto updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDto dto){
        return employeeService.updateEmployee(id, dto);
    }

    //Delete Employee
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return "Employee Deleted Successfully";
    }

    //Get Employee By Pagination And Sorting
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    @GetMapping("/page-sort")
    public ResponseEntity<Page<EmployeeResponseDto>> getEmployees(@RequestParam int page, @RequestParam int size, @RequestParam String field){
        return ResponseEntity.ok(employeeService.getEmployeeWithPaginationAndSorting(page, size, field));

    }

    //Get Employee By Name
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    @GetMapping("/name")
    public ResponseEntity<List<EmployeeResponseDto>> getByName(@RequestParam String name){
        return ResponseEntity.ok(employeeService.findByName(name));
    }

    //Get Employee By Department
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    @GetMapping("/department")
    public ResponseEntity<List<EmployeeResponseDto>> getByDepartment(@RequestParam String department){
        return ResponseEntity.ok(employeeService.findByDepartment(department));
    }

    //Get Employee By Salary
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    @GetMapping("/salary")
    public ResponseEntity<List<EmployeeResponseDto>> getBySalary(@RequestParam Double salary){
        return ResponseEntity.ok(employeeService.findBySalaryGreaterThan(salary));
    }

    //Get Employee In Range
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    @GetMapping("/range")
    public ResponseEntity<List<EmployeeResponseDto>> getBySalaryRange(@RequestParam Double minSalary, @RequestParam Double maxSalary){
        return ResponseEntity.ok(employeeService.findBySalaryBetween(minSalary, maxSalary));
    }

}
