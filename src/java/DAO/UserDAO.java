/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import dbConnect.DBContext;
import model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Role;

public class UserDAO extends DBContext {

    public ArrayList<User> getListUserByPage(ArrayList<User> list, int start, int end) {
        ArrayList<User> arr = new ArrayList<>();
        for (int i = start; i < end; ++i) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public ArrayList<User> getAllUserByRole(int role) {
        ArrayList<User> users = new ArrayList<>();
        String sql = "select*from [USER] where role = " + role;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(role);
                user.setName(rs.getString("name"));
                user.setAvt(rs.getString("avt"));
                user.setSex(rs.getBoolean("sex"));
                user.setDatebirth(rs.getString("datebirth"));
                user.setPhone(rs.getString("phone"));
                user.setGmail(rs.getString("gmail"));
                if (role == 0) {
                    users.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public ArrayList<User> getAllEmployeeByRole() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "select u.username, u.[name], u.avt, u.role as [roleId], u.sex, r.[name] as [Role], u.datebirth, u.phone, u.gmail\n"
                + "from [USER] u JOin [role] r\n"
                + "on u.[role] = r.id\n"
                + "where r.id != 0";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getInt("roleId"));
                user.setName(rs.getString("name"));
                user.setAvt(rs.getString("avt"));
                user.setSex(rs.getBoolean("sex"));
                user.setDatebirth(rs.getString("datebirth"));
                user.setPhone(rs.getString("phone"));
                user.setGmail(rs.getString("gmail"));
                Role r = new Role(rs.getInt("roleId"), rs.getString("Role"));
                user.setRole1(r);
                if (user.getRole() == 2 || user.getRole() == 3) {
                    users.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public User findUser(String username, String password) {
        User user = null;
        String sql = "SELECT *, r.id as roleId , r.name as nameRole "
                + "FROM [USER] u JOin [role] r on u.[role] = r.id where username = ? AND password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
                user.setName(rs.getString("name"));
                user.setAvt(rs.getString("avt"));
                user.setSex(rs.getBoolean("sex"));
                user.setDatebirth(rs.getString("datebirth"));
                user.setPhone(rs.getString("phone"));
                user.setGmail(rs.getString("gmail"));
                Role r = new Role(rs.getInt("roleId"), rs.getString("nameRole"));
                user.setRole1(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User findUserByUsername(String username) {
        User user = null;
        String sql = "SELECT * FROM [USER] WHERE username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(0);
                user.setName(rs.getString("name"));
                user.setAvt(rs.getString("avt"));
                user.setSex(rs.getBoolean("sex"));
                user.setDatebirth(rs.getString("datebirth"));
                user.setPhone(rs.getString("phone"));
                user.setGmail(rs.getString("gmail"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public User findEmployeeByUsername(String username) {
        User user = null;
        String sql = "SELECT * FROM [USER] WHERE username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(2);
                user.setName(rs.getString("name"));
                user.setAvt(rs.getString("avt"));
                user.setSex(rs.getBoolean("sex"));
                user.setDatebirth(rs.getString("datebirth"));
                user.setPhone(rs.getString("phone"));
                user.setGmail(rs.getString("gmail"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void insertUser(User user) {
        String sql = "INSERT INTO dbo.[USER](username,password,role,name,avt,sex,datebirth,phone,gmail) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setInt(3, user.getRole());
            st.setString(4, user.getName());
            if (user.getAvt() == null) {
                st.setString(5, "img/avt/avt1.jpg");
            } else {
                st.setString(5, user.getAvt());
            }
            st.setBoolean(6, user.isSex());
            st.setString(7, user.getDatebirth());
            st.setString(8, user.getPhone());
            st.setString(9, user.getGmail());

            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteUser(String username) {
        String sql = "DELETE FROM [USER] WHERE username = '" + username + "'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        String sql = "UPDATE [USER] "
                + "SET [password]=?, [role]=?, name=?, avt=?, sex=?, datebirth=?, phone=?, gmail=? "
                + "WHERE username=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user.getPassword());
            st.setInt(2, user.getRole());
            st.setString(3, user.getName());
            st.setString(4, user.getAvt());
            st.setBoolean(5, user.isSex());
            st.setString(6, user.getDatebirth());
            st.setString(7, user.getPhone());
            st.setString(8, user.getGmail());
            st.setString(9, user.getUsername());

            st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
