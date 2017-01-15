package com.rentalcars.homework.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rentalcars.homework.bean.Vehicle;

@Component
public class TestUtil {
	
	public List<Vehicle> getTestDataUnsorted() {
		List<Vehicle> list = new ArrayList<>();
		list.add(new Vehicle("FVAR", "Ford Galaxy", new Float(706.89), "Hertz", new Float(8.9), 7));
		list.add(new Vehicle("MBMN", "Kia Picanto", new Float(136.57), "Hertz", new Float(8.9), 1));
		list.add(new Vehicle("IDMR", "Skoda Octavia", new Float(236.24), "Hertz", new Float(8.9), 3));
		list.add(new Vehicle("IDMR", "Chevrolet Cruze", new Float(229.45), "Sixt", new Float(8.2), 3));
		return list;
	}
	
	public List<Vehicle> getTestDataAscendingPrice() {
		List<Vehicle> list = new ArrayList<>();
		list.add(new Vehicle("MBMN", "Kia Picanto", new Float(136.57), "Hertz", new Float(8.9), 1));
		list.add(new Vehicle("IDMR", "Chevrolet Cruze", new Float(229.45), "Sixt", new Float(8.2), 3));
		list.add(new Vehicle("IDMR", "Skoda Octavia", new Float(236.24), "Hertz", new Float(8.9), 3));
		list.add(new Vehicle("FVAR", "Ford Galaxy", new Float(706.89), "Hertz", new Float(8.9), 7));
		return list;
	}
	
	public List<Vehicle> getTestDataDescendingSumScore() {
		List<Vehicle> list = new ArrayList<>();
		list.add(new Vehicle("FVAR", "Ford Galaxy", new Float(706.89), "Hertz", new Float(8.9), 7));
		list.add(new Vehicle("IDMR", "Skoda Octavia", new Float(236.24), "Hertz", new Float(8.9), 3));
		list.add(new Vehicle("IDMR", "Chevrolet Cruze", new Float(229.45), "Sixt", new Float(8.2), 3));
		list.add(new Vehicle("MBMN", "Kia Picanto", new Float(136.57), "Hertz", new Float(8.9), 1));
		return list;
	}
}
