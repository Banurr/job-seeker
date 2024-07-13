package com.banurr.pet_project.controller;

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
}
