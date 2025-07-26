package com.example.Weather_Api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //dont go default flow
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable) //disable csrf token
                .authorizeHttpRequests(request-> request.anyRequest().authenticated())//any request authenticated
                .httpBasic(Customizer.withDefaults()) //for the postman
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build(); //changing http is stateless

        //http.formLogin(Customizer.withDefaults()) //Display default form


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
            provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); //not use password encoder
            provider.setUserDetailsService(userDetailsService);
            return provider;
        }

    }


