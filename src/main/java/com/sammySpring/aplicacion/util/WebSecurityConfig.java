package com.sammySpring.aplicacion.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails usuario1 = User
                .withUsername("VISADOR")
                .password("$2a$10$21k9QLX0vdSytZ36T56yHeDwn2y6DiHdJ2k7fCzccV7CuskmNQmEq")
                .roles("VISADOR")
                .build();

        UserDetails usuario2 = User
                .withUsername("admin")
                .password("$2a$10$21k9QLX0vdSytZ36T56yHeDwn2y6DiHdJ2k7fCzccV7CuskmNQmEq")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(usuario1, usuario2);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests(requests -> requests
            .antMatchers("/").permitAll()
            .antMatchers("/userForm/**").hasAnyRole("VISADOR", "ADMIN")
            .antMatchers("/admin/**").hasRole("ADMIN") // Agregado para restringir a roles específicos
            .anyRequest().authenticated())
            .formLogin(login -> login
                    .loginPage("/login")
                    .defaultSuccessUrl("/userForm")
                    .permitAll())
            .logout(logout -> logout
                    .permitAll()
                    .logoutSuccessUrl("/"))  // Redirige a la página de inicio de sesión después del logout exitoso
            .csrf(csrf -> csrf.disable());
        }

}

