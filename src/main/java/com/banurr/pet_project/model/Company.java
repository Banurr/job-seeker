package com.banurr.pet_project.model;

import com.banurr.pet_project.enums.Industry;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "COMPANIES")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Company implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false,unique = true)
    private Long id;

    @NotBlank(message = "Name can't be blank or null")
    @Column(name = "NAME",nullable = false)
    private String name;

    @NotBlank(message = "Description can't be blank or null")
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Industry can't be null")
    @Column(name = "INDUSTRY", nullable = false)
    private Industry industry;

    @NotBlank(message = "Website can't be blank or null")
    @Column(name = "WEBSITE", nullable = false)
    private String website;

    @NotNull
    @Column(name = "DATE_OF_REGISTRATION", nullable = false)
    private LocalDate dateOfRegistration;

    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Vacancy> vacancies;
}
