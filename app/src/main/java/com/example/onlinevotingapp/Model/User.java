package com.example.onlinevotingapp.Model;

import com.google.firebase.Timestamp;

public class User {
    private String fullname;
    private String email;
    private String password;
    private String dob;
    private String gender;
    private String role;
    private String mobile;
    private Timestamp createdTimestamp;

    public User() {
    }

    public User(String fullname, String email, String password, String dob, String gender, String role, String mobile, Timestamp createdTimestamp) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.role = role;
        this.mobile = mobile;
        this.createdTimestamp = createdTimestamp;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Timestamp getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
}
