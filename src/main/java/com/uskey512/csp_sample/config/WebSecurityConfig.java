package com.uskey512.csp_sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        var cspDirectives = List.of(
            "img-src 'self'",
            "script-src 'self'",
            "style-src 'self'",
            "font-src 'self'",
            "connect-src 'self'",
            "frame-src 'self'",
            "media-src 'self'"
        );
        http
            .headers(headers -> headers
                .contentSecurityPolicy(csp -> csp
                    .policyDirectives(String.join("; ", cspDirectives))
                )
            );
        return http.build();
    }
}
