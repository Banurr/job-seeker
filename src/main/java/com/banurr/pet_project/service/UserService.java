package com.banurr.pet_project.service;

import com.banurr.pet_project.dto.UserLoginDto;
import com.banurr.pet_project.dto.UserRegisterDto;
import com.banurr.pet_project.exception.PasswordsDoNotMatchException;
import com.banurr.pet_project.exception.UserAlreadyExistsException;
import com.banurr.pet_project.mapper.UserMapper;
import com.banurr.pet_project.model.Role;
import com.banurr.pet_project.model.User;
import com.banurr.pet_project.repository.RoleRepository;
import com.banurr.pet_project.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
@Slf4j
public class UserService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByEmail(username).orElseThrow(()->
        {
            throw new UsernameNotFoundException("User with email " + username + "was not found");
        });
        log.info("User with email {} was retrieved",username);
        return user;
    }

    public void registerUser(UserRegisterDto userRegisterDto)
    {
        if(userRepository.existsByEmail(userRegisterDto.getEmail()))
        {
            log.error("User with email {} already exists",userRegisterDto.getEmail());
            throw new UserAlreadyExistsException("User with email " + userRegisterDto.getEmail() + " already exists");
        }
        if(!userRegisterDto.getPassword().equals(userRegisterDto.getRePassword()))
        {
            log.error("Passwords do not match");
            throw new PasswordsDoNotMatchException("Passwords do not match");
        }
        User user = UserMapper.INSTANCE.toEntity(userRegisterDto);
        user.setDateOfRegistration(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        Role role = roleRepository.findRoleUser();
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
        log.info("User with email {} was successfully registered",userRegisterDto.getEmail());
    }

    public void loginUser(UserLoginDto userLoginDto, AuthenticationManager authenticationManager)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(),
                        userLoginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("User with email {} authenticated",userLoginDto.getEmail());
    }
}