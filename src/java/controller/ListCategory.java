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
import java.util.HashMap;
import java.util.Map;
import model.Book;
import model.Category;


public class ListCategory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("role") == null || !session.getAttribute("role").equals("admin")) {
            response.sendRedirect("Login");
            return;
        }
        CategoryDAO ctD = new CategoryDAO();
        ArrayList<Category> listAllCategory = ctD.getListCategory();
        int total = listAllCategory.size();
        int bookPerPage= 5;
        int numberOfPage= (total%bookPerPage== 0)? (total/bookPerPage): (total/bookPerPage+ 1); //Số trang
        int page;
        String xpage= request.getParameter("page");
        if(xpage== null){
            page= 1;
        } else{
            page= Integer.parseInt(xpage);
        }
        int start= (page-1)* bookPerPage;
        int end= Math.min((page)*bookPerPage, total);
        ArrayList<Category> list= ctD.getListCategoryByPage(listAllCategory, start, end);

        request.setAttribute("listCategory", list);
        request.setAttribute("page", page);
        request.setAttribute("numberOfPage", numberOfPage);
        request.getRequestDispatcher("ViewCategory.jsp").forward(request, response);
    } 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }
}
