package com.example.EmployeeManagement.controller;

import com.example.EmployeeManagement.dto.EmployeeRequestDto;
import com.example.EmployeeManagement.dto.EmployeeResponseDto;
import com.example.EmployeeManagement.entity.Employee;
import com.example.EmployeeManagement.service.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/add")
    public EmployeeResponseDto saveEmployee(@Valid @RequestBody EmployeeRequestDto dto){
        return employeeService.saveEmployee(dto);
    }

    @GetMapping("/get")
    public List<EmployeeResponseDto> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeResponseDto getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/update/{id}")
    public EmployeeResponseDto updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDto dto){
        return employeeService.updateEmployee(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return "Employee Deleted Successfully";
    }

    @GetMapping("/page-sort")
    public ResponseEntity<Page<EmployeeResponseDto>> getEmployees(@RequestParam int page, @RequestParam int size, @RequestParam String field){
        return ResponseEntity.ok(employeeService.getEmployeeWithPaginationAndSorting(page, size, field));

    }

    @GetMapping("/name")
    public ResponseEntity<List<EmployeeResponseDto>> getByName(@RequestParam String name){
        return ResponseEntity.ok(employeeService.findByName(name));
    }

    @GetMapping("/department")
    public ResponseEntity<List<EmployeeResponseDto>> getByDepartment(@RequestParam String department){
        return ResponseEntity.ok(employeeService.findByDepartment(department));
    }

    @GetMapping("/salary")
    public ResponseEntity<List<EmployeeResponseDto>> getBySalary(@RequestParam Double salary){
        return ResponseEntity.ok(employeeService.findBySalaryGreaterThan(salary));
    }

    @GetMapping("/range")
    public ResponseEntity<List<EmployeeResponseDto>> getBySalaryRange(@RequestParam Double minSalary, @RequestParam Double maxSalary){
        return ResponseEntity.ok(employeeService.findBySalaryBetween(minSalary, maxSalary));
    }

}
