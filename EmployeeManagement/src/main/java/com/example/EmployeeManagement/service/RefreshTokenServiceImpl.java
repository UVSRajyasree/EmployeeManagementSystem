package com.example.EmployeeManagement.service;

import com.example.EmployeeManagement.entity.RefreshToken;
import com.example.EmployeeManagement.entity.User;
import com.example.EmployeeManagement.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository){
        this.refreshTokenRepository = refreshTokenRepository;
    }

    /*@Override
    @Transactional
    public RefreshToken createRefreshToken(User user){

        refreshTokenRepository.deleteByUser(user);

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(user);

        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken.setExpiryDate(Instant.now().plusSeconds(7*24*60*60));

        return refreshTokenRepository.save(refreshToken);

    }*/

    @Override
    @Transactional
    public RefreshToken createRefreshToken(User user) {

        Optional<RefreshToken> existingToken =
                refreshTokenRepository.findByUser(user);

        RefreshToken refreshToken;

        if (existingToken.isPresent()) {

            refreshToken = existingToken.get();

        } else {

            refreshToken = new RefreshToken();
            refreshToken.setUser(user);
        }

        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken.setExpiryDate(
                Instant.now().plusSeconds(7 * 24 * 60 * 60)
        );

        return refreshTokenRepository.save(refreshToken);
    }


    @Override
    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);

            throw new RuntimeException("Refresh Token Expired");
        }
        return  token;
    }

    @Override
    @Transactional
    public void deleteByUser(User user) {
        refreshTokenRepository.deleteByUser(user);
    }
}
