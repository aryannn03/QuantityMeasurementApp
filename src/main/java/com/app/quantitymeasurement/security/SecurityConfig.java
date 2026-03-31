package com.app.quantitymeasurement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

@Autowired
private JwtFilter jwtFilter;

@Autowired
private OAuthSuccessHandler successHandler;

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .csrf(csrf -> csrf.disable())

        // Stateless (no session, only JWT)
        .sessionManagement(session -> 
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )

        // Return 401 instead of redirect
        .exceptionHandling(ex -> ex
            .authenticationEntryPoint((req, res, excep) -> {
                res.setStatus(401);
                res.getWriter().write("Unauthorized");
            })
        )

        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/**", "/oauth2/**").permitAll()
            .anyRequest().authenticated()
        )

        .oauth2Login(oauth -> oauth
            .successHandler(successHandler)
        );

    // JWT filter
    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
}


}
