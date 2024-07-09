package com.banurr.pet_project.service;

import com.banurr.pet_project.dto.VacancyCreate;
import com.banurr.pet_project.dto.VacancyResponse;
import com.banurr.pet_project.mapper.VacancyMapper;
import com.banurr.pet_project.model.Company;
import com.banurr.pet_project.model.Vacancy;
import com.banurr.pet_project.repository.CompanyRepository;
import com.banurr.pet_project.repository.VacancyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow();
        log.info("Vacancy with id {} was retrieved",id);
        return VacancyMapper.INSTANCE.toResponseDto(vacancy);
    }

    public void createVacancy(VacancyCreate vacancyCreate)
    {
        Company company = companyRepository.findById(vacancyCreate.getCompanyId()).orElseThrow();
        Vacancy vacancy = VacancyMapper.INSTANCE.toEntity(vacancyCreate);
        vacancy.setCompany(company);
        vacancy.setDateOfPublication(LocalDate.now());
        vacancyRepository.save(vacancy);
        log.info("Vacancy {} was created",vacancy);
    }

    public void deleteVacancyById(Long id)
    {
        vacancyRepository.deleteById(id);
        log.info("Vacancy with id {} was deleted",id);
    }

    public void updateVacancy(Long id, VacancyCreate vacancyCreate)
    {
        Company company = companyRepository.findById(vacancyCreate.getCompanyId()).orElseThrow();
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow();
        vacancy.setLevel(vacancyCreate.getLevel());
        vacancy.setFormat(vacancyCreate.getFormat());
        vacancy.setDescription(vacancyCreate.getDescription());
        vacancy.setTitle(vacancyCreate.getTitle());
        vacancy.setCompany(company);
        vacancy.setYearsOfExperience(vacancyCreate.getYearsOfExperience());
        vacancy.setMinSalary(vacancyCreate.getMinSalary());
        vacancy.setMaxSalary(vacancy.getMaxSalary());
        vacancyRepository.save(vacancy);
        log.info("Vacancy with id {} was updated",id);
    }
}
