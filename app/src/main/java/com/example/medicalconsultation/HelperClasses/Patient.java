package com.example.medicalconsultation.HelperClasses;

public class Patient {
    String name;
    String email;
    String location;
    String gender;
    int age;

    public Patient() {
    }

    public Patient(String name,String email, String location, String gender, int age) {
        this.name = name;
        this.email=email;
        this.location = location;
        this.gender = gender;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }
}
