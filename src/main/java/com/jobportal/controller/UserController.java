package com.jobportal.controller;

import com.jobportal.config.JwtUtil;

import com.jobportal.model.User;

import com.jobportal.service.UserService;

import com.jobportal.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import java.util.Map;

@RestController

@RequestMapping("/users")

@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmailService emailService;

    // ==========================================
    // REGISTER
    // ==========================================
    @PostMapping
    public User registerUser(

            @RequestBody
            User user

    ) {

        return userService
                .registerUser(user);
    }

    // ==========================================
    // LOGIN
    // ==========================================
    @PostMapping("/login")
    public Map<String, Object> loginUser(

            @RequestBody
            User user

    ) {

        User existingUser =

                userService.loginUser(

                        user.getEmail(),

                        user.getPassword()
                );

        if (existingUser == null) {

            throw new RuntimeException(
                    "Invalid credentials"
            );
        }

        // ==========================================
        // GENERATE JWT TOKEN
        // ==========================================
        String token =

                jwtUtil.generateToken(

                        existingUser.getEmail(),

                        existingUser
                                .getRole()
                                .toString()
                );

        // ==========================================
        // RESPONSE
        // ==========================================
        Map<String, Object> response =

                new HashMap<>();

        // JWT
        response.put(
                "token",
                token
        );

        // BASIC
        response.put(
                "role",
                existingUser.getRole()
        );

        response.put(
                "email",
                existingUser.getEmail()
        );

        response.put(
                "fullName",
                existingUser.getFullName()
        );

        // ==========================================
        // STUDENT DETAILS
        // ==========================================
        response.put(
                "usn",
                existingUser.getUsn()
        );

        response.put(
                "department",
                existingUser.getDepartment()
        );

        response.put(
                "cgpa",
                existingUser.getCgpa()
        );

        response.put(
                "skills",
                existingUser.getSkills()
        );

        response.put(
                "graduationYear",
                existingUser.getGraduationYear()
        );

        return response;
    }

    // ==========================================
    // FORGOT PASSWORD
    // ==========================================
    @PostMapping("/forgot-password")
    public Map<String, String> forgotPassword(

            @RequestParam
            String email

    ) {

        String otp =

                userService.generateOtp(
                        email
                );

        // SEND EMAIL
        emailService.sendOtpEmail(

                email,

                otp
        );

        Map<String, String> response =

                new HashMap<>();

        response.put(

                "message",

                "OTP sent to email"
        );

        return response;
    }

    // ==========================================
    // VERIFY OTP
    // ==========================================
    @PostMapping("/verify-otp")
    public Map<String, String> verifyOtp(

            @RequestParam
            String email,

            @RequestParam
            String otp

    ) {

        boolean isValid =

                userService.verifyOtp(
                        email,
                        otp
                );

        Map<String, String> response =

                new HashMap<>();

        if (isValid) {

            response.put(

                    "message",

                    "OTP verified"
            );

        } else {

            response.put(

                    "message",

                    "Invalid OTP"
            );
        }

        return response;
    }

    // ==========================================
    // RESET PASSWORD
    // ==========================================
    @PostMapping("/reset-password")
    public Map<String, String> resetPassword(

            @RequestParam
            String email,

            @RequestParam
            String newPassword

    ) {

        boolean success =

                userService.resetPassword(

                        email,

                        newPassword
                );

        Map<String, String> response =

                new HashMap<>();

        if (success) {

            response.put(

                    "message",

                    "Password reset successful"
            );

        } else {

            response.put(

                    "message",

                    "User not found"
            );
        }

        return response;
    }

    // ==========================================
    // UPDATE PROFILE
    // ==========================================
    @PutMapping("/update/{email}")
    public User updateProfile(

            @PathVariable
            String email,

            @RequestBody
            User updatedUser

    ) {

        return userService.updateUserProfile(

                email,

                updatedUser
        );
    }
}