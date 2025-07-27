package com.example.Weather_Api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //dont go default flow
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults()) // Enable CORS
                .csrf(AbstractHttpConfigurer::disable) // disable csrf token
                .authorizeHttpRequests(request -> request
                        .requestMatchers("register", "login")
                        .permitAll()
                        .anyRequest().authenticated()) // any request authenticated
                .httpBasic(Customizer.withDefaults()) // for the postman
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build(); // changing http is stateless
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails user1= User
//                .withDefaultPasswordEncoder()
//                .username("tharindu")
//                .password("thari123")
//                .roles("USER")
//                .build();
//
//        UserDetails user2= User
//                .withDefaultPasswordEncoder()
//                .username("Amal")
//                .password("amal123")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1,user2);
//        }

        @Bean
        public AuthenticationProvider authenticationProvider(){
            DaoAuthenticationProvider provider =new DaoAuthenticationProvider();//database authenticated provider
            provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
            provider.setUserDetailsService(userDetailsService);
            return provider;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
            return configuration.getAuthenticationManager();

        }

    }


