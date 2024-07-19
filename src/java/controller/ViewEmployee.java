/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CategoryDAO;
import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Category;
import model.User;

/**
 *
 * @author pc
 */
public class ViewEmployee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("role") == null) {
            response.sendRedirect("Login");
            return;
        }
        //Is Admin
        if (session.getAttribute("role").equals("admin") || session.getAttribute("role").equals("manager")) {
            String username = request.getParameter("id");
            UserDAO uDAO = new UserDAO();
            User user = uDAO.findEmployeeByUsername(username);
            request.setAttribute("user", user);
            request.getRequestDispatcher("ViewEmployee.jsp").forward(request, response);
            return;
        }

        //is Employee
        if (session.getAttribute("role").equals("employee")) {
            String username = session.getAttribute("username").toString();
            UserDAO uDAO = new UserDAO();
            User user = uDAO.findUserByUsername(username);

            //list category in sidebar
            CategoryDAO cadao = new CategoryDAO();
            ArrayList<Category> listca = cadao.getListCategory();
            request.setAttribute("listca", listca);
            request.setAttribute("user", user);
            request.getRequestDispatcher("ViewEmployeeByEmployee.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("role") == null) {
            resp.sendRedirect("Login");
            return;
        }
        //Is Admin
        if (session.getAttribute("role").equals("admin")) {
            String username = req.getParameter("id");
            UserDAO uDAO = new UserDAO();
            User user = uDAO.findEmployeeByUsername(username);
            req.setAttribute("user", user);
            req.getRequestDispatcher("ViewEmployee.jsp").forward(req, resp);
            return;
        }

        //is User
        if (session.getAttribute("role").equals("user")) {
            String username = session.getAttribute("username").toString();
            UserDAO uDAO = new UserDAO();
            User user = uDAO.findEmployeeByUsername(username);

            //list category in sidebar
            CategoryDAO cadao = new CategoryDAO();
            ArrayList<Category> listca = cadao.getListCategory();
            req.setAttribute("listca", listca);
            req.setAttribute("user", user);
            req.getRequestDispatcher("ViewEmployeeByEmployee.jsp").forward(req, resp);
        }
    }
}
