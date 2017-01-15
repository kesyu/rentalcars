package com.rentalcars.homework.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rentalcars.homework.bean.SearchWrapper;
import com.rentalcars.homework.bean.Vehicle;

/**
 * 
 * @author Eva Balazsfalvi
 *
 * Handles vehicle operations
 *  
 */
@Service
public class VehicleService {
	private static final String URL = "http://www.rentalcars.com/js/vehicles.json";
	private static final int MANUAL_TRANSMISSION_SCORE = 1;
	private static final int AUTOMATIC_TRANSMISSION_SCORE = 5;
	private static final int AIR_CONDITIONING_SCORE = 2;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private SpecificationService specificationService;

	private List<Vehicle> vehicles;

	/**
	 * Gets all vehicles from json acquired from rentalcars url if it hasn't been done already.
	 *  
	 * @return List of vehicles with scores set.
	 * 
	 */
	public List<Vehicle> findAll() {
		if (vehicles == null) {
			ResponseEntity<SearchWrapper> responseEntity = restTemplate.getForEntity(URL, SearchWrapper.class);
			vehicles = responseEntity.getBody().getSearch().getVehicleList();
			vehicles.stream().forEach(v -> {
				Integer score = 0;

				if (specificationService.hasManualTransmission(v.getSipp())) {
					score += MANUAL_TRANSMISSION_SCORE;
				} else {
					score += AUTOMATIC_TRANSMISSION_SCORE;
				}

				if (specificationService.hasAirConditioning(v.getSipp())) {
					score += AIR_CONDITIONING_SCORE;
				}
				v.setScore(score);
			});
		}
		return Collections.unmodifiableList(vehicles);
	}

	/**
	 * Find highest rated supplier per car type, descending order.
	 * 
	 * @return Highest rated supplier per car type, descending order.
	 */
	public Map<String, Vehicle> findHighestRatedSuppliersByCarType() {
		Map<String, Vehicle> topRated = new TreeMap<>(Collections.reverseOrder());

		for (Vehicle vehicle : this.findAll()) {
			String carType = specificationService.getCarTypeFromSIPP(vehicle.getSipp());
			if (topRated.containsKey(carType)) {
				if (topRated.get(carType).getRating() < vehicle.getRating()) {
					topRated.replace(carType, vehicle);
				}
			} else {
				topRated.put(carType, vehicle);
			}
		}
		return topRated;
	}

	/**
	 * Find all vehicles by ascending price.
	 * 
	 * @return All vehicles by ascending price.
	 */
	public List<Vehicle> findAllByAscendingPrice() {
		List<Vehicle> list = this.findAll().stream().sorted((v1, v2) -> {
			return v1.getPrice() < v2.getPrice() ? -1 : v1.getPrice() > v2.getPrice() ? 1 : 0;
		}).collect(Collectors.toList());
		return list;
	}

	/**
	 * Find all vehicles by descending sum score.
	 * 
	 * @return All vehicles by descending sum score.
	 */
	public List<Vehicle> findAllByDescendingSumScore() {
		List<Vehicle> list = this.findAll().stream().sorted((v1, v2) -> {
			Float v1SumScore = v1.getScore() + v1.getRating();
			Float v2SumScore = v2.getScore() + v2.getRating();
			float scoreDifference = v2SumScore - v1SumScore;
			return scoreDifference > 0 ? 1 : scoreDifference < 0 ? -1 : 0;
		}).collect(Collectors.toList());
		return list;
	}
}
