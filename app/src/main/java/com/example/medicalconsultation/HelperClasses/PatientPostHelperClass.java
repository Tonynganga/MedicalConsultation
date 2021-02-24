package com.example.medicalconsultation.HelperClasses;

public class PatientPostHelperClass {
    String title, description;

    public PatientPostHelperClass(String title, String description) {
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
