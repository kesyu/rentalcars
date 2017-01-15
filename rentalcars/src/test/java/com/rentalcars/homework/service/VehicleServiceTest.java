package com.rentalcars.homework.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rentalcars.homework.bean.Vehicle;
import com.rentalcars.homework.util.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleServiceTest {
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private TestUtil testUtil;
	
	private VehicleService vehicleServiceMock;
	
	private List<Vehicle> testData;
	
	@Before
	public void init() {
		testData = testUtil.getTestDataUnsorted();
		vehicleServiceMock = Mockito.spy(vehicleService);
		Mockito.when(vehicleServiceMock.findAll()).thenReturn(testData);
	}
	
	@Test
	public void testFindAll() {
		List<Vehicle> list = vehicleService.findAll();
		Assert.assertNotNull(list);
		Assert.assertFalse(list.isEmpty());
	}
	
	@Test
	public void testFindHighestRatedSuppliersByCarType() {
		Map<String, Vehicle> expected = new HashMap<>();
		expected.put("Full size", new Vehicle("FVAR", "Ford Galaxy", new Float(706.89), "Hertz", new Float(8.9), 7));
		expected.put("Mini", new Vehicle("MBMN", "Kia Picanto", new Float(136.57), "Hertz", new Float(8.9), 1));
		expected.put("Intermediate", new Vehicle("IDMR", "Skoda Octavia", new Float(236.24), "Hertz", new Float(8.9), 3));
		
		Map<String, Vehicle> actual = vehicleServiceMock.findHighestRatedSuppliersByCarType();
		Mockito.verify(vehicleServiceMock, Mockito.times(1)).findAll();
		
		for (Entry<String, Vehicle> entry : actual.entrySet()) {
			Assert.assertEquals(expected.get(entry.getKey()), entry.getValue());
		}
	}
	
	@Test
	public void testFindAllByAscendingPrice() {
		List<Vehicle> expected = testUtil.getTestDataAscendingPrice();
		List<Vehicle> actual = vehicleServiceMock.findAllByAscendingPrice();
		Mockito.verify(vehicleServiceMock, Mockito.times(1)).findAll();
		
		Assert.assertEquals(expected.size(), actual.size());
		for (int i=0; i<actual.size(); i++) {
			Assert.assertEquals(expected.get(i), actual.get(i));
		}
	}
	
	@Test
	public void testFindAllByDescendingSumScore() {
		List<Vehicle> expected = testUtil.getTestDataDescendingSumScore();
		List<Vehicle> actual = vehicleServiceMock.findAllByDescendingSumScore();
		Mockito.verify(vehicleServiceMock, Mockito.times(1)).findAll();
		
		Assert.assertEquals(expected.size(), actual.size());
		for (int i=0; i<actual.size(); i++) {
			Assert.assertEquals(expected.get(i), actual.get(i));
		}
	}
}
