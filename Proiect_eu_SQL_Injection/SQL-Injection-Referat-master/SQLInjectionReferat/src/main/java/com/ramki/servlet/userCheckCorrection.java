package com.ramki.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;


@WebServlet(name = "userCheckCorrection", urlPatterns = {"/userCheckCorrection"})
public class userCheckCorrection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	final String url = "jdbc:mysql://localhost:3306/";
    final String dbName = "test";
    final String driver = "com.mysql.jdbc.Driver";
    final String userName = "root";
    final String psw = "";

    PreparedStatement preparedStatement;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
	        response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	         out.println("<h1>SQL Injection Example</h1><br/><br/>");
	        try {

	            out.println("<html>");
	            out.println("<head>");
	            out.println("<title>Servlet userCheck</title>");
	            out.println("</head>");
	            out.println("<body>");
	            String user = request.getParameter("username");
	            String password = request.getParameter("password");
	            Connection conn = null;
	            try {
	                Class.forName(driver).newInstance();
	                conn = DriverManager.getConnection(url + dbName, userName, psw);
	                Statement st = conn.createStatement();
					String query = "SELECT * FROM User WHERE userId = ? and password = ?";
	                preparedStatement=(PreparedStatement) conn.prepareStatement(query);
	                preparedStatement.setString(1, user);
	                preparedStatement.setString(2, password);
	                out.println("Query : "+query);

	                System.out.println(preparedStatement);
	                        ResultSet res = preparedStatement.executeQuery();
	                
	               out.println("<br/><br/>Results");
	                while (res.next()) {
	                    String s = res.getString("userId");
	                    out.println("<br/><br/>\t\t" + s);
	                }

	                
	                
	                conn.close();
	                System.out.println("Disconnected from database");
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        out.println("</body>");
	        out.println("</html>");

	    }
	        finally {
	        	if (preparedStatement != null) {
					preparedStatement.close();
				}
	            out.close();
	    }
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
		processRequest(request, response);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

/** 
 * Handles the HTTP <code>POST</code> method.
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
		processRequest(request, response);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

/** 
 * Returns a short description of the servlet.
 * @return a String containing servlet description
 */
@Override
    public String getServletInfo() {
    return "Short description";
}// </editor-fold>


}
