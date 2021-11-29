package com.livrariasaara.core.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

	 @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.any())              
	          .paths(PathSelectors.any())                          
	          .build()
	          .apiInfo(apiInfo());                                           
	    }
	 
	/*
	private ApiInfo apiInfo() {
		    return new ApiInfo(
		      "Livraria Saara REST API", 
		      "API rest livraria projeto Engenharia de Software II.", 
		      "API TOS", 
		      "Terms of service", 
		      new Contact("Ráfion", "https://github.com/rafion", "rafion.torres@gmail.com"), 
		      "License of API", "API license URL", Collections.emptyList());
		}
	*/
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Livraria Saara REST API")
				.description("REST API projeto Engenharia de Software II.")
				.version("1")
				.contact(new Contact("G6 - Turma ESII 2021", "https://github.com/rafion", "rafion.torres@gmail.com"))
				.build();
	}
}
