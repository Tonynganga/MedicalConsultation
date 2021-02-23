package com.example.medicalconsultation.HelperClasses;

public class Doctor {
    String name, location, category, description, type;

    public Doctor() {
    }

    public Doctor(String name, String location, String category, String description) {
        this.name = name;
        this.location = location;
        this.category = category;
        this.description = description;
        this.type = "doctor";
    }
}
