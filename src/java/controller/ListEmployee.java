/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.User;

/**
 *
 * @author pc
 */
public class ListEmployee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("role") == null || !session.getAttribute("role").equals("admin")) {
            response.sendRedirect("Login");
            return;
        }
        UserDAO uDAO = new UserDAO();
        ArrayList<User> list = uDAO.getAllEmployeeByRole();
        int total = list.size();
        int bookPerPage = 5;
        int numberOfPage = (total % bookPerPage == 0) ? (total / bookPerPage) : (total / bookPerPage + 1); //Số trang
        int page;
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start = (page - 1) * bookPerPage;
        int end = Math.min((page) * bookPerPage, total);
        ArrayList<User> list1 = uDAO.getListUserByPage(list, start, end);
        request.setAttribute("listEmployeeByRole", list);
        request.setAttribute("listEmployee", list1);
        request.setAttribute("page", page);
        request.setAttribute("numberOfPage", numberOfPage);
        request.getRequestDispatcher("ListEmployee.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doPost(request, response); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
}
