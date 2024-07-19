/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Feedback{
    private int id;
    private String title;
    private String connent;
    private String username;

    public Feedback() {
    }

    public Feedback(String title, String connent, String username) {
        this.title = title;
        this.connent = connent;
        this.username = username;
    }

    public Feedback(int id, String title, String connent, String username) {
        this.id = id;
        this.title = title;
        this.connent = connent;
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConnent() {
        return connent;
    }

    public void setConnent(String connent) {
        this.connent = connent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}

