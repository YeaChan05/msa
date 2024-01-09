package org.yechan.userservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Component;
import org.yechan.userservice.service.AuthenticationFilter;
import org.yechan.userservice.service.UserService;

@Component
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity {
    
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Environment env;
    private final AuthenticationConfiguration authenticationConfiguration;
    IpAddressMatcher hasIpAddress = new IpAddressMatcher("192.168.1.26");
    
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(String.valueOf(PathRequest.toStaticResources().atCommonLocations()));
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/actuator/**","/h2-console/**").permitAll()
                        .requestMatchers("/**").access((authentication, context) -> new AuthorizationDecision(hasIpAddress.matches(context.getRequest())))
                )
                .addFilter(getAuthenticationFilter(authenticationConfiguration))
                .headers(headers-> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }
    
    private AuthenticationFilter getAuthenticationFilter(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return new AuthenticationFilter(authenticationConfiguration.getAuthenticationManager(), userService, env);
    }
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    
}