package com.banurr.pet_project.service;

import com.banurr.pet_project.enums.ApplicationStatus;
import com.banurr.pet_project.exception.ApplicationNotFoundException;
import com.banurr.pet_project.exception.VacancyNotFoundException;
import com.banurr.pet_project.model.Application;
import com.banurr.pet_project.model.Vacancy;
import com.banurr.pet_project.repository.ApplicationRepository;
import com.banurr.pet_project.repository.VacancyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private EmailService emailService;

    private final String creation = "Your application created successfully";

    private final String rejection = "You application was rejected";

    private final String accepted = "You application was accepted";

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
        String textOfCreation = "Good day\nYou applied your resume to the Job-Seeker platform\nYour application to the " + vacancy.getTitle() + " vacancy, of company " + vacancy.getCompany().getName() + " was created successfully.\nPlease wait for the response\n\nSincerely, Job-Seeker team";
        emailService.sendEmail(email,creation,textOfCreation);
        log.info("Application {} was created", result);
    }

    public Application findApplicationById(Long id)
    {
        Application application = applicationRepository.findById(id).orElseThrow(()->
        {
            log.error("Application with id {} was not found",id);
            return new ApplicationNotFoundException("Application with id " + id + " was not found");
        });
        log.info("Application with id {} was retrieved ",id);
        return application;
    }

    public void acceptApplication(Long id)
    {
        Application application = findApplicationById(id);
        application.setApplicationStatus(ApplicationStatus.ACCEPTED);
        applicationRepository.save(application);
        String textOfAccepted = "Hello, nice news for you\nYour application to the vacancy " + application.getVacancy().getTitle() + " of the company " + application.getVacancy().getCompany().getName() + " was accepted and HR is ready to call you on an interview\n\nSincerely, Job-Seeker team";
        emailService.sendEmail(application.getEmail(),accepted,textOfAccepted);
        log.info("Application with {} was accepted",id);
    }

    public void rejectApplication(Long id)
    {
        Application application = findApplicationById(id);
        application.setApplicationStatus(ApplicationStatus.REJECTED);
        applicationRepository.save(application);
        String textOfRejected = "Hello, some updates\nUnfortunately\nYour application to the vacancy " + application.getVacancy().getTitle() + " of the company" + application.getVacancy().getCompany().getName() + " was rejected.But your resume was added to the archive, so we can contact you later\n\nSincerely, Job-Seeker team";
        emailService.sendEmail(application.getEmail(),rejection,textOfRejected);
        log.info("Application with {} was rejected",id);
    }
}
