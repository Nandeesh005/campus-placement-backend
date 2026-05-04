package com.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // ==========================================
    // SEND OTP EMAIL
    // ==========================================
    public void sendOtpEmail(
            String toEmail,
            String otp
    ) {

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(toEmail);

        message.setSubject(
                "Job Portal Password Reset OTP"
        );

        message.setText(
                "Your OTP is: " + otp
        );

        mailSender.send(message);
    }
}