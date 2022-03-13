package com.examplesecurity.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;

import java.util.Arrays;

@Configuration
public class CustomAuthenticationManager{
    //delegates authentication to AuthenticationProvider
    private final AuthenticationProvider authenticationProvider;

    public CustomAuthenticationManager(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        return new ProviderManager(Arrays.asList(authenticationProvider));
    }
}
