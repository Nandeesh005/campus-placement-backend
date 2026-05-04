package com.jobportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    // ==========================================
    // JWT FILTER
    // ==========================================
    private final JwtFilter jwtFilter;

    public SecurityConfig(
            JwtFilter jwtFilter
    ) {

        this.jwtFilter =
                jwtFilter;
    }

    // ==========================================
    // AUTH MANAGER
    // ==========================================
    @Bean
    public AuthenticationManager
    authenticationManager(

            AuthenticationConfiguration config

    ) throws Exception {

        return config
                .getAuthenticationManager();
    }

    // ==========================================
    // SECURITY FILTER CHAIN
    // ==========================================
    @Bean
    public SecurityFilterChain
    securityFilterChain(

            HttpSecurity http

    ) throws Exception {

        http

            // ==========================================
            // CSRF
            // ==========================================
            .csrf(csrf ->
                    csrf.disable()
            )

            // ==========================================
            // CORS
            // ==========================================
            .cors(cors -> {
            })

            // ==========================================
            // SESSION
            // ==========================================
            .sessionManagement(session ->

                    session.sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS
                    )
            )

            // ==========================================
            // AUTHORIZATION
            // ==========================================
            .authorizeHttpRequests(auth ->

                    auth

                    // ==========================================
                    // PUBLIC ENDPOINTS
                    // ==========================================
                    .requestMatchers(

                            "/users",

                            "/users/login",

                            "/users/forgot-password",

                            "/users/verify-otp",

                            "/users/reset-password",

                            "/files/**"

                    ).permitAll()

                    // ==========================================
                    // JOBS
                    // ==========================================
                    .requestMatchers(
                            HttpMethod.GET,
                            "/jobs"
                    ).permitAll()

                    // ==========================================
                    // APPLICATIONS
                    // ==========================================
                    .requestMatchers(
                            "/applications"
                    ).permitAll()

                    // ==========================================
                    // ADMIN
                    // ==========================================
                    .requestMatchers(
                            "/admin/**"
                    ).hasRole("ADMIN")

                    // ==========================================
                    // EVERYTHING ELSE
                    // ==========================================
                    .anyRequest()
                    .authenticated()
            )

            // ==========================================
            // JWT FILTER
            // ==========================================
            .addFilterBefore(

                    jwtFilter,

                    UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}