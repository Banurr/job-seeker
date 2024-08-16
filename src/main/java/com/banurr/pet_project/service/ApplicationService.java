package com.banurr.pet_project.service;

import com.banurr.pet_project.dto.ApplicationView;
import com.banurr.pet_project.enums.ApplicationStatus;
import com.banurr.pet_project.exception.ApplicationNotFoundException;
import com.banurr.pet_project.exception.VacancyAlreadyExistsException;
import com.banurr.pet_project.exception.VacancyNotFoundException;
import com.banurr.pet_project.mapper.ApplicationMapper;
import com.banurr.pet_project.model.Application;
import com.banurr.pet_project.model.Vacancy;
import com.banurr.pet_project.repository.ApplicationRepository;
import com.banurr.pet_project.repository.VacancyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

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

    @Autowired
    private MinioService minioService;

    public void createApplication(UUID id, MultipartFile multipartFile) throws IOException
    {
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow(()-> new VacancyNotFoundException("Vacancy with id " + id + " not found"));
        InputStream inputStream = multipartFile.getInputStream();
        String email = userService.getCurrentUser().getEmail();

        if(applicationRepository.existsApplicationByEmailAndVacancy(email,vacancy))
        {
            log.error("User with email {} already have application on vacancy with id {}",email,vacancy.getId());
            throw new VacancyAlreadyExistsException("Application from user with email " + email + " to the vacancy with id " + vacancy.getId() + " already exists");
        }

        String randomId = UUID.randomUUID().toString();
        Application application = Application.builder()
                .email(email)
                .resume(randomId)
                .applicationStatus(ApplicationStatus.CREATED)
                .vacancy(vacancy)
                .build();

        minioService.saveResumeToS3(randomId,inputStream,multipartFile.getSize());
        inputStream.close();
        Application result = applicationRepository.save(application);
        String textOfCreation = "Good day\nYou applied your resume to the Job-Seeker platform\nYour application to the " + vacancy.getTitle() + " vacancy, of company " + vacancy.getCompany().getName() + " was created successfully.\nPlease wait for the response\n\nSincerely, Job-Seeker team";
        String creation = "Your application created successfully";
        emailService.sendEmail(email, creation,textOfCreation);
        log.info("Application {} was created", result);
    }

    public Application findApplicationById(Long id) throws Exception {
        Application application = applicationRepository.findById(id).orElseThrow(()->
        {
            log.error("Application with id {} was not found",id);
            return new ApplicationNotFoundException("Application with id " + id + " was not found");
        });
        byte[] file = minioService.getResumeFromS3(application.getResume());
        log.info("Application with id {} was retrieved ",id);
        return application;
    }

    public ApplicationView retrieveApplicationById(Long id) throws Exception {
        Application application = findApplicationById(id);
        ApplicationView applicationView = ApplicationMapper.INSTANCE.toDto(application);
        applicationView.setResume(applicationView.getResume());
        return applicationView;
    }

    public void acceptApplication(Long id) throws Exception {
        Application application = findApplicationById(id);
        application.setApplicationStatus(ApplicationStatus.ACCEPTED);
        applicationRepository.save(application);
        String textOfAccepted = "Hello, nice news for you\nYour application to the vacancy " + application.getVacancy().getTitle() + " of the company " + application.getVacancy().getCompany().getName() + " was accepted and HR is ready to call you on an interview\n\nSincerely, Job-Seeker team";
        String accepted = "You application was accepted";
        emailService.sendEmail(application.getEmail(), accepted,textOfAccepted);
        log.info("Application with {} was accepted",id);
    }

    public void rejectApplication(Long id) throws Exception {
        Application application = findApplicationById(id);
        application.setApplicationStatus(ApplicationStatus.REJECTED);
        applicationRepository.save(application);
        String textOfRejected = "Hello, some updates\nUnfortunately\nYour application to the vacancy " + application.getVacancy().getTitle() + " of the company" + application.getVacancy().getCompany().getName() + " was rejected.But your resume was added to the archive, so we can contact you later\n\nSincerely, Job-Seeker team";
        String rejection = "You application was rejected";
        emailService.sendEmail(application.getEmail(), rejection,textOfRejected);
        log.info("Application with {} was rejected",id);
    }


}
