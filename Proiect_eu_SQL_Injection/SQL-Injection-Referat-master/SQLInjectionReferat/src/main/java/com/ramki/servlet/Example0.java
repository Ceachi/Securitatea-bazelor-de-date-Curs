/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ramki.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ramakrishnan
 */
@WebServlet(name = "example0", urlPatterns = {"/example0"})
public class Example0 extends HttpServlet {
	
	
    final String url = "jdbc:mysql://localhost:3306/";
    final String dbName = "test";
    final String driver = "com.mysql.jdbc.Driver";
    final String userName = "root";
    final String psw = "";

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
				String query="SELECT * FROM  User where userid= "+user;
                out.println("Query : "+query);
                
                
                //PreparedStatement  preparedStatement=conn.prepareStatement("SELECT * FROM  usercheck where username=?") ;
                //preparedStatement.setString(1, user);

                System.out.printf(query);
                        ResultSet res = st.executeQuery(query);
                
                 //ResultSet res = preparedStatement.executeQuery();
               out.println("<br/><br/>Results");
                while (res.next()) {
                    //int i = res.getInt("Emp_code");
                    String column1 = res.getString("userId");
                    String column2 = res.getString("password");
                    String column3 = res.getString("city");
                    String column4 = res.getString("country");
                    String column5 = res.getString("email");
                    String column6 = res.getString("phone");
                    String column7 = res.getString("firstName");
                    String column8 = res.getString("lastName");
                    out.println("</br>");
                    out.println("userid" + "\t\t\t" +  "password" +  "\t\t\t"+ "city" + "\t\t\t" + "country" + "\t\t\t" + "email"+ "\t\t\t"
                    + "phone" + "\t\t\t" + "firstName" + "\t\t\t" +  "lastName" + "<br>");
                    out.println(column1 + "\t\t\t" +  column2+  "\t\t\t"+ column3 + "\t\t\t" + column4 + "\t\t\t" + column5 + "\t\t\t"
                            + column6 + "\t\t\t" + column7 + "\t\t\t" +  column8 + "<br>");
                    //out.println("<br/><br/>\t\t" + s);
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
            out.close();
    }
}
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
/** 
 * Handles the HTTP <code>GET</code> method.
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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