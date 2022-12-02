package com.example.demodatajpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
@SecurityScheme(
	    name = "basicAuth",
	    type = SecuritySchemeType.HTTP,
	    scheme = "basic"
	)
public class SwaggerConfig {
	@Bean
	  public OpenAPI openAPI() {
	      return new OpenAPI()
	              .info(new Info().title("My API")
	              .description("My App")
	              .version("1.0.0")
	              .license(new License().name("Apache 2.0").url("http://springdoc.org"))
	              .contact(new Contact().name("Dev Team").email("desvTeam@Dev.com"))
	              .termsOfService("http://localhost/tos"))
	              .externalDocs(new ExternalDocumentation()
	              .description("My Api doc")
	              .url("https://springshop.wiki.github.org/docs"))
	              .addServersItem(new Server().url("http://localhost:8080"));
	              
	  }
}
