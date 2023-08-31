package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


	
@Configuration
public class OpenApiConfig {
		
	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI()
			.info(new Info()
			.title("API Gestão De Lançamentos Financeiros")
			.version("v1")
			.description("API desenvolvida para prover serviços de Gestão para Lançcamentos Financeiros De Empresas clientes")
			.license(new License().name("Apache 2.0").url("https://github")));
	}	
	
}
