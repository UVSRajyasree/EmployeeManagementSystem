package com.example.EmployeeManagement.controller;

import com.example.EmployeeManagement.dto.*;
import com.example.EmployeeManagement.entity.RefreshToken;
import com.example.EmployeeManagement.entity.User;
import com.example.EmployeeManagement.repository.UserRepository;
import com.example.EmployeeManagement.service.JwtService;
import com.example.EmployeeManagement.service.RefreshTokenService;
import com.example.EmployeeManagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserService userService;

    private final RefreshTokenService refreshTokenService;

    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService, RefreshTokenService refreshTokenService, UserRepository userRepository ){
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        /* If I use this I will get only AccessToken which is not much secure
        if(authentication.isAuthenticated()){

            String token = jwtService.generateToken(request.getUsername());
            return ResponseEntity.ok(new LoginResponseDto("Login Successful", token));
        }

        *//*return ResponseEntity.badRequest()
                .body(new LoginResponseDto("Invalid Credentials", token));
*/

        if(authentication.isAuthenticated()){
            String accessToken = jwtService.generateToken(request.getUsername());

            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User Not Found"));


            RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

            LoginResponseDto response = new LoginResponseDto("Login Successful",
                    accessToken,
                    refreshToken.getToken());

            return ResponseEntity.ok(response);
        }
        throw new RuntimeException("Invalid Credentials");
    }


    //To Register as a new user
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> registerUser(@Valid @RequestBody RegisterRequestDto dto){
        RegisterResponseDto response = userService.registerUser(dto);
        return ResponseEntity.ok(response);
    }


    //If I use this I will get Access Token from Refresh Token which is more secure
    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponseDto> refreshToken(@Valid @RequestBody RefreshTokenRequestDto request){

        RefreshToken refreshToken = refreshTokenService.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new RuntimeException("Refresh Token Not Found"));

        refreshTokenService.verifyExpiration(refreshToken);

        String accessToken = jwtService.generateToken(refreshToken.getUser().getUsername());

        return ResponseEntity.ok(new RefreshTokenResponseDto(accessToken));

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(Authentication authentication){
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User Not Found"));
        refreshTokenService.deleteByUser(user);
        return ResponseEntity.ok("Logged Out Successfully");
    }




}
