/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Category;

public class AddCategoryNew extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("role") == null || !session.getAttribute("role").equals("admin")) {
            response.sendRedirect("Login");
            return;
        }
        request.getRequestDispatcher("AddCategoryNew.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("role") == null || !session.getAttribute("role").equals("admin")) {
            response.sendRedirect("Login");
            return;
        }
        try {
            int category_id = Integer.parseInt(request.getParameter("category_id"));
            String category_name = request.getParameter("category_name");
            Category newCategory = new Category(category_id, category_name);
            CategoryDAO ctD = new CategoryDAO();
            if (ctD.getCategoryById(category_id) != null) {
                CategoryDAO cDAO = new CategoryDAO();
                ArrayList<Category> list = cDAO.getListCategory();
                request.setAttribute("list", list);
                request.setAttribute("category", newCategory);
                request.setAttribute("mess", "CategoryId really exsit");
                request.getRequestDispatcher("AddCategoryNew.jsp").forward(request, response);
                return;
            } else {
                ctD.addCategoryNew(category_id, category_name);
                response.sendRedirect("ListCategory");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
