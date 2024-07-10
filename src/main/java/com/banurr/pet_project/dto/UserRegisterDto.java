package com.banurr.pet_project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRegisterDto
{
    @NotBlank(message = "Name can't be blank or null")
    private String name;

    @NotBlank(message = "Surname can't be blank or null")
    private String surname;

    @Email(message = "Incorrect email format")
    private String email;

    @NotBlank(message = "Password can't be blank or null")
    @Size(min = 8,message = "Password minimal length is 8")
    private String password;

    @NotBlank(message = "Password can't be blank or null")
    @Size(min = 8,message = "Password minimal length is 8")
    private String rePassword;
}
