package com.example.gestiontickets.security;

import com.example.gestiontickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecuriteConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests().mvcMatchers("/", "/login", "/logout", "/inscription").permitAll()
                .mvcMatchers("/dev/mvc/**").hasAuthority("DEV")
                .mvcMatchers("/client/mvc/**").hasAuthority("USER")
                .mvcMatchers("/admin/mvc/**").hasAuthority("ADMIN")
                .mvcMatchers("/dev/**").hasAuthority("DEV")
                .mvcMatchers("/client/**").hasAuthority("USER")
                .mvcMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated().and().formLogin().loginPage("/login").and().logout()
                .clearAuthentication(true).invalidateHttpSession(true).and().csrf().disable();
    }

}