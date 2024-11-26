package com.agencelocation.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.agencelocation.config.CustomUserDetailsService;
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
        return NoOpPasswordEncoder.getInstance(); // Ne fait aucune transformation sur le mot de passe
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Configuration de la sécurité
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/register", "/login")
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home", "/register", "/login").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home")
                        .permitAll()
                )
                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedPage("/home") // Si l'utilisateur n'a pas accès à /admin, il est redirigé vers /home
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll() // Permet à tout le monde d'effectuer une déconnexion
                );

        return http.build();
    }
}


