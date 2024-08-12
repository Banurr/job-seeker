package com.banurr.pet_project.model;

import com.banurr.pet_project.enums.ApplicationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Table(name = "APPLICATIONS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Application
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false,unique = true)
    private Long id;

    @Column(name = "RESUME",nullable = false)
    private String resume;

    @Enumerated(EnumType.STRING)
    @Column(name = "APPLICATION_STATUS", nullable = false)
    private ApplicationStatus applicationStatus;

    @Email(message = "Incorrect email type")
    @Column(name = "EMAIL",nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VACANCY_ID")
    private Vacancy vacancy;
}
