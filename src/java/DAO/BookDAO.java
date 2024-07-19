/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import dbConnect.DBContext;
import model.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookDAO extends DBContext {
    
    public ArrayList<Book> getListBookByPage(ArrayList<Book> list, int start, int end) {
        ArrayList<Book> arr = new ArrayList<>();
        for (int i = start; i < end; ++i) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public Map<Integer, Book> getMapBook() {
        Map<Integer, Book> list = new HashMap<>();
        String sql = "select * from book";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt(1), rs.getString(2),
                        rs.getString(4), rs.getString(3),
                        rs.getInt(5), rs.getString(6),
                        rs.getString(7), rs.getInt(8),
                        rs.getInt(9), rs.getString(10));
                list.put(book.getBookid(), book);
            }
            st.close();
            rs.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<Book> getAllBook() {
        ArrayList<Book> list = new ArrayList<>();
        String sql = "select * from book";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt(1), rs.getString(2),
                        rs.getString(4), rs.getString(3),
                        rs.getInt(5), rs.getString(6),
                        rs.getString(7), rs.getInt(8),
                        rs.getInt(9), rs.getString(10));
                list.add(book);
            }
            st.close();
            rs.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<Book> getBookByName(String name) {
        ArrayList<Book> list = new ArrayList<>();
        String sql = "select * from book where [name] like '%" + name + "%'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt(1), rs.getString(2),
                        rs.getString(4), rs.getString(3),
                        rs.getInt(5), rs.getString(6),
                        rs.getString(7), rs.getInt(8),
                        rs.getInt(9), rs.getString(10));
                list.add(book);
            }
            st.close();
            rs.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public ArrayList<Book> getBookByCategory(int cid) {
        ArrayList<Book> list = new ArrayList<>();
        String sql = "select * from book where category =" + cid;
        DBContext db = new DBContext();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt(1), rs.getString(2),
                        rs.getString(4), rs.getString(3),
                        rs.getInt(5), rs.getString(6),
                        rs.getString(7), rs.getInt(8),
                        rs.getInt(9), rs.getString(10));
                list.add(book);
            }
            st.close();
            rs.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public Book getBookById(int id) {
        String sql = "SELECT * FROM book WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            Book book = null;
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                book = new Book(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getString("img"),
                        rs.getInt("category"),
                        rs.getString("publisher"),
                        rs.getString("language"),
                        rs.getInt("total"),
                        rs.getInt("current"),
                        rs.getString("position")
                );
            }
            rs.close();
            st.close();
            return book;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBook(Book book) {
        String sql = "UPDATE book "
                + "SET [name]=?, img=?, author=?, category=?, publisher=?, [language]=?, total=?, [current]=?, position=? "
                + "WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            // Set parameters
            st.setString(1, book.getName());
            st.setString(2, book.getImg());
            st.setString(3, book.getAuthor());
            st.setInt(4, book.getCategory());
            st.setString(5, book.getPublisher());
            st.setString(6, book.getLanguage());
            st.setInt(7, book.getTotal());
            st.setInt(8, book.getCurrent());
            st.setString(9, book.getPosition());
            st.setInt(10, book.getBookid());

            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertbook(Book book) {
        String sql = "INSERT INTO book (id, [name], img, author, category, publisher, [language], total, [current], position) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, book.getBookid());
            st.setString(2, book.getName());
            st.setString(3, book.getImg());
            st.setString(4, book.getAuthor());
            st.setInt(5, book.getCategory());
            st.setString(6, book.getPublisher());
            st.setString(7, book.getLanguage());
            st.setInt(8, book.getTotal());
            st.setInt(9, book.getCurrent());
            st.setString(10, book.getPosition());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean deleteBook(String id) {
        String sql = "DELETE FROM book WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
            st.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
