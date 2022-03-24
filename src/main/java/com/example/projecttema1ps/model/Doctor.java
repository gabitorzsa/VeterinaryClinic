package com.example.projecttema1ps.model;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends Entity {
    private int id;
    private String role;
    private String startProgram;
    private String finishProgram;
    private String username;
    private String password;
    private List<String> consults;

    public Doctor() {
        consults = new ArrayList<>();
    }

    public String getStartProgram() {
        return startProgram;
    }

    public void setStartProgram(String startProgram) {
        this.startProgram = startProgram;
    }

    public String getFinishProgram() {
        return finishProgram;
    }

    public void setFinishProgram(String finishProgram) {
        this.finishProgram = finishProgram;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getConsults() {
        return consults;
    }

    public void setConsults(List<String> consults) {
        this.consults = consults;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", startProgram='" + startProgram + '\'' +
                ", finishProgram='" + finishProgram + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", consults=" + consults +
                ", name='" + name + '\'' +
                '}';
    }
}
