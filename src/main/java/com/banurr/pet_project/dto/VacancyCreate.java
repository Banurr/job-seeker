package com.banurr.pet_project.dto;

import com.banurr.pet_project.enums.Format;
import com.banurr.pet_project.enums.Level;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VacancyCreate
{
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @Enumerated(EnumType.STRING)
    private Level level;

    @NotNull
    @Min(value = 0)
    @Max(value = 20)
    private Integer yearsOfExperience;

    @Enumerated(EnumType.STRING)
    private Format format;

    @NotNull
    @Min(value = 0)
    private Integer minSalary;

    @NotNull
    @Min(value = 0)
    private Integer maxSalary;

    @NotNull
    private Long companyId;
}
