/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.UserDAO;
import model.User;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ConfirmOTP extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDAO udao = new UserDAO();
        if (session.getAttribute("OTPCode") == null) {
            request.setAttribute("message", "*OTP is over time, try again");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
            return;
        }
        int code = Integer.parseInt(request.getParameter("codeOTP"));
        int otpCode = (int) session.getAttribute("OTPCode");
        User user = (User) session.getAttribute("userRegister");
        if (code != otpCode) {
            request.setAttribute("messConfirmOTP", "*Icorrect OTP, try again");
            request.getRequestDispatcher("ConfirmOTP.jsp").forward(request, response);
            return;
        }

        udao.insertUser(user);
        request.setAttribute("messagRegister", "*Register successfull");
        request.getRequestDispatcher("Login.jsp").forward(request, response);
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
