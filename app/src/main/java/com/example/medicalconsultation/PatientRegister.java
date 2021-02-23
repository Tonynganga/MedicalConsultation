package com.example.medicalconsultation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class PatientRegister extends AppCompatActivity {

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

        //hooks
        mEtName = findViewById(R.id.etPatientName);
        mEtAge = findViewById(R.id.etPatientAge);
        mEtLocation = findViewById(R.id.etPatientLocation);
        mEtEmail = findViewById(R.id.etPatientEmail);
        mEtPassword = findViewById(R.id.etPatientPassword);

        radioGroup = findViewById(R.id.radioGroup);
        mBtnRegister = findViewById(R.id.buttonRegister);
        progressBar = findViewById(R.id.progressBar);

        radioGroup.clearCheck();

        fAuth = FirebaseAuth.getInstance();

        mBtnRegister.setOnClickListener(v->{
            String name = mEtName.getText().toString().trim();
            String age = mEtAge.getText().toString().trim();
            String location = mEtLocation.getText().toString().trim();
            String email = mEtEmail.getText().toString().trim();
            String password = mEtPassword.getText().toString().trim();
            String gender;

            if(name.length() == 0){
                mEtName.setError("Name is Required");
                mEtName.requestFocus();
                return;
            }

            if(email.length() == 0){
                mEtEmail.setError("Username is Required");
                mEtEmail.requestFocus();
                return;
            }

            if(password.length() == 0){
                mEtPassword.setError("Password is Required");
                mEtPassword.requestFocus();
                return;
            }

            if(age.length() == 0){
                mEtAge.setError("Age is Required");
                mEtAge.requestFocus();
                return;
            }

            if(location.length() == 0){
                mEtLocation.setError("Username is Required");
                mEtLocation.requestFocus();
                return;
            }


            int selectedId = radioGroup.getCheckedRadioButtonId();
            genderRadioButton = findViewById(selectedId);

            if(selectedId==-1){
                Toast.makeText(getApplication(),"Select Gender", Toast.LENGTH_SHORT).show();
                return;
            }else {
                gender = genderRadioButton.getText().toString();
            }

            progressBar.setVisibility(View.VISIBLE);

            fAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Patient patient = new Patient(name, location, gender, Integer.parseInt(age));

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(patient).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void>task) {
                                        progressBar.setVisibility(View.GONE);
                                        if(task.isSuccessful()){
                                            Toast.makeText(getApplicationContext(), "User has been registered successfully",Toast.LENGTH_LONG).show();
                                        }else{
                                            Toast.makeText(getApplicationContext(), "User has not registered successfully. Try again!",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            } else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }
}