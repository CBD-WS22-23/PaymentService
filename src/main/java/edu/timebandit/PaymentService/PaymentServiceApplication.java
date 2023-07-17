package edu.timebandit.PaymentService;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new io.swagger.v3.oas.models.Components())
				.info(new io.swagger.v3.oas.models.info.Info()
						.title("Payment Service API")
						.version("v1")
						.description("Payment Service API for TimeBandit"));
	}

}
