package com.example.medicalconsultation.HelperClasses;

public class Comment {
    private String comment;
    private String id;

    private int imgurl;
    public Comment(){

    }


    public Comment(String comment) {
        this.comment = comment;


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
