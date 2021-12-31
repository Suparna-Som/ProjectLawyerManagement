package com.chetan.findyourlawyer.models;

public class Model {
    String name=" ", address=" ", email=" " ,gender= " ", phoneNo= " ", dob= " ",lawPractices=" ";
    String createdOn=" ";

    public Model() {
    }

    public Model(String name, String address, String email, String gender, String phoneNo, String dob, String lawPractices) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.dob = dob;
        this.lawPractices = lawPractices;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getLawPractices() {
        return lawPractices;
    }

    public void setLawPractices(String lawPractices) {
        this.lawPractices = lawPractices;
    }
}
