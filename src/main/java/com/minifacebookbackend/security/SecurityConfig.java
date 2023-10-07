package com.minifacebookbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(request -> request.requestMatchers("/auth/login/**").permitAll())
                .authorizeHttpRequests(request -> request.requestMatchers("/auth/register/**").permitAll())
                .authorizeHttpRequests(request -> request.requestMatchers("/auth/validateToken/**").permitAll())
                .authorizeHttpRequests(request -> request.anyRequest().authenticated());
        httpSecurity
                .oauth2ResourceServer(auth -> auth.jwt(Customizer.withDefaults()));
        return httpSecurity.build();
    }
    @Bean
    public SessionAuthenticationStrategy sessionAuthenticationStrategy(){
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
