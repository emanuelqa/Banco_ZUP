package com.emanuel.banco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.emanuel.banco.service.EmailService;
import com.emanuel.banco.service.MockEmailService;

@SpringBootApplication
public class BancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoApplication.class, args);
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
	
}
