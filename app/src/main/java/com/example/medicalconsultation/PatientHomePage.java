package com.example.medicalconsultation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.medicalconsultation.HelperClasses.PatientPostAdapter;
import com.example.medicalconsultation.HelperClasses.PatientPostHelperClass;

import java.util.ArrayList;

public class PatientHomePage extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home_page);

        recyclerView = findViewById(R.id.recyclerView);

        patientsPostRecycler();
    }

    private void patientsPostRecycler() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<PatientPostHelperClass> patientPostHelperClasses =new ArrayList<>();
        patientPostHelperClasses.add(new PatientPostHelperClass("Post One", "This is a sample description one"));
        patientPostHelperClasses.add(new PatientPostHelperClass("Post Two", "This is a sample description Two"));
        patientPostHelperClasses.add(new PatientPostHelperClass("Post Three", "This is a sample description Three"));
        patientPostHelperClasses.add(new PatientPostHelperClass("Post Four", "This is a sample description Four"));
        patientPostHelperClasses.add(new PatientPostHelperClass("Post Five", "This is a sample description Five"));
        patientPostHelperClasses.add(new PatientPostHelperClass("Post Six", "This is a sample description Six"));
        patientPostHelperClasses.add(new PatientPostHelperClass("Post Seven", "This is a sample description Seven"));

        PatientPostAdapter adapter = new PatientPostAdapter(patientPostHelperClasses);
        recyclerView.setAdapter(adapter);
    }
}