package com.jobportal.service;

import com.jobportal.model.Application;

import com.jobportal.repository.ApplicationRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    // ==========================================
    // CREATE APPLICATION
    // ==========================================
    public Application createApplication(
            Application application
    ) {

        return applicationRepository.save(
                application
        );
    }

    // ==========================================
    // GET ALL APPLICATIONS
    // ==========================================
    public List<Application> getAllApplications() {

        return applicationRepository.findAll();
    }

    // ==========================================
    // GET USER APPLICATIONS
    // ==========================================
    public List<Application> getApplicationsByEmail(
            String email
    ) {

        return applicationRepository
                .findByApplicantEmail(email);
    }
}