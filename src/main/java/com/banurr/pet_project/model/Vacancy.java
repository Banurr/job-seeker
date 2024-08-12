package com.banurr.pet_project.model;

import com.banurr.pet_project.enums.Format;
import com.banurr.pet_project.enums.Level;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "VACANCIES")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Vacancy implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false,unique = true)
    private Long id;

    @NotBlank(message = "Title can't be blank or null")
    @Column(name = "TITLE",nullable = false)
    private String title;

    @NotBlank(message = "Description can't be blank or null")
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "LEVEL")
    private Level level;

    @NotNull
    @Min(value = 0,message = "Minimum years of experience is 0")
    @Max(value = 20,message = "Maximum years of experience is 20")
    @Column(name = "YEARS_OF_EXPERIENCE", nullable = false)
    private Integer yearsOfExperience;

    @Enumerated(EnumType.STRING)
    @Column(name = "FORMAT")
    private Format format;

    @NotNull
    @Min(value = 0,message = "Minimal salary min value is 0")
    @Max(value = Integer.MAX_VALUE, message = "Minimal salary max value is " + Integer.MAX_VALUE)
    @Column(name = "MIN_SALARY", nullable = false)
    private Integer minSalary;

    @NotNull
    @Min(value = 0,message = "Maximal salary min value is 0")
    @Max(value = Integer.MAX_VALUE, message = "Maximal salary max value is " + Integer.MAX_VALUE)
    @Column(name = "MAX_SALARY", nullable = false)
    private Integer maxSalary;

    @NotNull
    @Column(name = "DATE_OF_PUBLICATION", nullable = false)
    private LocalDate dateOfPublication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @ToString.Exclude
    @JoinColumn(name = "COMPANY_ID", nullable = false)
    private Company company;

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vacancy",cascade = CascadeType.ALL,orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private Set<Application> applications;
}
