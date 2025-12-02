package com.example.springsecurityoauthendcodingbug.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers.hasScope;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    static final String VALID_SCOPE = "PUT:example";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorize ->
                                authorize.requestMatchers(HttpMethod.PUT, ExampleController.URL)
                                        .access(hasScope(VALID_SCOPE))
                                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .opaqueToken(Customizer.withDefaults())
                );
        return http.build();
    }
}
