 package com.example.medicalconsultation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.medicalconsultation.HelperClasses.Doctor;
import com.example.medicalconsultation.HelperClasses.Patient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;


 public class DoctorRegister extends AppCompatActivity  {
     private static final String TAG = "DoctorRegister";
     private EditText edtdname, edtdemail, edtdpassword, edtdphone, edtdlocation, edtddesc;
    private Button docregister;
    private FirebaseAuth mAuth;

    FirebaseAuth fAuth;
    private final String[] mDoctorCategory={"Dentist","Optician","Dermatologist","Pediatrician","Gynaecologist","Gastrologist"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);


        mAuth = FirebaseAuth.getInstance();

        edtdname = findViewById(R.id.etDoctorName);
        edtdemail = findViewById(R.id.etDoctorEmail);
        edtdpassword = findViewById(R.id.etDoctorPassword);
        edtdphone = findViewById(R.id.etDoctorPhone);
        edtdlocation = findViewById(R.id.etDoctorLocation);
        edtddesc = findViewById(R.id.etDoctorDescription);

        docregister = findViewById(R.id.buttondocregister);

        docregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String doctorname = edtdname.getText().toString().trim();
                String doctoremail = edtdemail.getText().toString().trim();
                String doctorpassword = edtdpassword.getText().toString().trim();
                String doctorphone = edtdphone.getText().toString().trim();
                String doctorlocation = edtdlocation.getText().toString().trim();
                String doctordescription= edtddesc.getText().toString().trim();

                //validate values
                if (doctorname.isEmpty()) {
                    edtdname.setError("Full Name is required");
                    edtdname.requestFocus();
                    return;
                }
                if (doctoremail.isEmpty()) {
                    edtdemail.setError("Email is required");
                    edtdemail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(doctoremail).matches()) {
                    edtdemail.setError("Please provide a valid email");
                    edtdemail.requestFocus();
                    return;
                }
                if (doctorpassword.isEmpty()) {
                    edtdpassword.setError("Password is required");
                    edtdpassword.requestFocus();
                    return;
                }

                if (doctorpassword.length() < 6) {
                    edtdpassword.setError("The password length must be 6 characters long");
                    edtdpassword.requestFocus();
                    return;
                }

                if (doctorphone.isEmpty()) {
                    edtdphone.setError("Phone Number is required");
                    edtdphone.requestFocus();
                    return;
                }
                if (!Patterns.PHONE.matcher(doctorphone).matches()){
                    edtdphone.setError("Please provide a valid phone number");
                    edtdphone.requestFocus();
                }
                if (doctorlocation.isEmpty()) {
                    edtdlocation.setError("Location is required");
                    edtdlocation.requestFocus();
                    return;
                }
                if (doctordescription.isEmpty()) {
                    edtddesc.setError("Description is required is required");
                    edtddesc.requestFocus();
                    return;
                }

                Doctor doctoruser = new Doctor(doctorname,doctoremail,doctorlocation,doctordescription,doctorphone);

                mAuth.createUserWithEmailAndPassword(doctoremail,doctorpassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(DoctorRegister.this, "Register Successfull", Toast.LENGTH_LONG).show();
//
                                    FirebaseUtils.registerDoctorUser(doctoruser);

                                } else {
                                    throwRegisterError(task);
                                }
                            }
                        });

            }
        });

    }

     private void throwRegisterError(@NonNull Task<AuthResult> task) {
         try {
             throw task.getException();
         } catch(FirebaseAuthWeakPasswordException e) {
             edtdpassword.setError(getString(R.string.error_weak_password));
             edtdpassword.requestFocus();
         } catch(FirebaseAuthInvalidCredentialsException e) {
             edtdemail.setError(getString(R.string.error_invalid_email));
             edtdemail.requestFocus();
         } catch(FirebaseAuthUserCollisionException e) {
             edtdemail.setError(getString(R.string.error_user_exists));
             edtdemail.requestFocus();
         }catch(FirebaseException e){
             Toast.makeText(getApplicationContext(),R.string.error_no_internet,Toast.LENGTH_LONG).show();
         } catch(Exception e) {
             Log.e(TAG, e.getMessage());
         }
     }


 }