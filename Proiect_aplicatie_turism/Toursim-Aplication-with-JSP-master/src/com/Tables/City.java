package com.Tables;

import java.util.List;

import com.DAO.Implementations.Factory;

public class City {
	private int id;
	private String name;
	private String ISO3;
	private double latitude;
	private double longitude;
	private int county_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getISO3() {
		return ISO3;
	}
	public void setISO3(String iSO3) {
		ISO3 = iSO3;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getCounty_id() {
		return county_id;
	}
	public void setCounty_id(int county_id) {
		this.county_id = county_id;
	}
	public List<Accomodation> getAccomodations() {

		return Factory.getCityImpl().getAccomodations(this.getId());

	}
	
	
	
	
}
