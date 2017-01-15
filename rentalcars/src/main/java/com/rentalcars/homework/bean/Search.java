package com.rentalcars.homework.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Eva Balazsfalvi
 * 
 * Represents a {@link List} of {@link Vehicle} holder.
 * 
 */
public class Search implements Serializable {
	private static final long serialVersionUID = 9186654033097190117L;
	
	@JsonProperty("VehicleList")
	private List<Vehicle> vehicleList;

	public Search() {
		super();
	}

	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(List<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vehicleList == null) ? 0 : vehicleList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Search other = (Search) obj;
		if (vehicleList == null) {
			if (other.vehicleList != null)
				return false;
		} else if (!vehicleList.equals(other.vehicleList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Search [VehicleList=").append(vehicleList).append("]");
		return builder.toString();
	}
}
