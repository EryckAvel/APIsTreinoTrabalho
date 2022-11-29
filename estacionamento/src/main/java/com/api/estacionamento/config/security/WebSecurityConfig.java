package com.api.estacionamento.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class WebSecurityConfig{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
                .httpBasic()
                .and()
                .authorizeHttpRequests((authorize) -> authorize
                        //.requestMatchers(HttpMethod.GET,"/estacionamento").permitAll()//Limitação a um endpoint/metodo especifico
                        .anyRequest().authenticated()//limitação publica a todos os endpoints e metodos
                )
                .csrf().disable();

        return http.build();
    }

    @Bean
    public UserDetailsService user(){
        UserDetails user = User.builder()
                .username("eryck")
                .password(passwordEncoder().encode("3003"))
                .roles("user")
                .build();
        return new InMemoryUserDetailsManager(user);

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
