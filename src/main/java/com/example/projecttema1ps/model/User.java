package com.example.projecttema1ps.model;

public class User extends Entity {
    private int id;
    private String role;
    private String username;
    private String password;

    public User() { }

    public User(int id, String role, String username, String password) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public User(String name, int id, String role, String username, String password) {
        super(name);
        this.id = id;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", role='" + role + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}' + "\n";
    }

    public String getRole() {
        return role;
    }

    public void setRole(String type) {
        this.role = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
