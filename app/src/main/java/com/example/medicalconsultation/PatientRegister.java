package com.example.medicalconsultation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

public class PatientRegister extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText edtname, edtemail, edtpassword,edtage, edtlocation;
    private RadioGroup gender;
    private RadioButton selectedRadioButton;
    private Button edtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);
        edtname = findViewById(R.id.etPatientName);
        edtemail = findViewById(R.id.etPatientEmail);
        edtpassword = findViewById(R.id.etPatientPassword);
        edtage = findViewById(R.id.etPatientAge);
        edtlocation = findViewById(R.id.etPatientLocation);

        gender = findViewById(R.id.radioGroupgender);
        edtRegister = findViewById(R.id.buttonpatientregister);

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

                UserDetails user = new UserDetails(patientage,patientemail, patientname, patientgender, patientlocation);

                FirebaseUtils.registerUser(user);


//                mAuth.createUserWithEmailAndPassword(patientemail, patientpassword)
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    UserDetails user = new UserDetails(patientage,patientemail, patientname, patientgender, patientlocation);
//                                    FirebaseDatabase.getInstance().getReference("Users")
//                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            if (task.isSuccessful()) {
//                                                Toast.makeText(PatientRegister.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
//
//                                            } else {
//                                                Toast.makeText(PatientRegister.this, "User has not registered successfully. Try again!", Toast.LENGTH_LONG).show();
//                                            }
//                                        }
//
//                                    });
//                                } else {
//                                    Toast.makeText(PatientRegister.this, "Failed to register the user", Toast.LENGTH_LONG).show();
//
//                                }
//                        }
//                });
//
            }
        });



    }
}