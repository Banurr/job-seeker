package com.banurr.pet_project.dto;

import com.banurr.pet_project.enums.Industry;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class CompanyDto implements Serializable
{
    @NotBlank(message = "Name can't be blank or null")
    private String name;

    @NotBlank(message = "Description can't be blank or null")
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Industry can't be null")
    private Industry industry;

    @NotBlank(message = "Website can't be blank or null")
    private String website;
}
