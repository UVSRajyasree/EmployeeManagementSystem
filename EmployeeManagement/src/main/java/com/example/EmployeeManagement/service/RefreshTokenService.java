package com.example.EmployeeManagement.service;

import com.example.EmployeeManagement.entity.RefreshToken;
import com.example.EmployeeManagement.entity.User;

import java.util.Optional;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(User user);

    Optional<RefreshToken> findByToken(String token);

    RefreshToken verifyExpiration(RefreshToken token);

    void deleteByUser(User user);

}
