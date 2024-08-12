package com.banurr.pet_project.controller;

import com.banurr.pet_project.dto.ApplicationView;
import com.banurr.pet_project.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/application")
public class ApplicationController
{
    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/{vacancyId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_USER')")
    public void createApplication(@PathVariable(name = "vacancyId") Long id,
                                  @RequestParam("resume") MultipartFile multipartFile) throws IOException
    {
        applicationService.createApplication(id,multipartFile);
    }

    @GetMapping("/{applicationId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public ApplicationView findApplicationById(@PathVariable(name = "applicationId") Long id) throws Exception {

        return applicationService.retrieveApplicationById(id);
    }

    @PutMapping("/accept/{applicationId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HR')")
    public void acceptApplicationById(@PathVariable(name = "applicationId") Long id) throws Exception {
        applicationService.acceptApplication(id);
    }

    @PutMapping("/reject/{applicationId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HR')")
    public void rejectApplicationById(@PathVariable(name = "applicationId") Long id) throws Exception {
        applicationService.rejectApplication(id);
    }
}
