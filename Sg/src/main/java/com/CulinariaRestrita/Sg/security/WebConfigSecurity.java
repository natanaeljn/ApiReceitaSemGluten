package com.CulinariaRestrita.Sg.security;



import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class WebConfigSecurity  implements WebMvcConfigurer{
	
	

	@Autowired
	private FilterToken filter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.POST, "auth/login").permitAll()
						.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
						.requestMatchers(HttpMethod.POST , "/auth").permitAll()
						.requestMatchers(HttpMethod.POST , "auth").permitAll()
						.requestMatchers(HttpMethod.POST , "http://localhost:8080/auth/login").permitAll()
						.requestMatchers(HttpMethod.POST, "auth/register").permitAll()
						.requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/**",
								"/swagger-resources", "/v3/api-docs/**", "/proxy/**")
						.permitAll()
						
						.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
						
						.anyRequest().authenticated())
				      
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				
				.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("*"));
	    configuration.setAllowedMethods(Arrays.asList("*"));
	    configuration.setAllowedHeaders(Arrays.asList("*"));
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}

	/* para permitir o acesso ao swagger */
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/v2/api-docs/**", "/swagger-ui/**", "/swagger-resources/**",
				"/v2/api-docs/**");
	}
	
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
		
	}
	
	


}