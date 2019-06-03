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
@WebServlet("/Cities")
public class Cities extends Controller {
	private static final long serialVersionUID = 1L;
	
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter("id");
		
		int id = Integer.parseInt(idParam);
		City city= Factory.getCityImpl().getCity(id);
        request.setAttribute("city", city);


		List<Accomodation> accomodations = city.getAccomodations();
		
        request.setAttribute("accomodations", accomodations);
        
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/cities/edit.jsp");
        dispatcher.forward(request, response);
		
	}

}
