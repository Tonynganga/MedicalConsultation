package com.example.medicalconsultation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Patterns;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.medicalconsultation.HelperClasses.Patient;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.medicalconsultation.MainActivity.APP_USER;
import static com.example.medicalconsultation.MainActivity.USER_PATIENT;


public class PatientRegister extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText edtname, edtemail, edtpassword,edtage, edtlocation;
    private RadioGroup gender;
    private RadioButton selectedRadioButton;
    private Button edtRegister, loginback;

    //variables
    EditText mEtName, mEtAge, mEtLocation, mEtEmail, mEtPassword;
    RadioGroup radioGroup;
    RadioButton genderRadioButton;
    Button mBtnRegister;
    ProgressBar progressBar;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);

        edtname = findViewById(R.id.etPatientName);
        edtemail = findViewById(R.id.etPatientEmail);
        edtpassword = findViewById(R.id.etPatientPassword);
        edtage = findViewById(R.id.etPatientAge);
        edtlocation = findViewById(R.id.etPatientLocation);

        gender = findViewById(R.id.radioGroup);
        edtRegister = findViewById(R.id.buttonRegister);
        loginback = (Button)findViewById(R.id.buttonloginback);




        mAuth = FirebaseAuth.getInstance();

        edtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String patientname = edtname.getText().toString().trim();
                String patientemail = edtemail.getText().toString().trim();
                String patientpassword = edtpassword.getText().toString().trim();
                String patientage = edtage.getText().toString().trim();
                String patientlocation = edtlocation.getText().toString().trim();
                String patientgender = ((RadioButton)findViewById(gender.getCheckedRadioButtonId())).getText().toString();


                //validate values
                if (patientname.isEmpty()) {
                    edtname.setError("Full Name is required");
                    edtname.requestFocus();
                    return;
                }
                if (patientemail.isEmpty()) {
                    edtemail.setError("Email is required");
                    edtemail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(patientemail).matches()) {
                    edtemail.setError("Please provide a valid email");
                    edtemail.requestFocus();
                    return;
                }
                if (patientpassword.isEmpty()) {
                    edtpassword.setError("Email is required");
                    edtpassword.requestFocus();
                    return;
                }

                if (patientpassword.length() < 6) {
                    edtpassword.setError("The password length must be 6 characters long");
                    edtpassword.requestFocus();
                    return;
                }

                if (patientage.isEmpty()) {
                    edtage.setError("Age is required");
                    edtage.requestFocus();
                    return;
                }
                if (patientlocation.isEmpty()) {
                    edtlocation.setError("Age is required");
                    edtlocation.requestFocus();
                    return;
                }


                Patient patientUser = new Patient(patientname,patientemail,patientlocation,patientgender,Integer.parseInt(patientage));

                mAuth.createUserWithEmailAndPassword(patientemail,patientpassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(PatientRegister.this, "Register Successfull", Toast.LENGTH_LONG).show();
//
                                    FirebaseUtils.registerPatientUser(patientUser);

                                } else {
                                    Toast.makeText(PatientRegister.this, "Failed to register the user", Toast.LENGTH_LONG).show();

                                }
                        }
                });

                loginback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), LogInPage.class);
                        intent.putExtra(APP_USER,USER_PATIENT);
                        startActivity(intent);
                        finish();
                    }
                });

            }
        });




    }
}