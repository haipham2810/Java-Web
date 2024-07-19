/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.BookDAO;
import model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15)    // 15 MB
public class UpdateBook extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null || session.getAttribute("role") == null || !session.getAttribute("role").equals("admin")) {
            resp.sendRedirect("Login");
            return;
        }
        try {
            int bookid = Integer.parseInt(request.getParameter("bookid"));
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            int category = Integer.parseInt(request.getParameter("category"));
            String publisher = request.getParameter("publisher");
            Part filePart = request.getPart("avtFile");
            String avt = "book" + bookid;
            if (filePart != null && filePart.getSize() > 0) {
                String applicationPath = request.getServletContext().getRealPath("");
                String uploadFilePath = applicationPath + File.separator + "img/book";

                File uploadDir = new File(uploadFilePath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String fileName = avt + fileExtension;
                String filePath = uploadFilePath + File.separator + fileName;
                avt = "img/book/"+fileName;
                filePart.write(filePath);
            } else {
                avt = request.getParameter("avt");
            }

            String language = request.getParameter("language");
            int total = Integer.parseInt(request.getParameter("total"));
            int current = Integer.parseInt(request.getParameter("current"));
            String position = request.getParameter("position");

            Book newBook = new Book(bookid, name, author, avt, category, publisher, language, total, current, position);
            BookDAO bDAO = new BookDAO();
            bDAO.updateBook(newBook);
            resp.sendRedirect("ListBook");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
