package com.banurr.pet_project.service;

import com.banurr.pet_project.dto.VacancyCreate;
import com.banurr.pet_project.dto.VacancyResponse;
import com.banurr.pet_project.exception.CompanyNotFoundException;
import com.banurr.pet_project.exception.VacancyNotFoundException;
import com.banurr.pet_project.mapper.VacancyMapper;
import com.banurr.pet_project.model.Company;
import com.banurr.pet_project.model.Vacancy;
import com.banurr.pet_project.repository.CompanyRepository;
import com.banurr.pet_project.repository.VacancyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class VacancyService
{
    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public List<VacancyResponse> getAllVacancies()
    {
        List<Vacancy> vacancies = vacancyRepository.findAll();
        log.info("All vacancies were retrieved");
        return vacancies.stream().map(VacancyMapper.INSTANCE::toResponseDto).toList();
    }

    public VacancyResponse findVacancyById(Long id)
    {
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow(()->
        {
            log.error("Vacancy with id {}, was not found",id);
            return new VacancyNotFoundException("Not found vacancy with id " + id);
        });
        log.info("Vacancy with id {} was retrieved",id);
        return VacancyMapper.INSTANCE.toResponseDto(vacancy);
    }

    @Transactional
    public void createVacancy(VacancyCreate vacancyCreate)
    {
        Company company = companyRepository.findById(vacancyCreate.getCompanyId()).orElseThrow();
        Vacancy vacancy = VacancyMapper.INSTANCE.toEntity(vacancyCreate);
        vacancy.setCompany(company);
        vacancy.setDateOfPublication(LocalDate.now());
        vacancyRepository.save(vacancy);
        log.info("Vacancy {} was created",vacancy);
    }

    @Transactional
    public void deleteVacancyById(Long id)
    {
        if(!vacancyRepository.existsById(id))
        {
            log.error("Can't delete vacancy with id {}, it doesn't exist",id);
            throw new VacancyNotFoundException("Vacancy with id " + id + " does not exist");
        }
        vacancyRepository.deleteById(id);
        log.info("Vacancy with id {} was deleted",id);
    }

    @Transactional
    public void updateVacancy(Long id, VacancyCreate vacancyCreate)
    {
        Company company = companyRepository.findById(vacancyCreate.getCompanyId()).orElseThrow(()->
        {
            log.error("Company with id {}, was not found",id);
            return new CompanyNotFoundException("Not found company with id " + id);
        });
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow(()->
        {
            log.error("Vacancy with id {}, was not found",id);
            return new VacancyNotFoundException("Not found vacancy with id " + id);
        });
        vacancy = Vacancy.builder()
                .id(vacancy.getId())
                .title(vacancy.getTitle())
                .description(vacancyCreate.getDescription())
                .dateOfPublication(vacancy.getDateOfPublication())
                .level(vacancyCreate.getLevel())
                .yearsOfExperience(vacancyCreate.getYearsOfExperience())
                .format(vacancyCreate.getFormat())
                .minSalary(vacancyCreate.getMinSalary())
                .maxSalary(vacancyCreate.getMaxSalary())
                .company(company)
                .build();
        vacancyRepository.save(vacancy);
        log.info("Vacancy with id {} was updated",id);
    }
}
