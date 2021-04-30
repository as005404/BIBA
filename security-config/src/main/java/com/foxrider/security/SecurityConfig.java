package com.foxrider.security;

import com.foxrider.security.auth.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.foxrider.security.roles.Roles.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApplicationUserService userService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public SecurityConfig(ApplicationUserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*", "/swagger-ui.html/**", "/register").permitAll()
                .antMatchers(HttpMethod.GET, "/values/**").hasAnyRole(USER.name(), MODER.name(), ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/values/**").hasAnyRole(USER.name(), MODER.name(), ADMIN.name())
                .antMatchers(HttpMethod.POST, "/values/**").hasAnyRole(USER.name(), MODER.name(), ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/values/**").hasAnyRole(USER.name(), MODER.name(), ADMIN.name())

                .antMatchers(HttpMethod.GET, "/shifts/**").hasAnyRole(MODER.name(), ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/shifts/**").hasAnyRole(MODER.name(), ADMIN.name())
                .antMatchers(HttpMethod.POST, "/shifts/**").hasAnyRole(MODER.name(), ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/shifts/**").hasAnyRole(MODER.name(), ADMIN.name())

                .anyRequest().authenticated()
                .and()
                .formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}
