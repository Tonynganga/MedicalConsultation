package com.example.medicalconsultation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicalconsultation.HelperClasses.PatientPost;

import static com.example.medicalconsultation.FirebaseUtils.mFirebaseAuth;

public class PatientProblem extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {
    private final String[] mDoctorCategory={"Dentistry","Optical","Skin Problems","Pediatrics","Gastrointestinal"};
    private String mCategoryVal;
    private EditText mEtDesc;
    private Button mBtPostProb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_problem);
        Spinner spin = findViewById(R.id.categorySpinner);
        spin.setOnItemSelectedListener(this);
        mEtDesc = findViewById(R.id.etPatientProblemDesc);
        mBtPostProb = findViewById(R.id.button2);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinner_item,mDoctorCategory);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(adapter);
        mBtPostProb.setOnClickListener( view -> {
            String description = mEtDesc.getText().toString().trim();
            if(description.length() == 0){
                mEtDesc.setError("Enter a Description");
                mEtDesc.requestFocus();
                return;
            }
            PatientPost post= new PatientPost(mFirebaseAuth.getUid(),description,mCategoryVal);
            FirebaseUtils.addPost(this,post);
            mEtDesc.setText("");



        });

    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        mCategoryVal=mDoctorCategory[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}