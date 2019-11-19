package com.example.paijoov1;

public class Users {

    private int id;
    private String username;
    private String password;
    private String salt;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt()
    {
        return salt;
    }

    public Users(int id, String username, String password, String salt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
    }


}