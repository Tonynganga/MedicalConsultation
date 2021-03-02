package com.example.medicalconsultation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalconsultation.HelperClasses.Doctor;
import com.example.medicalconsultation.HelperClasses.PatientPostAdapter;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.medicalconsultation.LogInPage.DOCTOR_DETAILS;
import static com.example.medicalconsultation.MainActivity.APP_USER;
import static com.example.medicalconsultation.MainActivity.USER_DOCTOR;
import static com.example.medicalconsultation.MainActivity.USER_PATIENT;

public class DoctorHomePage extends AppCompatActivity {
    RecyclerView mRecyclerView;
    Button btnLogOut;
    public Doctor mDocDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);
        Intent myIntent = getIntent();

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        PatientPostAdapter adapter = new PatientPostAdapter(this,USER_DOCTOR);
        mRecyclerView.setAdapter(adapter);
        mDocDetails = (Doctor) myIntent.getSerializableExtra(DOCTOR_DETAILS);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.homepage_menu,menu);

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile_menu:
                Intent intent = new Intent(getApplicationContext(), DoctorsProfile.class);
                intent.putExtra(DOCTOR_DETAILS,mDocDetails);
                startActivity(intent);
                return true;
            case R.id.logout_menu:
                FirebaseAuth.getInstance().signOut();
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }

    }
}