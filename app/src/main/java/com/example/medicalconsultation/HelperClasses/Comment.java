package com.example.medicalconsultation.HelperClasses;

public class Comment {
    private String comment;
    private String id;
    private String problemId;

    public Comment(String comment, String problemId) {
        this.comment = comment;
        this.problemId = problemId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }



    public String getProblemId() {
        return problemId;
    }



}
