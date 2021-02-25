package com.example.medicalconsultation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.medicalconsultation.HelperClasses.PatientPostAdapter.PROBLEM_DESCRIPTION;

public class CommentSection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_section);
        TextView tvDesc=findViewById(R.id.tvDescOfProb);
        Intent myIntent = getIntent();
        tvDesc.setText(myIntent.getStringExtra(PROBLEM_DESCRIPTION));
    }
}