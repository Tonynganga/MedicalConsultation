package com.example.medicalconsultation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.medicalconsultation.HelperClasses.Doctor;
import com.example.medicalconsultation.HelperClasses.Patient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static com.example.medicalconsultation.FirebaseUtils.DOCTOR_USERS;
import static com.example.medicalconsultation.FirebaseUtils.PATIENT_USERS;
import static com.example.medicalconsultation.FirebaseUtils.mFireStore;
import static com.example.medicalconsultation.FirebaseUtils.mFirebaseAuth;
import static com.example.medicalconsultation.MainActivity.APP_USER;
import static com.example.medicalconsultation.MainActivity.USER_PATIENT;

public class LogInPage extends AppCompatActivity {

    private static final String TAG = "Login Page";
    public static final String DOCTOR_DETAILS = "Doctor Details";
    public static final String PATIENT_DETAILS = "Patient Details";
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    private Button mButtonRegister,mButtonLogin;
    private EditText mEtPassword,mEtUsername;
    public Patient mUserPatientDetails;
    public String mUser;
    public Doctor mUserDoctorDetails;
    public Boolean mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);

        fAuth = FirebaseAuth.getInstance();

        Intent myIntent = getIntent();

        mUser = myIntent.getStringExtra(APP_USER);


        mButtonRegister = (Button)findViewById(R.id.button_register);
        mButtonLogin = (Button)findViewById(R.id.buttonlogin);
        progressBar = findViewById(R.id.progressBar);


        mEtUsername = findViewById(R.id.etUserName);
        mEtPassword = findViewById(R.id.etPassword);

        mButtonLogin.setOnClickListener(v->{
            String username = mEtUsername.getText().toString().trim();
            String password = mEtPassword.getText().toString().trim();
            if(username.length() == 0){
                mEtUsername.setError("Username is Required");
                mEtUsername.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(username).matches()){
                mEtUsername.setError("Please enter a valid email");
                mEtUsername.requestFocus();
                return;
            }
            if(password.length() == 0){
                mEtPassword.setError("Password is Required");
                mEtPassword.requestFocus();
                return;
            }
            if(password.length()<6){
                mEtPassword.setError("Minimum password length is 6 characters");
                mEtPassword.requestFocus();
                return;
            }


            progressBar.setVisibility(View.VISIBLE);

            fAuth.signInWithEmailAndPassword(username,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if(task.isSuccessful()){
                                logIn();
                            }
                        }

                    }).addOnFailureListener(new OnFailureListener(){

                @Override
                public void onFailure(@NonNull Exception e) {
                    if(e instanceof FirebaseAuthInvalidUserException){
                        Toast.makeText(getApplicationContext(),R.string.error_invalid_email_or_password,Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),R.string.error_no_internet,Toast.LENGTH_LONG).show();
                    }

                }
            });

        });

        mButtonRegister.setOnClickListener(v -> {
            if(mUser.equals(USER_PATIENT)){
                Intent intent = new Intent(getApplicationContext(), PatientRegister.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(getApplicationContext(), DoctorRegister.class);
                startActivity(intent);
            }


        });

    }

    private void throwLoginError(@NonNull Task<AuthResult> task) {
        try {
            throw task.getException();
        } catch(FirebaseAuthWeakPasswordException e) {
            mEtPassword.setError(getString(R.string.error_weak_password));
            mEtPassword.requestFocus();
        } catch(FirebaseAuthInvalidCredentialsException e) {
            mEtUsername.setError(getString(R.string.error_invalid_email_or_password));
            mEtUsername.requestFocus();
        } catch(FirebaseAuthUserCollisionException e) {
            mEtUsername.setError(getString(R.string.error_user_exists));
            mEtUsername.requestFocus();
        }catch(FirebaseNetworkException e){
                Toast.makeText(getApplicationContext(),R.string.error_no_internet,Toast.LENGTH_LONG).show();
        } catch(Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void logIn() {
        FirebaseUtils.setCurrentUser();
        if (mUser.equals(USER_PATIENT)){
            checkUserPatient();
        }else{
            checkUserDoctor();
        }
    }


    private void checkUserDoctor() {
        mFireStore.collection(DOCTOR_USERS)
                .whereEqualTo("doctoremail", FirebaseUtils.sUserEmail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                mUserDoctorDetails = document.toObject(Doctor.class);
                            }
                            if(mUserDoctorDetails!=null){
                                Intent intent = new Intent(getApplicationContext(), DoctorHomePage.class);
                                intent.putExtra(DOCTOR_DETAILS,mUserDoctorDetails);
                                startActivity(intent);
                            }else{
                                mFirebaseAuth.signOut();
                                Toast.makeText(getApplicationContext(), "No User Found",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void checkUserPatient() {
        mFireStore.collection(PATIENT_USERS)
                .whereEqualTo("email", FirebaseUtils.sUserEmail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    mUserPatientDetails = document.toObject(Patient.class);
                                }
                                if(mUserPatientDetails!=null){
                                    Intent intent = new Intent(getApplicationContext(), PatientHomePage.class);
                                    intent.putExtra(PATIENT_DETAILS,mUserPatientDetails);
                                    startActivity(intent);
                                }else{
                                    mFirebaseAuth.signOut();
                                    Toast.makeText(getApplicationContext(), "No User Found",Toast.LENGTH_SHORT).show();
                                }


                        }
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
//        FirebaseUtils.detachListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        FirebaseUtils.attachListener();
    }
}