package org.yechan.userservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
public class WebSecurity {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->{
                    request.requestMatchers("/actuator/**","/h2-console/**").permitAll();
                    request.requestMatchers("/user-service/**").permitAll();
                    request.requestMatchers("/users/**").permitAll();}
                )
                .headers(headers->
                        headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                );
        return http.build();
    }
}
