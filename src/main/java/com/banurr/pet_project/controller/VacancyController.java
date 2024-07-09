package com.banurr.pet_project.controller;

import com.banurr.pet_project.dto.VacancyCreate;
import com.banurr.pet_project.dto.VacancyResponse;
import com.banurr.pet_project.service.VacancyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vacancy")
public class VacancyController
{
    @Autowired
    private VacancyService vacancyService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VacancyResponse> getAllVacancies()
    {
        return vacancyService.getAllVacancies();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VacancyResponse findVacancyById(@PathVariable(name = "id") Long id)
    {
        return vacancyService.findVacancyById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createVacancy(@Valid @RequestBody VacancyCreate vacancyCreate)
    {
        vacancyService.createVacancy(vacancyCreate);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateVacancy(@PathVariable(name = "id") Long id,
                              @Valid @RequestBody VacancyCreate vacancyCreate)
    {
        vacancyService.updateVacancy(id, vacancyCreate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVacancy(@PathVariable(name = "id") Long id)
    {
        vacancyService.deleteVacancyById(id);
    }
}
