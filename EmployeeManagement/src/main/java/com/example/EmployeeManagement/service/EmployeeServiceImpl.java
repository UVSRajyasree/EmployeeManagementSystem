package com.example.EmployeeManagement.service;

import com.example.EmployeeManagement.dto.EmployeeRequestDto;
import com.example.EmployeeManagement.dto.EmployeeResponseDto;
import com.example.EmployeeManagement.entity.Department;
import com.example.EmployeeManagement.entity.Employee;
import com.example.EmployeeManagement.exception.EmployeeNotFoundException;
import com.example.EmployeeManagement.mapper.EmployeeMapper;
import com.example.EmployeeManagement.repository.DepartmentRepository;
import com.example.EmployeeManagement.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }
        @Override
        public EmployeeResponseDto saveEmployee(EmployeeRequestDto dto){
        log.info("Creating Employee with name : {}", dto.getName());

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(()->new RuntimeException("Department not found"));

            Employee employee = EmployeeMapper.toEntity(dto, department);

            Employee savedEmployee = employeeRepository.save(employee);
        log.info("Employee created with name: {}", dto.getName());

            return EmployeeMapper.toDto(savedEmployee);
        }

        @Override
        @Cacheable(value = "employees")
        public List<EmployeeResponseDto> getAllEmployees(){
           return employeeRepository.findAll()
                   .stream()
                   .map(EmployeeMapper::toDto)
                   .collect(Collectors.toList());
        }

        @Override
        @Cacheable(value = "employees", key = "#id")
        public EmployeeResponseDto getEmployeeById(Long id){
            log.info("Fetching employee with id: {} ", id);
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found"));
            return EmployeeMapper.toDto(employee);
        }


    @Override
    @CachePut(value = "employees", key ="#id")
    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto dto) {
        log.info("Updating employee with id: {}", id);
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found"));

        Department department = departmentRepository.findById(dto.getDepartmentId())
                        .orElseThrow(()-> new RuntimeException("Department Not Found"));

        existingEmployee.setName(dto.getName());
        existingEmployee.setDepartment(department);
        existingEmployee.setSalary(dto.getSalary());

        Employee updatedEmployee =
                employeeRepository.save(existingEmployee);
        log.info("Updated Employee with id: {}", id);
        return EmployeeMapper.toDto(updatedEmployee);

    }

    @Override
    @CacheEvict(value= "employees", key = "#id")
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with id: {}", id);
       Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found"));
       log.info("Deleted employee with id : {}",id);
        employeeRepository.delete(employee);
    }

    @Override
    public Page<EmployeeResponseDto> getEmployeeWithPaginationAndSorting(int page, int size, String field ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(field));
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return employeePage.map(EmployeeMapper::toDto);
    }


    @Override
    public List<EmployeeResponseDto> findByName(String name) {
        return employeeRepository.findByName(name)
                .stream()
                .map(EmployeeMapper::toDto)
                .toList();
    }

    @Override
    public List<EmployeeResponseDto> findByDepartment(String departmentName) {
        return employeeRepository.findByDepartment_DepartmentName(departmentName)
                .stream()
                .map(EmployeeMapper::toDto)
                .toList();
    }

    @Override
    public List<EmployeeResponseDto> findBySalaryGreaterThan(Double salary) {
        return employeeRepository.findBySalaryGreaterThan(salary)
                .stream()
                .map(EmployeeMapper::toDto)
                .toList();
    }

    @Override
    public List<EmployeeResponseDto> findBySalaryBetween(Double minSalary, Double maxSalary) {
        return employeeRepository.findBySalaryBetween(minSalary, maxSalary)
                .stream()
                .map(EmployeeMapper::toDto)
                .toList();
    }



}
