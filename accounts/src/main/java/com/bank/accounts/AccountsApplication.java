package com.bank.accounts;

import com.bank.accounts.dto.ApplicationDetailsDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts Microservice REST API documentation",
				description = "Bank account Microservice description",
				version = "v1",
				license = @License(
						name = "MIT License"
				),
				contact = @Contact(
						name = "Krishna Singh",
						email = "id.krishnasingh@gmail.com"
				)
		)
)
 @EnableConfigurationProperties(value = {ApplicationDetailsDto.class})
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
