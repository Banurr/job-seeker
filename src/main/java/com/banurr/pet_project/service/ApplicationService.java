package com.banurr.pet_project.service;

import com.banurr.pet_project.enums.ApplicationStatus;
import com.banurr.pet_project.exception.VacancyNotFoundException;
import com.banurr.pet_project.mapper.VacancyMapper;
import com.banurr.pet_project.model.Application;
import com.banurr.pet_project.model.Vacancy;
import com.banurr.pet_project.repository.ApplicationRepository;
import com.banurr.pet_project.repository.VacancyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class ApplicationService
{
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private UserService userService;

    public void createApplication(Long id, MultipartFile multipartFile) throws IOException
    {
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow(()-> new VacancyNotFoundException("Vacancy with id " + id + " not found"));
        byte[] resume = multipartFile.getBytes();
        String email = userService.getCurrentUser().getEmail();
        Application application = Application.builder()
                .email(email)
                .resume(resume)
                .applicationStatus(ApplicationStatus.CREATED)
                .vacancy(vacancy)
                .build();
        Application result = applicationRepository.save(application);
        log.info("Application {} was created", result);
    }
}
