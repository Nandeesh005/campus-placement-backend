package com.jobportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF (IMPORTANT for APIs)
            .csrf(csrf -> csrf.disable())

            // Enable CORS
            .cors(cors -> {})

            // Authorization rules
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/api/auth/**",   // allow login & register
                        "/error"
                ).permitAll()
                .anyRequest().authenticated()
            );

        return http.build();
    }

}