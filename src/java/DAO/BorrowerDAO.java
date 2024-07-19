/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import dbConnect.DBContext;
import model.Book;
import model.Borrower;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BorrowerDAO extends DBContext{

    public ArrayList<Borrower> getListBorrowerByPage(ArrayList<Borrower> list, int start, int end) {
        ArrayList<Borrower> arr = new ArrayList<>();
        for (int i = start; i < end; ++i) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public void deleteBorrower(String id) {
        String sql = "DELETE FROM [borrower] WHERE id = '" + id + "'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Borrower> getBorrowerByStatusAndUsername(String status, String username) {
        String sql = "select * from borrower where status= ? and username=?";
        ArrayList<Borrower> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            st.setString(2, username);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Borrower b = new Borrower(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));
                if (!status.equals("processing")) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = dateFormat.parse(rs.getString(5));
                    Date check = new Date();
                    if (!date.after(check)) {
                        b.setLate(true);
                    }
                }
                list.add(b);
            }
            st.close();
            rs.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Borrower> getBorrowerByStatus(String status) {
        ArrayList<Borrower> list = new ArrayList<>();
        String sql = "select * from borrower where [status]= ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Borrower b = new Borrower(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));
                if (!status.equals("processing")) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = dateFormat.parse(rs.getString(5));
                    Date check = new Date();
                    if (!date.after(check)) {
                        b.setLate(true);
                    }
                }
                list.add(b);
            }
            st.close();
            rs.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Borrower> getBorrowerByStatusAndUserId(String status, String username) {
        ArrayList<Borrower> list = new ArrayList<>();
        String sql = "select * from borrower where [status]= ? and [username]= ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            st.setString(2, username);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Borrower b = new Borrower(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));
                list.add(b);
            }
            st.close();
            rs.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return list;
    }

    public Borrower getBorrowerById(String id) {
        String sql = "select * from [borrower] WHERE id = '" + id + "'";
        Borrower b = new Borrower();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                b = new Borrower(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));
            }
            st.close();
            rs.close();
            return b;
        } catch (Exception e) {
        }
        return null;
    }

    public void updateBorrower(Borrower borrower) {
        String sql = "UPDATE borrower SET [username]=?, book_id=?, form=?, [to]=?, [status]=? WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, borrower.getUsername());
            st.setInt(2, borrower.getBookid());
            st.setString(3, borrower.getBorrow_from());
            st.setString(4, borrower.getBorrow_to());
            st.setString(5, borrower.getStatus());
            st.setInt(6, borrower.getId());
            st.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertBorrower(String username, String bookid) {
        String sql = "insert into borrower (username, book_id, [status])\n"
                + "values (?, ?,'processing' )";
        DBContext db = new DBContext();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, bookid);
            st.executeQuery();
        } catch (Exception e) {
            System.out.println("insertBorrower: " + e.getMessage());
        }
    }
}
