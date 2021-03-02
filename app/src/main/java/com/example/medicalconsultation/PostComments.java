package com.example.medicalconsultation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.medicalconsultation.HelperClasses.Comment;
import com.example.medicalconsultation.HelperClasses.Doctor;
import com.example.medicalconsultation.HelperClasses.PatientPost;

import static com.example.medicalconsultation.FirebaseUtils.mFirebaseAuth;
import static com.example.medicalconsultation.HelperClasses.PatientPostAdapter.PROBLEM_DESCRIPTION;
import static com.example.medicalconsultation.HelperClasses.PatientPostAdapter.PROBLEM_ID;

public class PostComments extends AppCompatActivity {

    private EditText mEtComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comments);
        Intent myIntent =getIntent();
        String description=myIntent.getStringExtra(PROBLEM_DESCRIPTION);
        String problemId=myIntent.getStringExtra(PROBLEM_ID);
        TextView probDesc=findViewById(R.id.tvDescForDoc);
        probDesc.setText(description);
        mEtComment = findViewById(R.id.etAddComment);
        Button mBtPostProb=findViewById(R.id.button2);
        mBtPostProb.setOnClickListener( view -> {
            String comment = mEtComment.getText().toString().trim();
            if(description.length() == 0){
                mEtComment.setError("Enter a Description");
                mEtComment.requestFocus();
                return;
            }
            Comment postComment= new Comment(comment,FirebaseUtils.sUserDoctorDetails.getId());
            if(FirebaseUtils.sUserDoctorDetails.getImgUri()!=null){
                postComment.setImgUri(FirebaseUtils.sUserDoctorDetails.getImgUri());
            }

            FirebaseUtils.addComment(this,postComment,problemId);
            mEtComment.setText("");
        });
    }

}