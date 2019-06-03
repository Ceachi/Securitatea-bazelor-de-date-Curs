package com.DAO.Implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Tables.*;
import com.dbConnection.MySQLConnection;
import com.DaoInterfaces.CityDAO;

public class CityImpl implements CityDAO {

	@Override
	public List<City> getAllCities() {
		String sql = "Select * from city";
		List<City> cityList = new ArrayList<City>();
				
		try {
			Connection conn = MySQLConnection.startConnection();
			
			PreparedStatement pstm = conn.prepareStatement(sql);
	        ResultSet rs = pstm.executeQuery();
	        
	        while(rs.next()) {
	        	City city = new City();
	        	
	        	// get the parameters selected in the table
	        	int cityID = rs.getInt("id");
	        	String cityName = rs.getString("name");
	        	String cityISO3 = rs.getString("ISO3");// daca apare vreo eroare, poate ISO3 e cu litere mici
	        	double cityLatitude = rs.getDouble("latitude");
	        	double cityLongitude = rs.getDouble("longitude");
	        	int county_id = rs.getInt("county_id");
	        	
	        	// add to list
	        	city.setId(cityID);
	        	city.setName(cityName);
	        	city.setISO3(cityISO3);
	        	city.setLatitude(cityLatitude);
	        	city.setLongitude(cityLongitude);
	        	city.setCounty_id(county_id);
	        	
	        	// add to list
	        	cityList.add(city);
	        }
	        pstm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ! remember to return the new List
		return cityList;
	}

	@Override
	public City getCity(int id) {
		String sql = "Select * from city where id=?";
		
		try {
			Connection conn = MySQLConnection.startConnection();
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
	        ResultSet rs = pstm.executeQuery();
	        
	        if(rs.next()) {
	        	City city = new City();
	        	
	        	// get the parameters selected in the table
	        	int cityID = rs.getInt("id");
	        	String cityName = rs.getString("name");
	        	String cityISO3 = rs.getString("ISO3");// daca apare vreo eroare, poate ISO3 e cu litere mici
	        	double cityLatitude = rs.getDouble("latitude");
	        	double cityLongitude = rs.getDouble("longitude");
	        	int county_id = rs.getInt("county_id");
	        	
	        	// add to list
	        	city.setId(cityID);
	        	city.setName(cityName);
	        	city.setISO3(cityISO3);
	        	city.setLatitude(cityLatitude);
	        	city.setLongitude(cityLongitude);
	        	city.setCounty_id(county_id);
	        	
	        	// return
	        	return city;
	        }
	        pstm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateCity(City city) {
		String sql = "Update city set name =?, ISO3=?, latitude=?, longitude=?, county_id=? where id=? ";
		 
		try {
		Connection conn = MySQLConnection.startConnection();
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        // implement ? in query
        pstm.setString(1, city.getName());
        pstm.setString(2, city.getISO3());
        pstm.setDouble(3, city.getLatitude());
        pstm.setDouble(4, city.getLongitude());
        pstm.setInt(5, city.getCounty_id());
        pstm.setInt(6, city.getId());
          
        //execute
        pstm.executeUpdate();
        pstm.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteCity(City city) {
		String sql = "Delete From City where id= ?";
		 
		try {
			Connection conn = MySQLConnection.startConnection();
	        PreparedStatement pstm = conn.prepareStatement(sql);
	        
	        pstm.setInt(1, city.getId());
	        pstm.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public County getCounty(int county_id) {
		County county = Factory.getCountyImpl().getCounty(county_id);
		return county;
	}

	public List<Accomodation> getAccomodations(int id) {
		String sql = "select * from "
				+ "accomodation h "
				+ "left join address a on a.id = h.address_id where a.city_id = ?";
		List<Accomodation> accomodationList = new ArrayList<Accomodation>();
		
		
		try {
			Connection conn = MySQLConnection.startConnection();
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1,id);
			
	        ResultSet rs = pstm.executeQuery();
	        
	        while(rs.next()) {
	        	Accomodation accomodation = new Accomodation();
	        	
	        	// get the parameters selected in the table
	        	int accomodationID = rs.getInt("id");
	        	String accomodationName = rs.getString("name");
	        	int  accomodationStars = rs.getInt("stars");
	        	String accomodationDescription_full = rs.getString("description_full");
	        	String accomodationDescription_short = rs.getString("description_short");
	        	int address_id = rs.getInt("address_id");
	        	int contact_id = rs.getInt("contact_id");
	        	int accomodation_type_id = rs.getInt("accomodation_type_id");
	        	
	        	// add to list
	        	accomodation.setId(accomodationID);
	        	accomodation.setName(accomodationName);
	        	accomodation.setStars(accomodationStars);
	        	accomodation.setDescription_full(accomodationDescription_full);
	        	accomodation.setDescription_short(accomodationDescription_short);
	        	accomodation.setAddress_id(address_id);
	        	accomodation.setContact_id(contact_id);
	        	accomodation.setAccomodation_type_id(accomodation_type_id);
	        	
	        	
	        	// add to list
	        	accomodationList.add(accomodation);
	        }
	        pstm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accomodationList;
	}

}
