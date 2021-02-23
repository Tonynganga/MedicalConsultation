package com.example.medicalconsultation.HelperClasses;

public class Patient {
    String name, location, gender, type;
    int age;

    public Patient() {
    }

    public Patient(String name, String location, String gender, int age) {
        this.name = name;
        this.location = location;
        this.gender = gender;
        this.age = age;
        this.type="patient";
    }
}
