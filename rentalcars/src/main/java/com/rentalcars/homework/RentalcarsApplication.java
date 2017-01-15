package com.rentalcars.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.rentalcars.homework.controller.RentalcarsApplicationController;

/**
 * 
 * @author Eva Balazsfalvi
 * 
 * Spring Boot console application for Rentalcars technical test
 *
 */
@SpringBootApplication
public class RentalcarsApplication {
	
	/**
	 * Entry point of application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RentalcarsApplication.class, args);
		String consoleResult = context.getBean(RentalcarsApplicationController.class).getVehicles();
		System.out.print(consoleResult);
	}
}
