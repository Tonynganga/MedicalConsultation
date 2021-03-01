package com.example.medicalconsultation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.medicalconsultation.HelperClasses.CommentsAdapter;
import com.example.medicalconsultation.HelperClasses.PatientPostAdapter;

import static com.example.medicalconsultation.HelperClasses.PatientPostAdapter.PROBLEM_DESCRIPTION;
import static com.example.medicalconsultation.HelperClasses.PatientPostAdapter.PROBLEM_ID;
import static com.example.medicalconsultation.MainActivity.USER_PATIENT;

public class CommentSection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_section);
        TextView tvDesc=findViewById(R.id.tvDescOfProb);
        Intent myIntent = getIntent();
        tvDesc.setText(myIntent.getStringExtra(PROBLEM_DESCRIPTION));
        String problemId=myIntent.getStringExtra(PROBLEM_ID);
        RecyclerView mRecyclerView = findViewById(R.id.commentRecylerView);
//        btnLogOut = findViewById(R.id.logOutBtn);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        CommentsAdapter adapter = new CommentsAdapter(this,problemId);
        mRecyclerView.setAdapter(adapter);
    }
}