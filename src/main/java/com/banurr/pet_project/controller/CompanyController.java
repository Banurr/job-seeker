package com.banurr.pet_project.controller;

import com.banurr.pet_project.dto.CompanyDto;
import com.banurr.pet_project.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/company")
public class CompanyController
{
    @Autowired
    private CompanyService companyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void createCompany(@RequestBody @Valid CompanyDto companyDto)
    {
        companyService.createCompany(companyDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("isAuthenticated()")
    public List<CompanyDto> getAllCompanies()
    {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("isAuthenticated()")
    public CompanyDto findCompanyById(@PathVariable(name = "id") UUID id)
    {
        return companyService.findCompanyById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCompanyById(@PathVariable(name = "id") UUID id)
    {
        companyService.deleteCompanyById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateCompany(@PathVariable(name = "id") UUID id,
                              @Valid @RequestBody CompanyDto companyDto)
    {
        companyService.updateCompany(id, companyDto);
    }
}
