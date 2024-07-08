package com.banurr.pet_project.service;

import com.banurr.pet_project.dto.CompanyDto;
import com.banurr.pet_project.mapper.CompanyMapper;
import com.banurr.pet_project.model.Company;
import com.banurr.pet_project.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class CompanyService
{
    @Autowired
    private CompanyRepository companyRepository;

    public void createCompany(CompanyDto companyDto)
    {
        Company company = CompanyMapper.INSTANCE.toEntity(companyDto);
        company.setDateOfRegistration(LocalDate.now());
        companyRepository.save(company);
        log.info("Company {} was created",company);
    }

    public List<CompanyDto> getAllCompanies()
    {
        List<Company> companies = companyRepository.findAll();
        log.info("All companies were retrieved");
        return companies.stream().map(CompanyMapper.INSTANCE::toDto).toList();
    }

    public CompanyDto findCompanyById(Long id)
    {
        Company company = companyRepository.findById(id).orElseThrow();
        log.info("Company with id {} was retrieved",id);
        return CompanyMapper.INSTANCE.toDto(company);
    }

    public void deleteCompanyById(Long id)
    {
        companyRepository.deleteById(id);
        log.info("Company with id {} was deleted",id);
    }

    public void updateCompany(Long id, CompanyDto companyDto)
    {
        Company company = companyRepository.findById(id).orElseThrow();
        company.setName(companyDto.getName());
        company.setDescription(companyDto.getDescription());
        company.setIndustry(companyDto.getIndustry());
        company.setWebsite(companyDto.getWebsite());
        companyRepository.save(company);
        log.info("Company with id {} was updated",id);
    }
}
