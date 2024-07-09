package com.banurr.pet_project.dto;

import com.banurr.pet_project.enums.Format;
import com.banurr.pet_project.enums.Level;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VacancyResponse
{
    @NotBlank(message = "Title can't be blank or null")
    private String title;

    @NotBlank(message = "Description can't be blank or null")
    private String description;

    @Enumerated(EnumType.STRING)
    private Level level;

    @NotNull
    @Min(value = 0,message = "Minimum years of experience is 0")
    @Max(value = 20,message = "Maximum years of experience is 20")
    private Integer yearsOfExperience;

    @Enumerated(EnumType.STRING)
    private Format format;

    @NotNull
    @Min(value = 0,message = "Minimal salary min value is 0")
    @Max(value = Integer.MAX_VALUE, message = "Minimal salary max value is " + Integer.MAX_VALUE)
    private Integer minSalary;

    @NotNull
    @Min(value = 0,message = "Maximal salary min value is 0")
    @Max(value = Integer.MAX_VALUE, message = "Maximal salary max value is " + Integer.MAX_VALUE)
    private Integer maxSalary;

    @NotNull
    private CompanyDto company;
}
