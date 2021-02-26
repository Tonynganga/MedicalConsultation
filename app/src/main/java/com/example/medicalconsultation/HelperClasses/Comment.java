package com.example.medicalconsultation.HelperClasses;

public class Comment {
    private String comment;
    private String id;
    private int imgurl;

    public Comment(String comment, int imgurl) {
        this.comment = comment;
        this.imgurl = imgurl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }
    public int getImgUrl() {
        return imgurl;
    }



}
