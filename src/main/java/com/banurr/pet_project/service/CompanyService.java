package com.banurr.pet_project.service;

import com.banurr.pet_project.dto.CompanyDto;
import com.banurr.pet_project.exception.CompanyNotFoundException;
import com.banurr.pet_project.mapper.CompanyMapper;
import com.banurr.pet_project.model.Company;
import com.banurr.pet_project.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CompanyService
{
    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    public void createCompany(CompanyDto companyDto)
    {
        Company company = CompanyMapper.INSTANCE.toEntity(companyDto);
        company.setDateOfRegistration(LocalDate.now());
        Company created = companyRepository.save(company);
        cacheCompany(created);
        log.info("Company {} was created",company);

    }

    @CachePut(value = "companies", key = "#company.id")
    public void cacheCompany(Company company) { }


    @CachePut("companies")
    public List<CompanyDto> getAllCompanies()
    {
        List<Company> companies = companyRepository.findAll();
        log.info("All companies were retrieved");
        return companies.stream().map(CompanyMapper.INSTANCE::toDto).toList();
    }

    @CachePut(value = "companies", key = "#id")
    public CompanyDto findCompanyById(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Company with id {} was not found", id);
                    throw new CompanyNotFoundException("Not found company with id " + id);
                });
        log.info("Company with id {} was retrieved", id);
        return CompanyMapper.INSTANCE.toDto(company);
    }

    @Transactional
    @CacheEvict(value = "companies", key = "#id")
    public void deleteCompanyById(UUID id)
    {
        if(!companyRepository.existsById(id))
        {
            log.error("Can't delete company with id {}, it doesn't exist",id);
            throw new CompanyNotFoundException("Company with id " + id + " does not exist");
        }
        companyRepository.deleteById(id);
        log.info("Company with id {} was deleted",id);
    }

    @Transactional
    @CachePut(value = "companies", key = "#id")
    public void updateCompany(UUID id, CompanyDto companyDto)
    {
        Company company = companyRepository.findById(id).orElseThrow(()->
        {
            log.error("Can't update company with id {} ,it doesn't exist",id);
            return new CompanyNotFoundException("Not found company with id " + id);
        });
        company = Company.builder()
                .id(company.getId())
                .name(companyDto.getName())
                .description(companyDto.getDescription())
                .dateOfRegistration(company.getDateOfRegistration())
                .industry(companyDto.getIndustry())
                .website(companyDto.getWebsite())
                .vacancies(company.getVacancies())
                .build();
        companyRepository.save(company);
        log.info("Company with id {} was updated",id);
    }
}
