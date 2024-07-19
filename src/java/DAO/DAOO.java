/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import dbConnect.DBContext;
import model.TopBook;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAOO extends DBContext{

    public ArrayList<TopBook> getTopBook() {
        ArrayList<TopBook> list = new ArrayList<>();
        String sql = "select [name],A.Total from (select top 5 book_id, count(book_id) as Total from borrower where status != 'processing' group by book_id  order by Total desc) A\n"
                + "join book \n"
                + "on A.book_id= book.id";
        try {
            PreparedStatement st=connection.prepareStatement(sql);
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                TopBook tb= new TopBook(rs.getString(1), rs.getInt(2));
                list.add(tb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<TopBook> getTopUser() {
        ArrayList<TopBook> list = new ArrayList<>();
        String sql = "select top 5 username, count(username) as Total from borrower where status != 'processing' group by username  order by Total desc";
        try {
            PreparedStatement st=connection.prepareStatement(sql);
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                TopBook tb= new TopBook(rs.getString(1), rs.getInt(2));
                list.add(tb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
