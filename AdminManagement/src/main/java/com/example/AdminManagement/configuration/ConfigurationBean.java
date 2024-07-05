package com.example.AdminManagement.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ConfigurationBean {

	@Bean
	public WebClient.Builder webClientBuilder(){
		return WebClient.builder();
	}

	@Bean
	public PasswordEncoder passwordcoder() {
		return new BCryptPasswordEncoder();
	}

}
