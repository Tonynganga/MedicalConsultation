package com.example.medicalconsultation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import android.os.Bundle;
import android.util.Log;

import com.example.medicalconsultation.HelperClasses.PatientPost;
import com.example.medicalconsultation.HelperClasses.PatientPostAdapter;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.medicalconsultation.FirebaseUtils.mFireStore;
import static com.example.medicalconsultation.FirebaseUtils.mFirebaseAuth;
import static com.example.medicalconsultation.MainActivity.USER_DOCTOR;

public class DoctorHomePage extends AppCompatActivity {
    RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        PatientPostAdapter adapter = new PatientPostAdapter(this,USER_DOCTOR);
        mRecyclerView.setAdapter(adapter);


    }

}