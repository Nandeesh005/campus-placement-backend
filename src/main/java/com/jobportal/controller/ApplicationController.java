package com.jobportal.controller;

import com.jobportal.model.Application;

import com.jobportal.service.ApplicationService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // ==========================================
    // CREATE APPLICATION
    // ==========================================
    @PostMapping
    public Application createApplication(
            @RequestBody Application application
    ) {

        return applicationService
                .createApplication(application);
    }

    // ==========================================
    // GET ALL APPLICATIONS
    // ==========================================
    @GetMapping
    public List<Application> getAllApplications() {

        return applicationService
                .getAllApplications();
    }

    // ==========================================
    // GET USER APPLICATIONS
    // ==========================================
    @GetMapping("/user/{email}")
    public List<Application> getApplicationsByEmail(
            @PathVariable String email
    ) {

        return applicationService
                .getApplicationsByEmail(email);
    }
}