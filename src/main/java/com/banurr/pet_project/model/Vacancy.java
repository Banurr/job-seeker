package com.banurr.pet_project.model;

import com.banurr.pet_project.enums.Format;
import com.banurr.pet_project.enums.Level;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "vacancies")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Vacancy
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private LocalDate dateOfPublication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @ToString.Exclude
    private Company company;
}
