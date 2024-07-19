/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.BookDAO;
import DAO.BorrowerDAO;
import model.Book;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public class ConfirmBorrow extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Book> books = (List<Book>) session.getAttribute("CartBooks");
        BookDAO bookdao= new BookDAO();
        BorrowerDAO bdao = new BorrowerDAO();
        for (Book book : books) {
            book.setCurrent(book.getCurrent() - 1);
            bookdao.updateBook(book);
            bdao.insertBorrower(session.getAttribute("username").toString(), book.getBookid()+"");
        }
        session.removeAttribute("CartBooks");
        response.sendRedirect("/Library");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
