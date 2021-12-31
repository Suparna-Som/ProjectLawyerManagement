package com.group.findyourlawyer.models;

import java.util.Date;

public class Post {
    String title;
    String description;
    String author;
    String time;
    String question;
    String areaOfLaw;
    String user_id;
    String postId;

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", time='" + time + '\'' +
                ", question='" + question + '\'' +
                ", areaOfLaw='" + areaOfLaw + '\'' +
                ", user_id='" + user_id + '\'' +
                ", postId='" + postId + '\'' +
                '}';
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Post() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAreaOfLaw() {
        return areaOfLaw;
    }

    public void setAreaOfLaw(String areaOfLaw) {
        this.areaOfLaw = areaOfLaw;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}