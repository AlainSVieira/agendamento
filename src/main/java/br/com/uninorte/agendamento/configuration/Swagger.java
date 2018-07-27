package br.com.uninorte.agendamento.configuration;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger {
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
					   .select()
					   .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
					   .paths(PathSelectors.ant("/**"))
					   .build()
					   .pathMapping("/")
					   .securitySchemes(Collections.singletonList(new ApiKey("APIKEY", HttpHeaders.AUTHORIZATION, "HEADER")))
					   .securityContexts(Collections.singletonList(SecurityContext.builder()
							   .securityReferences(authorization())
							   .forPaths(PathSelectors.ant("/**"))
							   .forPaths(Predicates.not(PathSelectors.ant("/ws/authentication/**")))
							   .build()))
					   .apiInfo(new ApiInfo(
							   "Agendamento Documentation",
							   "Documentation for Agendamento",
							   "DEVELOPMENT",
							   "Terms of Service: TODO",
							   new Contact("Uninorte", "", ""),
							   "Apache License Version 2.0",
							   "https://www.apache.org/licenses/LICENSE-2.0",
							   Collections.singleton(new VendorExtension<String>() {
								   @Override
								   public String getName() {
									   return "Aulate";
								   }

								   @Override
								   public String getValue() {
									   return "Aula";
								   }
							   })));
	}


	private List<SecurityReference> authorization() {
		AuthorizationScope		scope	= new AuthorizationScope("REST", "Authorized REST Services");
		AuthorizationScope[]	scopes	= new AuthorizationScope[1];
		scopes[0] 						= scope;
		return Collections.singletonList(new SecurityReference("APIKEY", scopes));
	}
}
