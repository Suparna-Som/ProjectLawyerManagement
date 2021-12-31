package com.group.findyourlawyer.models;

public class AnswerModel {

    String username;
    String useranswer;
    String accountType;
    String time;

    @Override
    public String toString() {
        return "AnswerModel{" +
                "username='" + username + '\'' +
                ", useranswer='" + useranswer + '\'' +
                ", accountType='" + accountType + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public AnswerModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseranswer() {
        return useranswer;
    }

    public void setUseranswer(String useranswer) {
        this.useranswer = useranswer;
    }

}
