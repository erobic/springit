package com.erobic.springit.security;

import com.erobic.springit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

/**
 * Created by robik on 12/20/16.
 */
@Configuration
@EnableWebSecurity
@Profile(value = {"development", "production", "securedTest"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers("/", "/home", "/users").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/logout")
                .deleteCookies()
                .invalidateHttpSession(true)
                .logoutSuccessHandler(logoutSuccessHandler())
                .and().csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Bean(name = "authenticationEntryPoint")
    public RestAuthenticationEntryPoint authenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean(name = "authenticationSuccessHandler")
    public RestAuthenticationSuccessHandler authenticationSuccessHandler() {
        return new RestAuthenticationSuccessHandler();
    }

    @Bean(name = "authenticationFailureHandler")
    public RestAuthenticationFailureHandler authenticationFailureHandler() {
        return new RestAuthenticationFailureHandler();
    }

    @Bean(name = "logoutSuccessHandler")
    public RestLogoutSuccessHandler logoutSuccessHandler() {
        return new RestLogoutSuccessHandler();
    }

    @Bean(name = "saltSource")
    public ReflectionSaltSource saltSource() {
        ReflectionSaltSource reflectionSaltSource = new ReflectionSaltSource();
        reflectionSaltSource.setUserPropertyToUse("username");
        return reflectionSaltSource;
    }

    @Bean(name = "authenticationManager")
    public AuthenticationManager customAuthenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        //provider.setSaltSource(saltSource());
        provider.setUserDetailsService(userService);
        AuthenticationManager authenticationManager = new ProviderManager(Arrays.asList((AuthenticationProvider) provider));
        return authenticationManager;
    }

    @Bean(name = "authenticationFilter")
    public UsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
        UsernamePasswordAuthenticationFilter authFilter = new RequestBodyAuthenticationFilter();
        authFilter.setAuthenticationManager(customAuthenticationManager());
        authFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        authFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        authFilter.setFilterProcessesUrl("/login");
        authFilter.setUsernameParameter("a1b2c3d4");//We will be using RequestBody and not request params, so keeping parameter name to a non-sensible value.
        authFilter.setPasswordParameter("a1b2c3d4e5");//We will be using RequestBody and not request params, so keeping this field to a non-sensible value.
        //authFilter.setPostOnly(true);//This is handled by RequestBodyAuthenticationFilter itself. So keeping this commented.
        return authFilter;
    }
}
