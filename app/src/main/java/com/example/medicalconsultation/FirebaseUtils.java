package com.example.medicalconsultation;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class FirebaseUtils {
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Create a new user with a first and last name
    public static void registerUser(UserDetails user, Activity callactivity){
        db.collection("patientUsers")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(callactivity.getApplicationContext(), "User Registered Successful", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                        Toast.makeText(callactivity.getApplicationContext(), "Failed to register the user", Toast.LENGTH_LONG).show();
                    }
                });

    }


// Add a new document with a generated ID


}
