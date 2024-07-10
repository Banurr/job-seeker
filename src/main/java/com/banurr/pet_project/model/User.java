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
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name can't be blank or null")
    private String name;

    @NotBlank(message = "Surname can't be blank or null")
    private String surname;

    @Email(message = "Incorrect email format")
    private String email;

    @NotBlank(message = "Password can't be blank or null")
    @Size(min = 8,message = "Password minimal length is 8")
    private String password;

    @NotNull
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
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled()
    {
        return UserDetails.super.isEnabled();
    }
}
