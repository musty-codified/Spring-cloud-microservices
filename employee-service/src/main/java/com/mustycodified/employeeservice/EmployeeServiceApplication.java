package com.mustycodified.employeeservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@OpenAPIDefinition(
		info=@Info(
				title = "Employee Service REST APIs",
				description = "Employee Service REST APIs Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Mustycodified",
						email = "ilemonamustapha@gmail.com",
						url = "https:mustycodified.com"
				),
				license = @License(
						name = "Apache 2.0 Software Foundation",
						url =  "https:mustycodified.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Employee Service External Document",
				url = "https:mustycodified.com"
		)
)
@SpringBootApplication
@EnableFeignClients
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}
}
