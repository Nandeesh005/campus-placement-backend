package com.jobportal.service;

import com.jobportal.model.Role;
import com.jobportal.model.User;
import com.jobportal.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ==========================================
    // OTP STORAGE
    // ==========================================
    private Map<String, String> otpStorage =
            new HashMap<>();

    // ==========================================
    // REGISTER USER
    // ==========================================
    public User registerUser(User user) {

        // FORCE EVERY NEW USER AS USER ROLE
        user.setRole(Role.USER);

        // ENCODE PASSWORD
        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()
                )
        );

        return userRepository.save(user);
    }

    // ==========================================
    // LOGIN USER
    // ==========================================
    public User loginUser(
            String email,
            String password
    ) {

        Optional<User> optionalUser =
                userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {

            User user =
                    optionalUser.get();

            boolean isPasswordCorrect =
                    passwordEncoder.matches(
                            password,
                            user.getPassword()
                    );

            if (isPasswordCorrect) {

                return user;
            }
        }

        return null;
    }

    // ==========================================
    // GENERATE OTP
    // ==========================================
    public String generateOtp(
            String email
    ) {

        Random random =
                new Random();

        int otpNumber =
                100000 +
                random.nextInt(900000);

        String otp =
                String.valueOf(
                        otpNumber
                );

        otpStorage.put(
                email,
                otp
        );

        return otp;
    }

    // ==========================================
    // VERIFY OTP
    // ==========================================
    public boolean verifyOtp(
            String email,
            String otp
    ) {

        String storedOtp =
                otpStorage.get(email);

        return storedOtp != null &&
                storedOtp.equals(otp);
    }

    // ==========================================
    // RESET PASSWORD
    // ==========================================
    public boolean resetPassword(
            String email,
            String newPassword
    ) {

        Optional<User> optionalUser =
                userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {

            User user =
                    optionalUser.get();

            user.setPassword(
                    passwordEncoder.encode(
                            newPassword
                    )
            );

            userRepository.save(user);

            return true;
        }

        return false;
    }

    // ==========================================
    // UPDATE USER PROFILE
    // ==========================================
    public User updateUserProfile(
            String email,
            User updatedUser
    ) {

        User existingUser =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );

        // ==========================================
        // UPDATE FIELDS
        // ==========================================

        // FULL NAME
        existingUser.setFullName(
                updatedUser.getFullName()
        );

        // EMAIL
        existingUser.setEmail(
                updatedUser.getEmail()
        );

        // PASSWORD
        if (
                updatedUser.getPassword() != null &&
                !updatedUser.getPassword().isEmpty()
        ) {

            existingUser.setPassword(
                    passwordEncoder.encode(
                            updatedUser.getPassword()
                    )
            );
        }

        // PHONE
        existingUser.setPhone(
                updatedUser.getPhone()
        );

        // USN
        existingUser.setUsn(
                updatedUser.getUsn()
        );

        // DEPARTMENT
        existingUser.setDepartment(
                updatedUser.getDepartment()
        );

        // CGPA
        existingUser.setCgpa(
                updatedUser.getCgpa()
        );

        // SKILLS
        existingUser.setSkills(
                updatedUser.getSkills()
        );

        // GRADUATION YEAR
        existingUser.setGraduationYear(
                updatedUser.getGraduationYear()
        );

        return userRepository.save(
                existingUser
        );
    }
}