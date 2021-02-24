package com.example.medicalconsultation.HelperClasses;

public class Doctor {
    String doctorname;
    String doctorlocation;
    String doctoremail;
    String doctordescription;
    String doctorphone;

    public Doctor() {
    }

    public Doctor(String doctorname, String doctoremail, String doctorlocation, String doctordescription, String doctorphone) {
        this.doctorname = doctorname;
        this.doctoremail = doctoremail;
        this.doctorlocation = doctorlocation;
        this.doctordescription = doctordescription;
        this.doctorphone = doctorphone;
    }
    public String getDoctorname() {
        return doctorname;
    }

    public String getDoctoremail() {
        return doctoremail;
    }

    public String getDoctorlocation() {
        return doctorlocation;
    }

    public String getDoctordescription() {
        return doctordescription;
    }

    public String getDoctorphone(){return doctorphone;}
}
