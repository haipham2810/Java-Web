/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.CategoryDAO;
import DAO.UserDAO;
import model.Category;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import model.User;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15)    // 15 MB
public class UpdateEmployee extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            if (session.getAttribute("username") == null || session.getAttribute("role") == null) {
                resp.sendRedirect("Login");
                return;
            }
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            Part filePart = request.getPart("avtFile");
            String avt = "avt" + username;
            if (filePart != null && filePart.getSize() > 0) {
                String applicationPath = request.getServletContext().getRealPath("");
                String uploadFilePath = applicationPath + File.separator + "img/avt";

                File uploadDir = new File(uploadFilePath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String fileName = avt + fileExtension;
                String filePath = uploadFilePath + File.separator + fileName;
                avt = "img/avt/" + fileName;
                filePart.write(filePath);
            } else {
                avt = request.getParameter("avt");
            }

            String name = request.getParameter("name");
            boolean sex = Boolean.parseBoolean(request.getParameter("sex"));
            String datebirth = request.getParameter("datebirth");
            String phone = request.getParameter("phone");
            String gmail = request.getParameter("gmail");
            User user = new User(username, password, name, avt, sex, datebirth, phone, gmail);
            UserDAO uDAO = new UserDAO();
            user.setRole(2);
            uDAO.updateUser(user);
            //when admin update
            if (session.getAttribute("role").equals("admin")) {
                resp.sendRedirect("ListEmployee");
                return;
            }
        } catch (Exception ex) {
            session.setAttribute("mess", ex.getMessage());
            resp.sendRedirect("ErrorPage.jsp");
        }
        
    }

}
