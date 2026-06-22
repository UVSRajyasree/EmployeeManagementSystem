package com.example.EmployeeManagement.service;

import com.example.EmployeeManagement.dto.DepartmentRequestDto;
import com.example.EmployeeManagement.dto.DepartmentResponseDto;
import com.example.EmployeeManagement.entity.Department;
import com.example.EmployeeManagement.entity.Employee;
import com.example.EmployeeManagement.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentResponseDto saveDepartment(DepartmentRequestDto dto) {
        Department department = new Department();
        department.setDepartmentName(dto.getDepartmentName());

        Department saved = departmentRepository.save(department);

        return new DepartmentResponseDto(saved.getId(), saved.getDepartmentName());
    }

    @Override
    public List<DepartmentResponseDto> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(dep -> new DepartmentResponseDto(dep.getId(), dep.getDepartmentName()))
                .toList();
    }

    @Override
    public DepartmentResponseDto updateDepartment(Long id, DepartmentRequestDto dto) {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
        existingDepartment.setDepartmentName(dto.getDepartmentName());

        Department updated = departmentRepository.save(existingDepartment);
        return new DepartmentResponseDto(updated.getId(), updated.getDepartmentName());
    }

    @Override
    public void deleteDepartment(Long id) {
        Department deleteDepartment = departmentRepository.findById(id)
                .orElseThrow(()->new RuntimeException());
        departmentRepository.delete(deleteDepartment);
    }
}
