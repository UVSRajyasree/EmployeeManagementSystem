package com.example.EmployeeManagement.service;

import com.example.EmployeeManagement.PasswordGenerator;
import com.example.EmployeeManagement.dto.RegisterRequestDto;
import com.example.EmployeeManagement.dto.RegisterResponseDto;
import com.example.EmployeeManagement.entity.User;
import com.example.EmployeeManagement.exception.UserAlreadyExistsException;
import com.example.EmployeeManagement.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterResponseDto registerUser(RegisterRequestDto dto){

        if(userRepository.findByUsername(dto.getUsername()).isPresent()){
            throw  new UserAlreadyExistsException("Username Already Exists");
        }

        User user = new User();

        user.setUsername(dto.getUsername());

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        user.setRole("EMPLOYEE");

        userRepository.save(user);

        return new RegisterResponseDto("User Registered Successfully");

    }
}
