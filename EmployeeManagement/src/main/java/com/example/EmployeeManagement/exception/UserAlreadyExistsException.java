package com.example.EmployeeManagement.exception;

public class UserAlreadyExistsException extends  RuntimeException{

    public UserAlreadyExistsException(String message){
        super(message);
    }
}
