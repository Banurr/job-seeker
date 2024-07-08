package com.banurr.pet_project.controller;

import com.banurr.pet_project.dto.CompanyDto;
import com.banurr.pet_project.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public void createCompany(@Valid @RequestBody CompanyDto companyDto)
    {
        companyService.createCompany(companyDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyDto> getAllCompanies()
    {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDto findCompanyById(@PathVariable(name = "id") Long id)
    {
        return companyService.findCompanyById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompanyById(@PathVariable(name = "id") Long id)
    {
        companyService.deleteCompanyById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCompany(@PathVariable(name = "id") Long id,
                              @Valid @RequestBody CompanyDto companyDto)
    {
        companyService.updateCompany(id,companyDto);
    }
}
