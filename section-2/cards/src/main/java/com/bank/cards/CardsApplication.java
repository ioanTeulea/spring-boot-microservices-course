package com.bank.cards;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@OpenAPIDefinition(
		info=@Info(
				title = "Bank Cards API Documentation",
				version = "1.0",
				description = "Cards microservice REST API Documentation",
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
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
