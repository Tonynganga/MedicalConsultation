package com.example.medicalconsultation;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.medicalconsultation.HelperClasses.Doctor;
import com.example.medicalconsultation.HelperClasses.Patient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import static android.content.ContentValues.TAG;

public class FirebaseUtils {
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Create a new user with a first and last name
    public static void registerPatientUser(Patient user){
        db.collection("patientUsers")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }
    public static void registerDoctorUser(Doctor user){
        db.collection("doctorUsers")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }


// Add a new document with a generated ID


}
