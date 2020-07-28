/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ajitha.M
 */

public class insert extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         try ( Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/note_details","note","note")) {
            /* TODO output your page here. You may use following sample code. */
             //Statement st=con.createStatement();
             String id = request.getParameter("note_name");
             String des = request.getParameter("des");
             PreparedStatement pState = con.prepareStatement("insert into notes values(?,?)");
             pState.setString(1,id);
             pState.setString(2,des);
            
           
           
           
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet checkk</title>");            
            out.println("</head>");
            out.println("<body>");
            int i = pState.executeUpdate();
          con.commit();
          if(i!=0)
          {
               out.print("<center><h1>Your Note created successfully ...</h1>");  
             out.print("<h2>New Note</h2>Sticky note with following details is created Successfully!!!<br><br></center>");
                
           out.print("<center>Note Name => ");

                        out.print(id+"<br>");

                        out.print("Detail => ");

                        out.print(des+"<br></center>");           
          }
          else
          {
              out.print("<center><h1>awerYour Note created successfully ...</h1>");  
          }
            
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException ex) {
            out.println(ex);
            out.println("<br><h3><center>Please enter Valid details</center></h3>");
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
