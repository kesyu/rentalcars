package com.rentalcars.homework.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecificationServiceTest {

	@Autowired
	private SpecificationService specificationService;
	
	@Test(expected = NullPointerException.class)
	public void testParseSIPPWithNull() {
		specificationService.parseSIPP(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testParseSIPPWithEmptyString() {
		specificationService.parseSIPP("");
	}

	private Object[][] parseSIPPData() {
		return new Object[][] {
			{"MBMN", "{Mini} - {2 doors} - {Manual} - {Petrol} - {No air conditioning}\n"},
			{"ECAR", "{Economy} - {4 doors} - {Automatic} - {Petrol} - {Air conditioning}\n"},
			{"CDMN", "{Compact} - {5 doors} - {Manual} - {Petrol} - {No air conditioning}\n"},
			{"IWAR", "{Intermediate} - {Estate} - {Automatic} - {Petrol} - {Air conditioning}\n"},
			{"SFMN", "{Standard} - {SUV} - {Manual} - {Petrol} - {No air conditioning}\n"},
			{"FFAR", "{Full size} - {SUV} - {Automatic} - {Petrol} - {Air conditioning}\n"},
			{"PPMN", "{Premium} - {Pick up} - {Manual} - {Petrol} - {No air conditioning}\n"},
			{"LVAR", "{Luxury} - {Passenger Van} - {Automatic} - {Petrol} - {Air conditioning}\n"},
			{"XBMN", "{Special} - {2 doors} - {Manual} - {Petrol} - {No air conditioning}\n"}
		};
	}
	
	@Test()
	public void testParseSIPP() {
		for (Object[] testDatas : parseSIPPData()) {
			String testData = (String)testDatas[0];
			String expected = (String)testDatas[1];
			
			String actual = specificationService.parseSIPP(testData).toString();

			Assert.assertEquals(expected, actual);
		}
	}
	
	private Object[][] getCarTypeFromSIPPData() {
		return new Object[][] {
			{"MBMN", "Mini"},
			{"ECAR", "Economy"},
			{"CDMN", "Compact"},
			{"IWAR", "Intermediate"},
			{"SFMN", "Standard"},
			{"FFAR", "Full size"},
			{"PPMN", "Premium"},
			{"LVAR", "Luxury"},
			{"XBMN", "Special"}
		};
	}
	
	@Test()
	public void testGetCarTypeFromSIPP() {
		for (Object[] testDatas : getCarTypeFromSIPPData()) {
			String testData = (String)testDatas[0];
			String expected = (String)testDatas[1];
			
			String actual = specificationService.getCarTypeFromSIPP(testData);
			
			Assert.assertEquals(expected, actual);
		}
	}
	
	@Test
	public void testHasManualTransmission() {
		String testData = "MBMN";
		
		Assert.assertTrue("Should be true, but it isn't", specificationService.hasManualTransmission(testData));
		
		testData = "ECAR";
		
		Assert.assertFalse("Should be false, but it isn't", specificationService.hasManualTransmission(testData));
	}
	
	@Test
	public void testHasAirConditioning() {
		String testData = "MBMN";
		
		Assert.assertTrue("Should be true, but it isn't", specificationService.hasManualTransmission(testData));
		
		testData = "ECAR";
		
		Assert.assertFalse("Should be false, but it isn't", specificationService.hasManualTransmission(testData));
	}
}
