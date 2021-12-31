package com.group.findyourlawyer.models;

public class FeedbackModel {
    String feedback;
    String time;
    String reply;
    float rating;
    String username;

    @Override
    public String toString() {
        return "FeedbackModel{" +
                "feedback='" + feedback + '\'' +
                ", time='" + time + '\'' +
                ", reply='" + reply + '\'' +
                ", rating=" + rating +
                ", username='" + username + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public FeedbackModel() {
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
