package com.example.medicalconsultation.HelperClasses;

public class PatientPost {

    private String userId;
    private String id;
    private String patientProblem;
    private String category;
    private PatientPost(){
    }
    public PatientPost(String userId, String patientProblem, String category) {
        this.userId = userId;
        this.patientProblem = patientProblem;
        this.category = category;
    }
    public String getId() {
        return id;
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
