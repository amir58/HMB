package com.gp.hmb.model;

public class User {

    private String motherName, id, password, address, phoneNumber, nationality;

    public User(String motherName, String id, String password, String address, String phoneNumber, String nationality) {
        this.motherName = motherName;
        this.id = id;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.nationality = nationality;
    }

    public User() {
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
