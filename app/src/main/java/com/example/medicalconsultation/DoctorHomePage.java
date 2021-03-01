package com.example.medicalconsultation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalconsultation.HelperClasses.PatientPostAdapter;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.medicalconsultation.MainActivity.APP_USER;
import static com.example.medicalconsultation.MainActivity.USER_DOCTOR;
import static com.example.medicalconsultation.MainActivity.USER_PATIENT;

public class DoctorHomePage extends AppCompatActivity {
    RecyclerView mRecyclerView;
    Button btnLogOut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);

        mRecyclerView = findViewById(R.id.recyclerView);
        btnLogOut = findViewById(R.id.logOutBtn);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        PatientPostAdapter adapter = new PatientPostAdapter(this,USER_DOCTOR);
        mRecyclerView.setAdapter(adapter);


        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LogInPage.class);
                intent.putExtra(APP_USER,USER_DOCTOR);
                startActivity(intent);
                finish();
            }
        });

    }

}