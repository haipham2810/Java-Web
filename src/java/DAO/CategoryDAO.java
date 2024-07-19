/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import dbConnect.DBContext;
import model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CategoryDAO extends DBContext {
    
    public ArrayList<Category> getListCategoryByPage(ArrayList<Category> list, int start, int end) {
        ArrayList<Category> arr = new ArrayList<>();
        for (int i = start; i < end; ++i) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public void addCategoryNew(int id, String name) {
        String sql = "INSERT INTO [dbo].[category]\n"
                + "           ([id]\n"
                + "           ,[name])\n"
                + "     VALUES(?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, name);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, Category> getMapCategory() {
        Map<Integer, Category> list = new HashMap<>();
        String sql = "select * from category";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getInt(1), rs.getString(2));
                list.put(rs.getInt(1), category);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return list;
    }

    public ArrayList< Category> getListCategory() {
        ArrayList<Category> list = new ArrayList<>();
        String sql = "select * from category";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getInt(1), rs.getString(2));
                list.add(category);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean deleteCategory(int id){
        String sql = "DELETE FROM [dbo].[category]\n"
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
    
    public Category getCategoryById(int id) {
        String sql = "select*from category where id = " + id;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Category c = new Category(id, rs.getString("name"));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
