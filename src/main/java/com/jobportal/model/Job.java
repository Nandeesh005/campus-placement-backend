package com.jobportal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String company;
    private String location;
    private double salary;

    // Getters & Setters
    public Long getId() { return id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getCompany() { return company; }

    public void setCompany(String company) { this.company = company; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public double getSalary() { return salary; }

    public void setSalary(double salary) { this.salary = salary; }
}