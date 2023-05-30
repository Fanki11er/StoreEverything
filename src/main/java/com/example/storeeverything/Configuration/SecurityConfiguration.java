package com.example.storeeverything.Configuration;

import com.example.storeeverything.Services.UserService;
import com.example.storeeverything.Services.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {


    @Autowired
    private CustomSuccessHandler successHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        http.authorizeHttpRequests().requestMatchers(
                "/Auth/**",
                "/",
                "/js/**",
                "/css/**",
                "/img/**"
        ).permitAll().requestMatchers("/SharedLinks/**").anonymous().requestMatchers("/App/Items**").hasAnyAuthority("FULL", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/Auth/Login").usernameParameter("login").successHandler(successHandler)
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/Auth/Logout"))
                .logoutSuccessUrl("/Auth/Login?Logout")
                .permitAll();

        return http.build();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

}
