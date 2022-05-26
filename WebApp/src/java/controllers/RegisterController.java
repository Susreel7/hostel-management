/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.UserDAO;
import dto.UserDTO;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ACER
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SendEmailController";
    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url =ERROR;
        try {        
            String userID = request.getParameter("userID");
            String email = request.getParameter("email");
            String roleID = "US";
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String fullName = request.getParameter("fullName");
            String phone = request.getParameter("phone");
            String idCard= request.getParameter("idCard");
//            Date dateOfBirth = (Date) df.parse(request.getParameter("dateOfBirth"));
            boolean check = true;
            UserDAO dao = new UserDAO();
            boolean checkDuplicate = dao.checkDuplicate(userID);
            boolean checkDuplicateEmail = dao.checkEmailDuplicate(email);
            if (checkDuplicate) {
                check = false;
                request.setAttribute("ERROR_USERID", "User ID have been exist!!");
            }
            if(checkDuplicateEmail){
                check = false; 
                request.setAttribute("ERROR_EMAIL", "Email Already Use!");
            }
            if (!password.equals(confirm)) {
                check = false;
                request.setAttribute("ERROR_CONFIRM", "Please Sure Your Password Match!!");
            }
            if (check) {
                UserDTO user = new UserDTO(userID, password, roleID, fullName,email,phone,idCard,null,0);
                    request.setAttribute("VERIFY_USER", user);
                    url = SUCCESS;
                
            } else {
                request.setAttribute("ERROR", ERROR);
            }
        } catch (Exception e) {
                log("Error at RegisterController: "+e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
