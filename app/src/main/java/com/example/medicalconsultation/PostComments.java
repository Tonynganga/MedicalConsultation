package com.example.medicalconsultation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.medicalconsultation.HelperClasses.PatientPostAdapter.PROBLEM_DESCRIPTION;

public class PostComments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comments);
        Intent myIntent =getIntent();
        String description=myIntent.getStringExtra(PROBLEM_DESCRIPTION);
        TextView probDesc=findViewById(R.id.tvDescForDoc);
        probDesc.setText(description);
    }
}