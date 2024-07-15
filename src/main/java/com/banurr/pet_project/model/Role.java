package com.banurr.pet_project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "ROLES")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role implements GrantedAuthority
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false,unique = true)
    private Long id;

    @NotBlank
    @Column(name = "NAME",nullable = false)
    private String name;

    @Override
    public String getAuthority()
    {
        return name;
    }
}
