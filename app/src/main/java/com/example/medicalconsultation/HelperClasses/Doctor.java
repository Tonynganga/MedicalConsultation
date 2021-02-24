package com.example.medicalconsultation.HelperClasses;

public class Doctor {
    String name;
    String location;
    String category;
    String description;

    public Doctor() {
    }

    public Doctor(String name, String location, String category, String description) {
        this.name = name;
        this.location = location;
        this.category = category;
        this.description = description;
    }
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}
