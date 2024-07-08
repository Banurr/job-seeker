package com.banurr.pet_project.model;

import com.banurr.pet_project.enums.Format;
import com.banurr.pet_project.enums.Level;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vacancies")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vacancy
{
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private LocalDate dateOfPublication;

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

    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;
}
