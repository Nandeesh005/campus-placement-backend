package com.jobportal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    // ==========================================
    // BASIC DETAILS
    // ==========================================
    private String fullName;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    // ==========================================
    // STUDENT DETAILS
    // ==========================================
    private String phone;

    private String usn;

    private String department;

    private Double cgpa;

    private String skills;

    private Integer graduationYear;

    // ==========================================
    // GETTERS & SETTERS
    // ==========================================

    public Long getId() {
        return id;
    }

    // FULL NAME
    public String getFullName() {
        return fullName;
    }

    public void setFullName(
            String fullName
    ) {
        this.fullName = fullName;
    }

    // EMAIL
    public String getEmail() {
        return email;
    }

    public void setEmail(
            String email
    ) {
        this.email = email;
    }

    // PASSWORD
    public String getPassword() {
        return password;
    }

    public void setPassword(
            String password
    ) {
        this.password = password;
    }

    // ROLE
    public Role getRole() {
        return role;
    }

    public void setRole(
            Role role
    ) {
        this.role = role;
    }

    // PHONE
    public String getPhone() {
        return phone;
    }

    public void setPhone(
            String phone
    ) {
        this.phone = phone;
    }

    // USN
    public String getUsn() {
        return usn;
    }

    public void setUsn(
            String usn
    ) {
        this.usn = usn;
    }

    // DEPARTMENT
    public String getDepartment() {
        return department;
    }

    public void setDepartment(
            String department
    ) {
        this.department = department;
    }

    // CGPA
    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(
            Double cgpa
    ) {
        this.cgpa = cgpa;
    }

    // SKILLS
    public String getSkills() {
        return skills;
    }

    public void setSkills(
            String skills
    ) {
        this.skills = skills;
    }

    // GRADUATION YEAR
    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(
            Integer graduationYear
    ) {
        this.graduationYear = graduationYear;
    }
}