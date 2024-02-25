package com.rest.blog.config;

import com.rest.blog.security.CustomUserDetailService;
import com.rest.blog.security.JwtAuthenticationEntryPoint;
import com.rest.blog.security.JwtAuthenticationFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled =true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailService customUserDetailService;
	@Autowired
	private JwtAuthenticationEntryPoint JwtAuthenticationEntryPoint;
	@Autowired
	private JwtAuthenticationFilter JwtAuthenticationFilter;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	  http
       .csrf()
       .disable()
       .authorizeRequests()
       .antMatchers("/api/v1/auth/login").permitAll()
       .antMatchers(HttpMethod.GET).permitAll()
       .anyRequest()
       .authenticated()
       .and()
       .exceptionHandling().authenticationEntryPoint(this.JwtAuthenticationEntryPoint)
       .and()
       .sessionManagement()
       .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       
       http.addFilterBefore(this.JwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean()throws Exception{
		return super.authenticationManagerBean();
	}
	
  
}
