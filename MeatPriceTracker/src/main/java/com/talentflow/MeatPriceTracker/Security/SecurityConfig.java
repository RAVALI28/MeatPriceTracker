package com.talentflow.MeatPriceTracker.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions().disable())
                        .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/h2-console/**").permitAll()
                                .anyRequest().permitAll());

                return httpSecurity.build();

    }
}
