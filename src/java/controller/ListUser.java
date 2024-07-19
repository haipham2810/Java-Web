/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.UserDAO;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.Book;

public class ListUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("role") == null || !session.getAttribute("role").equals("admin")) {
            resp.sendRedirect("Login");
            return;
        }
        UserDAO uDAO = new UserDAO();
        ArrayList<User> list = uDAO.getAllUserByRole(0);
        int total = list.size();
        int bookPerPage= 5;
        int numberOfPage= (total%bookPerPage== 0)? (total/bookPerPage): (total/bookPerPage+ 1); //Số trang
        int page;
        String xpage= req.getParameter("page");
        if(xpage== null){
            page= 1;
        } else{
            page= Integer.parseInt(xpage);
        }
        int start= (page-1)* bookPerPage;
        int end= Math.min((page)*bookPerPage, total);
        ArrayList<User> list1 = uDAO.getListUserByPage(list, start, end);
        req.setAttribute("listUserRole", list);
        req.setAttribute("listUser", list1);
        req.setAttribute("page", page);
        req.setAttribute("numberOfPage", numberOfPage);
        req.getRequestDispatcher("ListUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
