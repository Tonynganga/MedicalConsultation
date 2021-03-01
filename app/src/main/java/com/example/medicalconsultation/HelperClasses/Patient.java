package com.example.medicalconsultation.HelperClasses;

import java.io.Serializable;

public class Patient implements Serializable {


    private String name;
    private String email;
    private String location;
    private String gender;
    private int age;

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
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
