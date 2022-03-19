package com.bootcamp.bankdeposit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@EnableEurekaClient
@SpringBootApplication
public class BankdepositApplication {

	@Value("${microservices-urls.api-transaction}")
	private String API_TRANSACTION;

	@Bean
	public WebClient webClient(WebClient.Builder builder){
		return builder
				.baseUrl(API_TRANSACTION)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(BankdepositApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		 return new RestTemplate();
	}
}
