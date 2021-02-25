package com.example.medicalconsultation;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.medicalconsultation.HelperClasses.Comment;
import com.example.medicalconsultation.HelperClasses.Doctor;
import com.example.medicalconsultation.HelperClasses.Patient;
import com.example.medicalconsultation.HelperClasses.PatientPost;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class FirebaseUtils {
    public static final FirebaseFirestore mFireStore = FirebaseFirestore.getInstance();
    public static final FirebaseAuth mFirebaseAuth= FirebaseAuth.getInstance();


    // Create a new user with a first and last name
    public static void registerPatientUser(Patient user){
        mFireStore.collection("patientUsers")
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
        mFireStore.collection("doctorUsers")
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
    public static void addPost(PatientPost post){
        final DocumentReference PatientPostRef;
        PatientPostRef = mFireStore.collection("problems")
                .document();
        post.setId( PatientPostRef.getId());
        PatientPostRef.set(post)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Firestore", "Document updated with ID: " + PatientPostRef.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Firestore", "Error updating document", e);
                    }
                });
    }
    public static void addComment(Comment comment){
        final DocumentReference commentRef;
        commentRef = mFireStore.collection("problems")
                .document(comment.getProblemId())
                .collection("comments")
                .document();
        comment.setId( commentRef.getId());
        commentRef.set(comment)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Firestore", "Document updated with ID: " + commentRef.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Firestore", "Error updating document", e);
                    }
                });

    }



// Add a new document with a generated ID


}
