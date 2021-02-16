package com.example.medicalconsultation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnpatient, btndoctor;
    public static final String APP_USER="state user";
    public static final String USER_PATIENT="patient";
    public static final String USER_DOCTOR="doctor";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnpatient = (Button)findViewById(R.id.buttonpatient);
        btndoctor = (Button)findViewById(R.id.buttondoctor);

        btnpatient.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LogInPage.class);
            intent.putExtra(APP_USER,USER_PATIENT);
            startActivity(intent);
        });

        btndoctor.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LogInPage.class);
            intent.putExtra(APP_USER,USER_DOCTOR);
            startActivity(intent);
        });
    }
}