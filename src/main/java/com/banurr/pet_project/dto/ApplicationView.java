package com.banurr.pet_project.dto;

import com.banurr.pet_project.enums.ApplicationStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationView
{
    @Lob
    private byte[] resume;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @Email(message = "Incorrect email type")
    private String email;

    @NotNull
    private VacancyResponse vacancy;
}
