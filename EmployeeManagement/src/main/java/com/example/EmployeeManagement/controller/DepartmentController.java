package com.example.EmployeeManagement.controller;

import com.example.EmployeeManagement.dto.DepartmentRequestDto;
import com.example.EmployeeManagement.dto.DepartmentResponseDto;
import com.example.EmployeeManagement.service.DepartmentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService){
        this.departmentService = departmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<DepartmentResponseDto> saveDepartment(@RequestBody DepartmentRequestDto dto){
        return ResponseEntity.ok(departmentService.saveDepartment(dto));
    }

    @GetMapping("/get")
    public ResponseEntity<List<DepartmentResponseDto>> getDepartment(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DepartmentResponseDto> updateDepartment(@PathVariable Long id, @RequestBody DepartmentRequestDto dto){
        return ResponseEntity.ok(departmentService.updateDepartment(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("Department Deleted Successfully");
    }
}

