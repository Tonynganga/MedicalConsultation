package com.example.medicalconsultation.HelperClasses;

public class Comment {
    private String comment;
    private String id;
    private String docId;
    private String imgUri;
    public Comment(){

    }


    public Comment(String comment,String docId) {
        this.comment = comment;
        this.docId=docId;

    }
    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }



}
