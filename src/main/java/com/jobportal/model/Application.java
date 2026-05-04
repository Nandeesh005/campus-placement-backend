package com.jobportal.model;

import jakarta.persistence.*;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String applicantName;

    private String applicantEmail;

    private String resumeLink;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    // GETTERS & SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public String getResumeLink() {
        return resumeLink;
    }

    public void setResumeLink(String resumeLink) {
        this.resumeLink = resumeLink;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}