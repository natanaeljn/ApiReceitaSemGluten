package com.CulinariaRestrita.Sg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
@CrossOrigin
public class SgApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(SgApplication.class, args);
	}
	
	

}
