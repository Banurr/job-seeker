package com.banurr.pet_project.model;

import com.banurr.pet_project.enums.Industry;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
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

    @NotBlank(message = "Name can't be blank or null")
    private String name;

    @NotBlank(message = "Description can't be blank or null")
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Industry can't be null")
    private Industry industry;

    @NotBlank(message = "Website can't be blank or null")
    private String website;

    @NotNull
    private LocalDate dateOfRegistration;

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Vacancy> vacancies;
}
