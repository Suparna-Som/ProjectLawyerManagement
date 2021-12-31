package com.group.findyourlawyer.models;

public class Model {
    String name = " ", address = " ", email = " ", gender = " ", phoneNo = " ", dob = " ", lawPractices = " ", image = "", id = "";
    String createdOn = " ";
    String mAccountType, service, courts, about, edu, exp;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Model() {
    }


    public Model(String name, String address, String email, String gender, String phoneNo, String dob, String lawPractices, String image, String id, String createdOn, String mAccountType, String service, String courts, String about, String edu, String exp) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.dob = dob;
        this.lawPractices = lawPractices;
        this.image = image;
        this.id = id;
        this.createdOn = createdOn;
        this.mAccountType = mAccountType;
        this.service = service;
        this.courts = courts;
        this.about = about;
        this.edu = edu;
        this.exp = exp;
    }

    public String getId() {
        return id;
    }

    public String getmAccountType() {
        return mAccountType;
    }

    public void setmAccountType(String mAccountType) {
        this.mAccountType = mAccountType;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getCourts() {
        return courts;
    }

    public void setCourts(String courts) {
        this.courts = courts;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }


    public void setId(String id) {
        this.id = id;
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
        this.lawPractices = "Law Practices : \n" + lawPractices;
    }

    public String getAccountType() {
        return mAccountType;
    }

    public void setAccountType(String mAccountType) {
        this.mAccountType = mAccountType;
    }
}
