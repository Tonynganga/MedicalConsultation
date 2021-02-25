package com.example.medicalconsultation.HelperClasses;

public class PatientPost {

    public String userId;
    public String id;
    public String patientProblem;
    public String category;
    public PatientPost(){
    }
    public PatientPost(String userId, String patientProblem, String category) {
        this.userId = userId;
        this.patientProblem = patientProblem;
        this.category = category;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPatientProblem(String patientProblem) {
        this.patientProblem = patientProblem;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getUserId() {
        return userId;
    }

    public String getPatientProblem() {
        return patientProblem;
    }

    public String getCategory() {
        return category;
    }

    public PatientPost withId(String id){
        setId(id);
        return this;
    }

}
