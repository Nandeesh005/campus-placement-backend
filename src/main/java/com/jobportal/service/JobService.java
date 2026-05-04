package com.jobportal.service;

import com.jobportal.model.Job;
import com.jobportal.repository.JobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    // ==========================================
    // CREATE JOB
    // ==========================================
    public Job createJob(Job job) {

        return jobRepository.save(job);
    }

    // ==========================================
    // GET ALL JOBS
    // ==========================================
    public List<Job> getAllJobs() {

        return jobRepository.findAll();
    }

    // ==========================================
    // DELETE JOB
    // ==========================================
    public void deleteJob(Long id) {

        jobRepository.deleteById(id);
    }

    // ==========================================
    // UPDATE JOB
    // ==========================================
    public Job updateJob(Long id, Job updatedJob) {

        Job existingJob = jobRepository
                .findById(id)
                .orElseThrow();

        existingJob.setTitle(updatedJob.getTitle());

        existingJob.setCompany(updatedJob.getCompany());

        existingJob.setLocation(updatedJob.getLocation());

        existingJob.setSalary(updatedJob.getSalary());

        return jobRepository.save(existingJob);
    }
}