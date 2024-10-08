package com.banurr.pet_project.controller;

import com.banurr.pet_project.dto.AuthResponseDto;
import com.banurr.pet_project.dto.UserLoginDto;
import com.banurr.pet_project.dto.UserRegisterDto;
import com.banurr.pet_project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.util.StringUtils;
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
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody UserLoginDto userLoginDto)
    {
        String token = userService.loginUser(userLoginDto,authenticationManager);
        return new ResponseEntity<>(new AuthResponseDto(token),HttpStatus.OK);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegisterDto userRegisterDto)
    {
        userService.registerUser(userRegisterDto);
        return new ResponseEntity<>("User registered successfully",HttpStatus.OK);
    }

    @PostMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> logout(HttpServletRequest request)
    {
        return userService.logoutUser(request);
    }
}
