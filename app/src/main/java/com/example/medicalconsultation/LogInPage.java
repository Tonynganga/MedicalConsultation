package com.example.medicalconsultation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.medicalconsultation.MainActivity.APP_USER;
import static com.example.medicalconsultation.MainActivity.USER_PATIENT;

public class LogInPage extends AppCompatActivity {

    FirebaseAuth fAuth;

    private Button mButtonRegister,mButtonLogin;
    private EditText mEtPassword,mEtUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);

        fAuth = FirebaseAuth.getInstance();

        Intent myIntent = getIntent();

        String user = (String) myIntent.getStringExtra(APP_USER);

        mButtonRegister = (Button)findViewById(R.id.button_register);
        mButtonLogin = (Button)findViewById(R.id.buttonlogin);

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
            if(password.length() == 0){
                mEtPassword.setError("Password is Required");
                mEtPassword.requestFocus();
                return;
            }

            fAuth.signInWithEmailAndPassword(username,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseUser user = fAuth.getCurrentUser();
                            }else{
                                Toast.makeText(getApplicationContext(), "Invalid email or Password",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        });

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