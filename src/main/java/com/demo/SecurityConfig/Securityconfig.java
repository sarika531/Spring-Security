package com.demo.SecurityConfig;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Securityconfig {

	
	//security filter chain
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
		.csrf().disable()
		.authorizeRequests()
		  .requestMatchers("/auth/register", "/auth/login").permitAll() 
		.anyRequest()
		.authenticated();
		
		return http.build();
	}
	
	//authencation mananer
	
	@Bean
   public AuthenticationManager authenticationManager(
		   AuthenticationConfiguration authenticationConfiguration
		   )throws Exception{
	   return authenticationConfiguration.getAuthenticationManager();
	   
   }
	

}