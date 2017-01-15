package com.rentalcars.homework.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Eva Balazsfalvi
 *
 * Configuration class.
 * 
 */
@Configuration
public class RentalcarsApplicationConfiguration {
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplateBuilder()
	    		.build();
	}
}
