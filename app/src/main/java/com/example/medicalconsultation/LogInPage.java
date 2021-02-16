package com.example.medicalconsultation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import static com.example.medicalconsultation.MainActivity.APP_USER;
import static com.example.medicalconsultation.MainActivity.USER_PATIENT;

public class LogInPage extends AppCompatActivity {


    private Button mButtonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);
        Intent myIntent = getIntent();
        String user=(String)myIntent.getStringExtra(APP_USER);
        mButtonRegister = (Button)findViewById(R.id.button_register);
        mButtonRegister.setOnClickListener(v -> {
            if(user.equals(USER_PATIENT)){
                Intent intent = new Intent(getApplicationContext(), PatientRegister.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(getApplicationContext(), DoctorRegister.class);
                startActivity(intent);
            }


        });

    }
}