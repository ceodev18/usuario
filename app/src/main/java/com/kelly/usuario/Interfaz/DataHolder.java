package com.kelly.usuario.Interfaz;

/**
 * Created by KELLY on 17-Jul-17.
 */

public class DataHolder {
    private String name;
    private String phone;
    private String address;
    public String getData() {return getName();}
    public void setData(String name) {
        this.setName(name);}

    private static final DataHolder holder = new DataHolder();
    public static DataHolder getInstance() {return holder;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}