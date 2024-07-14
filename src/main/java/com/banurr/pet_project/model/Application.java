package com.banurr.pet_project.model;

import com.banurr.pet_project.enums.ApplicationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Table(name = "applications")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Application
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @ToString.Exclude
    private byte[] resume;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @Email(message = "Incorrect email type")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vacancy vacancy;
}
