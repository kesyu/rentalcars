package com.rentalcars.homework.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rentalcars.homework.bean.Vehicle;
import com.rentalcars.homework.service.SpecificationService;
import com.rentalcars.homework.service.VehicleService;

/**
 * 
 * @author Eva Balazsfalvi
 * 
 * Vehicles endpoint controller.
 *
 */
@RestController
public class RentalcarsApplicationController {
	private static final Logger log = LoggerFactory.getLogger(RentalcarsApplicationController.class);
	
	@Autowired
	private SpecificationService specificationService;
	
	@Autowired
	private VehicleService vehicleService;
	
	/**
	 * Root end point.
	 * @return Default welcoming message.
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String welcome () {
		log.info("Endpoint \"/\" was called");
		return "Welcome";
	}
	
	/**
	 * vehicles end point. 
	 * Execute the operations on all the cars.
	 * Prints the output.
	 * @return the output {@link String}
	 */
	@RequestMapping(value="/vehicles", method=RequestMethod.GET, produces={MediaType.TEXT_PLAIN_VALUE})
	public String getVehicles () {
		log.info("Endpoint \"/vehicles\" was called");
		
		StringBuilder response = new StringBuilder();
		response.append(printAllVehiclesWithAscendingPrice())
			.append(printDivider())
			.append(printVehicleSpecifications())
			.append(printDivider())
			.append(printSupplierRatingsDescending())
			.append(printDivider())
			.append(printVehicleSumOfScoresDescending());

		return response.toString();
	}
	
	private String printDivider() {
		return "----------\n";
	}
	
	/**
	 * Print all vehicles in ascending price order.
	 * 
	 * @return String of all vehicles in ascending price order.
	 */
	private String printAllVehiclesWithAscendingPrice() {
		StringBuilder response = new StringBuilder();
		List<Vehicle> vehicleList = vehicleService.findAllByAscendingPrice();
		
		int i=0;
		for (Vehicle vehicle : vehicleList) {
			StringJoiner stringJoiner = new StringJoiner("} - {", "{", "}\n");
			stringJoiner.add(vehicle.getName()).add(vehicle.getPrice().toString());
			response.append(++i).append(".\t").append(stringJoiner.toString());
		}
		
		return response.toString();
	}
	
	/**
	 * Print vehicle specifications.
	 * @return String of vehicle specifications.
	 */
	private String printVehicleSpecifications() {
		StringBuilder response = new StringBuilder();
		List<Vehicle> vehicleList = vehicleService.findAll();
		
		int i=0;
		for (Vehicle vehicle : vehicleList) {
			StringJoiner stringJoiner = new StringJoiner("} - {", "{", "}\n");
			stringJoiner.add(vehicle.getName()).add(vehicle.getSipp()).merge(specificationService.parseSIPP(vehicle.getSipp()));
			response.append(++i).append(".\t").append(stringJoiner.toString());
		}
		
		return response.toString();
	}
	
	/**
	 * Print highest rated supplier per car type, descending order.
	 * @return String of highest rated supplier per car type, descending order.
	 */
	private String printSupplierRatingsDescending() {
		StringBuilder response = new StringBuilder();
		Map<String, Vehicle> topRated = vehicleService.findHighestRatedSuppliersByCarType();
		
		int i=0;
		for (Entry<String, Vehicle> vehicleEntry : topRated.entrySet()) {
			StringJoiner stringJoiner = new StringJoiner("} - {", "{", "}\n");
			Vehicle vehicle = vehicleEntry.getValue();
			stringJoiner.add(vehicle.getName()).add(vehicleEntry.getKey()).add(vehicle.getSupplier()).add(vehicle.getRating().toString());
			response.append(++i).append(".\t").append(stringJoiner.toString());
		}
		
		return response.toString();
	}
	
	/**
	 * Print all vehicles by descending sum score.
	 * @return String of all vehicles by descending sum score.
	 */
	private String printVehicleSumOfScoresDescending() {
		StringBuilder response = new StringBuilder();
		List<Vehicle> vehicleList = vehicleService.findAllByDescendingSumScore();
		
		int i=0;
		for (Vehicle vehicle : vehicleList) {
			StringJoiner stringJoiner = new StringJoiner("} - {", "{", "}\n");
			Float sumScore = vehicle.getScore() + vehicle.getRating();
			stringJoiner.add(vehicle.getName()).add(vehicle.getScore().toString()).add(vehicle.getRating().toString()).add(sumScore.toString());
			response.append(++i).append(".\t").append(stringJoiner.toString());
		}
		
		return response.toString();
	}

	
}
