package com.app.qma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.*;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
         

           
            .csrf(csrf -> csrf.disable())

        
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

     
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint((req, res, excep) -> {
                    res.setStatus(401);
                    res.getWriter().write("Unauthorized");
                })
            )

            
            .authorizeHttpRequests(auth -> auth

           
                .requestMatchers(
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-ui.html",

                  
                    "/api/v1/quantities/compare",
                    "/api/v1/quantities/add",
                    "/api/v1/quantities/subtract",
                    "/api/v1/quantities/divide",
                    "/api/v1/quantities/convert"
                ).permitAll()

         
                .requestMatchers(
                    "/api/v1/quantities/history/**",
                    "/api/v1/quantities/count/**"
                ).authenticated()

      
                .anyRequest().authenticated()
            );

         

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of(
        	    "http://localhost:4200",
        	    "http://127.0.0.1:4200",
        	    "http://localhost:5500",
        	    "http://127.0.0.1:5500",
        	    "https://quantifyhub.vercel.app"
        	));

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}