package io.github.eryckavel.vendas.config;

import io.github.eryckavel.vendas.config.jwt.JwtAuthFilter;
import io.github.eryckavel.vendas.config.jwt.JwtService;
import io.github.eryckavel.vendas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    JwtService jwtService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(usuarioService, jwtService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                /*
                    .antMatchers(HttpMethod.GET,"/clientes")
                        .hasAnyRole("USER")
                    .antMatchers(HttpMethod.GET,"/pedidos")
                        .hasRole("ADMIN")
                    .antMatchers(HttpMethod.GET,"/produtos")
                        .hasAnyRole("ADMIN","USER")
                    .antMatchers(HttpMethod.GET,"/usuarios")
                        .hasRole("ADMIN")
                        */
                .anyRequest().permitAll()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());
    }
}
