package com.bank.accounts;

import com.bank.accounts.dtos.AccountsContactInfoDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(value = {AccountsContactInfoDTO.class})
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@OpenAPIDefinition(
		info=@Info(
				title = "Bank Accounts API Documentation",
				version = "1.0",
				description = "Accounts microservice REST API Documentation",
				contact = @Contact(
						name = "Teulea Ioan-Octavian",
						email = "ioanteulea@gmail.com"
				),
				license = @License(
						name = "Apache 2.0 License",
						url = "http://www.apache.org/licenses/LICENSE-2.0"
				)
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
