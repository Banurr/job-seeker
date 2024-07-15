package com.banurr.pet_project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false,unique = true)
    private Long id;

    @NotBlank(message = "Name can't be blank or null")
    @Column(name = "NAME",nullable = false)
    private String name;

    @NotBlank(message = "Surname can't be blank or null")
    @Column(name = "SURNAME",nullable = false)
    private String surname;

    @Email(message = "Incorrect email format")
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password can't be blank or null")
    @Size(min = 8,message = "Password minimal length is 8")
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @NotNull
    @Column(name = "DATE_OF_REGISTRATION", nullable = false)
    private LocalDateTime dateOfRegistration;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return roles;
    }

    @Override
    public String getUsername()
    {
        return email;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
