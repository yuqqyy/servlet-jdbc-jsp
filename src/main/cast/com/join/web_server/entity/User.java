package com.join.web_server.entity;


public class User {
    private int id;
    private String username;
    private String pwd;
    private String profession;
    private int duration;
    private Long signInTime;
    private Long signOutTime;
    private String fileName;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }


    public Long getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(Long signIn) {
        this.signInTime = signIn;
    }

    public Long  getSignOutTime() {
        return signOutTime;
    }

    public void setSignOutTime(Long signOut) {
        this.signOutTime = signOut;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
