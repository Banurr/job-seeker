package com.banurr.pet_project.service;

import com.banurr.pet_project.dto.CompanyDto;
import com.banurr.pet_project.enums.Industry;
import com.banurr.pet_project.mapper.CompanyMapper;
import com.banurr.pet_project.model.Company;
import com.banurr.pet_project.repository.CompanyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest
{
    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown()
    {

    }

    @Test
    void createCompanyTest()
    {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName("Apple");
        companyDto.setDescription("Huge company");
        companyDto.setIndustry(Industry.EDUCATION);
        companyDto.setWebsite("www.mockito.org");

        Company company = Company.builder()
                .id(1L)
                .name("Apple")
                .description("Huge company")
                .industry(Industry.EDUCATION)
                .website("www.mockito.org")
                .dateOfRegistration(LocalDate.now())
                .build();

        when(companyRepository.save(any(Company.class))).thenReturn(company);

        // Act
        companyService.createCompany(companyDto);

        // Assert
        verify(companyRepository, times(1)).save(any(Company.class));
    }

    @Test
    void getAllCompanies()
    {

    }

    @Test
    void findCompanyById()
    {
    }

    @Test
    void deleteCompanyById()
    {
    }

    @Test
    void updateCompany()
    {
    }
}