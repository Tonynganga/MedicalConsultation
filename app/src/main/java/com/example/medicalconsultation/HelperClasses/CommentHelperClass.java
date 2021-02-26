package com.example.medicalconsultation.HelperClasses;

public class CommentHelperClass {
    String title, description;

    public CommentHelperClass(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
