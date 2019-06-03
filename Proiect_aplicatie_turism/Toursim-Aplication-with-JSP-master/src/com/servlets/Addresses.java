package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.Implementations.Factory;
import com.Tables.Accomodation;
import com.Tables.Address;
import com.Tables.City;
import com.mysql.jdbc.StringUtils;

/**
 * Servlet implementation class Addresses
 */
@WebServlet("/Addresses")
public class Addresses extends Controller {
	private static final long serialVersionUID = 1L;
	
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
        String name = request.getParameter("name");
        String postal_code = request.getParameter("postalCode");
        double longitude = Double.parseDouble(request.getParameter("longitude"));
        double latitude = Double.parseDouble(request.getParameter("latitude"));
        int city_id = Integer.parseInt(request.getParameter("city_id"));
        
        Address address= new Address();
        
        address.setName(name);
        address.setPostal_code(postal_code);
        address.setLatitude(latitude);
        address.setLongitude(longitude);
        address.setCity_id(city_id);
        
        address = Factory.getAddressImpl().insert(address);

        response.sendRedirect("?action=edit&id="+address.getId());
		
	}

	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter("id");
		System.out.println(idParam);
		
		if( StringUtils.isNullOrEmpty(idParam) ) {
			insert(request,response);
			return;
        }

		int id = Integer.parseInt(idParam);
		
		String name = request.getParameter("name");
        String postal_code = request.getParameter("postalCode");
        double longitude = Double.parseDouble(request.getParameter("longitude"));
        double latitude = Double.parseDouble(request.getParameter("latitude"));
        int city_id = Integer.parseInt(request.getParameter("city_id"));

        Address address= Factory.getAddressImpl().getAddress(id);
        
        address.setName(name);
        address.setPostal_code(postal_code);
        address.setLatitude(latitude);
        address.setLongitude(longitude);
        address.setCity_id(city_id);
        
        
        Factory.getAddressImpl().update(address);

        response.sendRedirect("?action=edit&id="+id);
	}
	
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter("id");
		
		if( idParam != null) {
			int id = Integer.parseInt(idParam);
			Address address= Factory.getAddressImpl().getAddress(id);
	        request.setAttribute("address", address);
        } 

		List<City> cities= Factory.getCityImpl().getAllCities();
		
        request.setAttribute("cities", cities);
        
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/address/edit.jsp");
        dispatcher.forward(request, response);
		
	}

}
