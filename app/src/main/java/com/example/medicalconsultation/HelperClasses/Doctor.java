package com.example.medicalconsultation.HelperClasses;

import java.io.Serializable;

public class Doctor implements Serializable {


    private String id;
    private String doctorname;
    private String doctorlocation;
    private String doctoremail;
    private String doctordescription;
    private String doctorphone;


    private String imgUri;

    public Doctor() {
    }

    public Doctor(String doctorname, String doctoremail, String doctorlocation, String doctordescription, String doctorphone) {
        this.doctorname = doctorname;
        this.doctoremail = doctoremail;
        this.doctorlocation = doctorlocation;
        this.doctordescription = doctordescription;
        this.doctorphone = doctorphone;
    }

    public Doctor withId(String id){
        this.id=id;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public String getDoctoremail() {
        return doctoremail;
    }

    public String getImgUri() {
        return imgUri;
    }

    public String getDoctorlocation() {
        return doctorlocation;
    }

    public String getDoctordescription() {
        return doctordescription;
    }

    public String getDoctorphone(){return doctorphone;}

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public void setDoctorlocation(String doctorlocation) {
        this.doctorlocation = doctorlocation;
    }

    public void setDoctoremail(String doctoremail) {
        this.doctoremail = doctoremail;
    }

    public void setDoctordescription(String doctordescription) {
        this.doctordescription = doctordescription;
    }

    public void setDoctorphone(String doctorphone) {
        this.doctorphone = doctorphone;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }
}
