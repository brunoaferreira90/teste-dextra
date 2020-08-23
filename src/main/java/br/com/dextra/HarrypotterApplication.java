package br.com.dextra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableCircuitBreaker
@SpringBootApplication
@EnableCaching
public class HarrypotterApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarrypotterApplication.class, args);
	}

}
