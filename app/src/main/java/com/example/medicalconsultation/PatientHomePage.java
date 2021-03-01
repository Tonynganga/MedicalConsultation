package com.example.medicalconsultation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.medicalconsultation.HelperClasses.PatientPostAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.medicalconsultation.MainActivity.APP_USER;
import static com.example.medicalconsultation.MainActivity.USER_PATIENT;

public class PatientHomePage extends AppCompatActivity {

    RecyclerView mRecyclerView;
    Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home_page);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientHomePage.this,PatientProblem.class);
                startActivity(intent);
            }
        });

        mRecyclerView = findViewById(R.id.recyclerView);
        btnLogOut = findViewById(R.id.logOutBtn);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        PatientPostAdapter adapter = new PatientPostAdapter(this,USER_PATIENT);
        mRecyclerView.setAdapter(adapter);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LogInPage.class);
                intent.putExtra(APP_USER,USER_PATIENT);
                startActivity(intent);
                finish();
            }
        });
    }


}