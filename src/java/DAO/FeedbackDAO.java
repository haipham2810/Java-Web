/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import dbConnect.DBContext;
import model.Book;
import model.Feedback;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FeedbackDAO extends DBContext {

    public ArrayList<Feedback> getListFeedbackByPage(ArrayList<Feedback> list, int start, int end) {
        ArrayList<Feedback> arr = new ArrayList<>();
        for (int i = start; i < end; ++i) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public ArrayList<Feedback> getListFeedback() {
        ArrayList<Feedback> list = new ArrayList<>();
        String sql = "select * from feedback";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Feedback fb = new Feedback(rs.getInt(1),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(2));
                list.add(fb);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void insertFeedback(Feedback fb) {
        String sql = "insert into feedback([user_id], title, [content]) values (?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fb.getUsername());
            st.setString(2, fb.getTitle());
            st.setString(3, fb.getConnent());
            st.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deleteFeedback(int id) {
        String sql = "DELETE FROM [dbo].[feedback]\n"
                + "      WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            st.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
