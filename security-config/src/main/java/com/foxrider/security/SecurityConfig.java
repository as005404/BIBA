package com.foxrider.security;

import com.foxrider.security.auth.ApplicationUserService;
import com.foxrider.security.jwt.JwtConfig;
import com.foxrider.security.jwt.JwtSecretKey;
import com.foxrider.security.jwt.JwtTokenVerifier;
import com.foxrider.security.jwt.JwtUsernameAndPasswordAuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.foxrider.security.roles.Roles.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApplicationUserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig config;
    private final JwtSecretKey secretKey;


    @Autowired
    public SecurityConfig(ApplicationUserService userService, PasswordEncoder passwordEncoder, JwtConfig config, JwtSecretKey secretKey) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.config = config;
        this.secretKey = secretKey;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationUser(authenticationManager(), config, secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey), JwtUsernameAndPasswordAuthenticationUser.class)
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*", "/swagger-ui/**", "/register", "/api-docs/**").permitAll()
                .antMatchers(HttpMethod.GET, "/values/**").hasAnyRole(USER.name(), MODER.name(), ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/values/**").hasAnyRole(USER.name(), MODER.name(), ADMIN.name())
                .antMatchers(HttpMethod.POST, "/values/**").hasAnyRole(USER.name(), MODER.name(), ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/values/**").hasAnyRole(USER.name(), MODER.name(), ADMIN.name())

                .antMatchers(HttpMethod.GET, "/shifts/**").hasAnyRole(USER.name(), MODER.name(), ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/shifts/**").hasAnyRole(MODER.name(), ADMIN.name())
                .antMatchers(HttpMethod.POST, "/shifts/**").hasAnyRole(MODER.name(), ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/shifts/**").hasAnyRole(MODER.name(), ADMIN.name())

                .antMatchers(HttpMethod.GET, "/persons/**").hasAnyRole(ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/persons/**").hasAnyRole(ADMIN.name())
                .antMatchers(HttpMethod.POST, "/persons/**").hasAnyRole(ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/persons/**").hasAnyRole(ADMIN.name())

                .antMatchers(HttpMethod.GET, "/sensors/**").hasAnyRole(USER.name(), MODER.name(), ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/sensors/**").hasAnyRole(MODER.name(), ADMIN.name())
                .antMatchers(HttpMethod.POST, "/sensors/**").hasAnyRole(MODER.name(), ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/sensors/**").hasAnyRole(MODER.name(), ADMIN.name())

                .antMatchers(HttpMethod.GET, "/accesses/**").hasAnyRole(ADMIN.name())
                .antMatchers(HttpMethod.DELETE, "/accesses/**").hasAnyRole(ADMIN.name())
                .antMatchers(HttpMethod.POST, "/accesses/**").hasAnyRole(ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/accesses/**").hasAnyRole(ADMIN.name())

                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public GrantedAuthoritiesMapper authoritiesMapper() {
        SimpleAuthorityMapper mapper = new SimpleAuthorityMapper();
        mapper.setConvertToUpperCase(true);
//        mapper.setDefaultAuthority("ROLE_USER");
        return mapper;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        provider.setAuthoritiesMapper(authoritiesMapper());
        return provider;
    }
}
