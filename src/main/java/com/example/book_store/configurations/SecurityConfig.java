package com.example.book_store.configurations;

import com.example.book_store.entities.UserEntity;
import com.example.book_store.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder regPasswordencoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            UserEntity user = userRepo.findByUsername(username);
            if (user != null) return user;
            throw new UsernameNotFoundException("User ‘" + username + "’ not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/employee/**").hasRole("EMPLOYEE")
                .requestMatchers("/client/**").hasRole("CLIENT")
                .requestMatchers("/**").permitAll().anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").permitAll())
                .csrf(csrf -> csrf.disable())
                .logout(Customizer.withDefaults())
                .build();
    }
}