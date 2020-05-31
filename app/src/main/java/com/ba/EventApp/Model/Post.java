package com.ba.EventApp.Model;

public class Post {
    private String postid;
    private String postimage;
    private String description;
    private String publisher;
    private String location;
    private String date;
    private String time;
    private String type;


    public Post(String postid, String postimage, String description, String publisher, String location, String date
    , String time , String type) {
        this.postid = postid;
        this.postimage = postimage;
        this.description = description;
        this.publisher = publisher;
        this.location = location;
        this.date = date;
        this.time = time;
        this.type = type;
    }

    public Post() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getPostimage() {
        return postimage  = "https://firebasestorage.googleapis.com/v0/b/sarajevosportapp.appspot.com/o/mzeeei.jpg?alt=media&token=104c2f61-48ad-4bc3-ab80-993ff24d5725";
    }

    public void setPostimage(String postimage) {
        this.postimage = postimage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
