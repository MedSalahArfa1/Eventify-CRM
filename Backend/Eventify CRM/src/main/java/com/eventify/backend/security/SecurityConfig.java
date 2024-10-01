package com.eventify.backend.security;

import com.eventify.backend.entities.Rolename;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtAuthEntryPoint authEntryPoint;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtAuthEntryPoint authEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.authEntryPoint = authEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors() // Enable CORS
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/roles/**").permitAll()
                .antMatchers("/api/users/**").permitAll()
                //.antMatchers(HttpMethod.GET, "/api/events/**").permitAll()
                //.antMatchers(HttpMethod.POST, "/api/events/**").hasAuthority(Rolename.EVENTMANAGER.name())
                //.antMatchers(HttpMethod.POST, "/api/image/**").hasAuthority(Rolename.EVENTMANAGER.name())
                .antMatchers("/api/tasks/**").permitAll()
                .antMatchers("/api/events/**").permitAll()
                .antMatchers("/api/vendors/**").permitAll()
                .antMatchers("/api/feedbacks/**").permitAll()
                .antMatchers("/image/**").permitAll()
             //   .antMatchers("/post/**").hasAuthority(UserRoleName.ADMIN.name())
              //  .antMatchers("/image/**").hasAnyAuthority(UserRoleName.USER.name(),UserRoleName.ADMIN.name())


                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean // Expose AuthenticationManager as a Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }
}
