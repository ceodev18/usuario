package com.kelly.usuario.Classes;

/**
 * Created by KELLY on 16-Jul-17.
 */

public class User {
    private String address;
    private String name;
    private String password;
    public User(String address, String name, String password){
        this.address=address;
        this.name=name;
        this.password=password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
