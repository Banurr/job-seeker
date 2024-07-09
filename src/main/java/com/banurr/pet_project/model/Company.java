package com.banurr.pet_project.model;

import com.banurr.pet_project.enums.Industry;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Company
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Industry industry;

    @NotNull
    private LocalDate dateOfRegistration;

    @NotBlank
    private String website;

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Vacancy> vacancies;
}
