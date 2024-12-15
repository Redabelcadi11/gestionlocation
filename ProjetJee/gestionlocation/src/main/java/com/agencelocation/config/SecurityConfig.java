package com.agencelocation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Attention : NoOpPasswordEncoder ne doit pas être utilisé en production
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/register", "/login") // Désactive CSRF pour ces URLs si besoin
                )
                .authorizeHttpRequests(auth -> auth
                        // URLs publiques accessibles sans authentification
                        .requestMatchers("/", "/home", "/admin", "/register", "/login", "/vehicules", "/vehicules/**").permitAll()
                        // Accès spécifique pour l'admin
                        .requestMatchers("/admin").hasRole("ADMIN")
                        // Toutes les autres requêtes nécessitent une authentification
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // Page personnalisée pour le login
                        .defaultSuccessUrl("/home") // Redirige après un login réussi
                        .permitAll()
                )
                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedPage("/home") // Redirection en cas d'accès refusé
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/") // URL de redirection après déconnexion
                        .permitAll()
                );

        return http.build();
    }
}