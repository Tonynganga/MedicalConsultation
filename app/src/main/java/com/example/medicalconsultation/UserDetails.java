package com.example.medicalconsultation;

public class UserDetails {

    public String patientname;
    public String patientage;
    public String patientemail;
    public String patientlocation;
    public String patientgender;

    public UserDetails(){

    }

    public UserDetails(String patientname, String patientage, String patientemail, String patientlocation, String patientgender){

        this.patientname = patientname;
        this.patientage = patientage;
        this.patientemail= patientemail;
        this.patientgender = patientgender;
        this.patientlocation = patientlocation;
    }
    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public String getPatientage() {
        return patientage;
    }

    public void setPatientage(String patientage) {
        this.patientage = patientage;
    }

    public String getPatientemail() {
        return patientemail;
    }

    public void setPatientemail(String patientemail) {
        this.patientemail = patientemail;
    }

    public String getPatientlocation() {
        return patientlocation;
    }

    public void setPatientlocation(String patientlocation) {
        this.patientlocation = patientlocation;
    }

    public String getPatientgender() {
        return patientgender;
    }

    public void setPatientgender(String patientgender) {
        this.patientgender = patientgender;
    }

}
