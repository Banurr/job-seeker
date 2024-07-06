package com.banurr.pet_project.model;

import com.banurr.pet_project.enums.Format;
import com.banurr.pet_project.enums.Level;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Enumerated(EnumType.STRING)
    private Level level;

    @NotNull
    @Size(max = 20)
    private Byte yearsOfExperience;

    @Enumerated(EnumType.STRING)
    private Format format;

    @NotNull
    @Size
    private Integer minSalary;

    @NotNull
    @Size
    private Integer maxSalary;

    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;
}
