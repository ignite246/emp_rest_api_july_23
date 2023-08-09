package com.rahul.projects.EmployeeRESTAPI.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    UserDetailsService userDetailsService() {
        final InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();

        final UserDetails user = User.withUsername("rahul")
                .password(bCryptPasswordEncoder().encode("rahul"))
                .roles("USER").build();
        final UserDetails admin = User.withUsername("admin")
                .password(bCryptPasswordEncoder().encode("admin"))
                .roles("ADMIN").build();

        userDetailsService.createUser(user);
        userDetailsService.createUser(admin);

        return userDetailsService;
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();
        httpSecurity.authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/employee-api/employees/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/employee-api/employees").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/employee-api/employees/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/actuator/**").hasAnyRole("USER", "ADMIN")

                .requestMatchers(HttpMethod.POST, "/test-sensitive/news").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/test-sensitive/news").hasAnyRole("USER", "ADMIN");


        httpSecurity.csrf().disable();

        return httpSecurity.build();
    }
}
