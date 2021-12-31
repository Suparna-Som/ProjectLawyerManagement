package com.group.findyourlawyer.models;

public class userModel {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "userModel{" +
                "name='" + name + '\'' +
                '}';
    }
}
