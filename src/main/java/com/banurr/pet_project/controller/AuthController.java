package com.banurr.pet_project.controller;

import com.banurr.pet_project.dto.AuthResponseDto;
import com.banurr.pet_project.dto.UserLoginDto;
import com.banurr.pet_project.dto.UserRegisterDto;
import com.banurr.pet_project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController
{
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody UserLoginDto userLoginDto)
    {
        String token = userService.loginUser(userLoginDto,authenticationManager);
        return new ResponseEntity<>(new AuthResponseDto(token),HttpStatus.OK);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> register(@Valid @RequestBody UserRegisterDto userRegisterDto)
    {
        userService.registerUser(userRegisterDto);
        return new ResponseEntity<>("User registered successfully",HttpStatus.OK);
    }
}
