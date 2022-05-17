package com.example.cookncalc.security.config;

import com.example.cookncalc.security.config.jwt.JWTFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JWTFilter jwtFilter;

    @Value("${jwt-cookie-name}")
    private String jwtCookieName;

    public WebSecurityConfiguration(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/api/auth/register", "/api/auth/login").permitAll()
                .antMatchers("/api", "/api/recipe/{id}", "/api/recipe").permitAll()
                .antMatchers("/api/auth/**").authenticated()
                .antMatchers("/api/users/**").authenticated()
//                .antMatchers("/api/secret/admin").hasRole(SecurityService.ADMIN_ROLE)
                .anyRequest().authenticated();


        http
                .logout()
                .logoutUrl("/api/auth/logout")
                .deleteCookies("JSESSIONID", jwtCookieName)
                .invalidateHttpSession(true)
                .logoutSuccessHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK))
                .permitAll();

        http
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling()
                .authenticationEntryPoint(((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))).and()
                .formLogin().disable();

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }



}