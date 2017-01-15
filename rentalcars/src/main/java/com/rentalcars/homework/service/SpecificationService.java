package com.rentalcars.homework.service;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rentalcars.homework.bean.Vehicle;

/**
 * 
 * @author Eva Balazsfalvi
 *
 * Handles specification operations.
 *
 */
@Service
public class SpecificationService {
	private static final Logger log = LoggerFactory.getLogger(SpecificationService.class);
	
	private static final Map<Character, String> carType = new HashMap<>();
	static {
		carType.put('M', "Mini");
		carType.put('E', "Economy");
		carType.put('C', "Compact");
		carType.put('I', "Intermediate");
		carType.put('S', "Standard");
		carType.put('F', "Full size");
		carType.put('P', "Premium");
		carType.put('L', "Luxury");
		carType.put('X', "Special");		
	}

	private static final Map<Character, String> doorCarType = new HashMap<>();
	static {
		doorCarType.put('B', "2 doors");
		doorCarType.put('C', "4 doors");
		doorCarType.put('D', "5 doors");
		doorCarType.put('W', "Estate");
		doorCarType.put('T', "Convertible");
		doorCarType.put('F', "SUV");
		doorCarType.put('P', "Pick up");
		doorCarType.put('V', "Passenger Van");
	}
	
	private static final Map<Character, String> transmission = new HashMap<>();
	static {
		transmission.put('M', "Manual");
		transmission.put('A', "Automatic");
	}
	
	private static final Map<Character, String> fuel = new HashMap<>();
	static {
		fuel.put('N', "Petrol");
		fuel.put('R', "Petrol");
	}
	
	private static final Map<Character, String> airCon = new HashMap<>();
	static {
		airCon.put('N', "No air conditioning");
		airCon.put('R', "Air conditioning");
	}
	
	/**
	 * Parses the SIPP code of a {@link Vehicle}.
	 * Generates a specification as string for a given {@link Vehicle} by its SIPP code.
	 * 
	 * @param sipp the SIPP code of a {@link Vehicle}.
	 * @return formatted specification
	 */
	public StringJoiner parseSIPP(String sipp) {
		checkSIPPIsValid(sipp);
		StringJoiner stringJoiner = new StringJoiner("} - {", "{", "}\n");
		stringJoiner.add(getCarTypeFromSIPP(sipp))
			.add(doorCarType.get(sipp.charAt(1)))
			.add(transmission.get(sipp.charAt(2)))
			.add(fuel.get(sipp.charAt(3)))
			.add(airCon.get(sipp.charAt(3)));
		return stringJoiner;
	}
	
	/**
	 * Decides the type of a {@link Vehicle} by its SIPP code.
	 * 
	 * @param sipp the SIPP code of a {@link Vehicle}.
	 * @return the type of the {@link Vehicle}
	 */
	public String getCarTypeFromSIPP(String sipp) {
		checkSIPPIsValid(sipp);
		return carType.get(sipp.charAt(0));
	}
	
	/**
	 * Decides if {@link Vehicle} has manual transmission or not.
	 * 
	 * @param sipp the SIPP code of a {@link Vehicle}.
	 * @return true if {@link Vehicle} has manual transmission, false otherwise.
	 */
	public boolean hasManualTransmission(String sipp) {
		checkSIPPIsValid(sipp);
		return sipp.charAt(2) == 'M';
	}
	
	/**
	 * Decides if {@link Vehicle} has air conditioning or not.
	 * 
	 * @param sipp the SIPP code of a {@link Vehicle}.
	 * @return true if {@link Vehicle} has air conditioning, false otherwise.
	 */
	public boolean hasAirConditioning(String sipp) {
		checkSIPPIsValid(sipp);
		return sipp.charAt(3) == 'R';
	}

	/**
	 * Checks if sipp is valid and if it can be parsed.
	 *  
	 * @param sipp
	 */
	private void checkSIPPIsValid(String sipp) {
		if (sipp != null && sipp.length() < 4) {
			throw new IllegalArgumentException("Invalid SIPP value.");
		}
		if (doorCarType.get(sipp.charAt(1)) == null 
				|| transmission.get(sipp.charAt(2)) == null
				|| fuel.get(sipp.charAt(3)) == null
				|| airCon.get(sipp.charAt(3)) == null) {
			log.error("SIPP value \"" + sipp + "\" contains invalid character(s), parsing related attribute(s) will be skipped.");
		}
	}
}
