package com.example.medicalconsultation.HelperClasses;

public class Doctor {
    private String doctorname;
    private String doctorlocation;
    private String doctoremail;
    private String doctordescription;
    private String doctorphone;
    private String imgUrl;

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

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
