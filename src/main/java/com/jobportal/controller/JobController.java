package com.jobportal.controller;

import com.jobportal.model.Job;
import com.jobportal.service.JobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    // ==========================================
    // CREATE JOB
    // ==========================================
    @PostMapping
    public Job createJob(@RequestBody Job job) {

        return jobService.createJob(job);
    }

    // ==========================================
    // GET ALL JOBS
    // ==========================================
    @GetMapping
    public List<Job> getAllJobs() {

        return jobService.getAllJobs();
    }

    // ==========================================
    // DELETE JOB
    // ==========================================
    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {

        jobService.deleteJob(id);
    }

    // ==========================================
    // UPDATE JOB
    // ==========================================
    @PutMapping("/{id}")
    public Job updateJob(
            @PathVariable Long id,
            @RequestBody Job updatedJob
    ) {

        return jobService.updateJob(id, updatedJob);
    }
}