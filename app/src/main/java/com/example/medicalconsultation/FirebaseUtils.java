package com.example.medicalconsultation;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.medicalconsultation.HelperClasses.Comment;
import com.example.medicalconsultation.HelperClasses.Doctor;
import com.example.medicalconsultation.HelperClasses.Patient;
import com.example.medicalconsultation.HelperClasses.PatientPost;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class FirebaseUtils {
    public static final FirebaseFirestore mFireStore = FirebaseFirestore.getInstance();
    public static final FirebaseAuth mFirebaseAuth= FirebaseAuth.getInstance();
    public static FirebaseAuth.AuthStateListener mAuthStateListener;
    public static final String PATIENT_USERS = "patientUsers";
    public static final String DOCTOR_USERS = "doctorUsers";
    public static String sUserEmail;
    public static Doctor sUserDoctorDetails;


    // Create a new user with a first and last name
    public static void registerPatientUser(Patient user){
        mFireStore.collection(PATIENT_USERS)
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
    public static void setCurrentUser(){
      FirebaseUser user =mFirebaseAuth.getCurrentUser();
      if(user!=null){
          sUserEmail = user.getEmail();
      }
    }
    public static Doctor setDoctorUser(Doctor docDetails){
       sUserDoctorDetails=docDetails;
        return sUserDoctorDetails;
    }
    public static void attachListener() {
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    public static void detachListener() {
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    public static void saveDoctorUser(Doctor user){
        if(user.getId()==null){
        mFireStore.collection(DOCTOR_USERS)
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
        }else{
            DocumentReference docRef = mFireStore.collection(DOCTOR_USERS)
                    .document(user.getId());
            docRef.update(
                    "doctordescription", user.getDoctordescription(),
                    "imgUri", user.getImgUri()
            );
        }

    }

    public static void addPost(Context context,PatientPost post){
        final DocumentReference PatientPostRef;
        PatientPostRef = mFireStore.collection("problems")
                .document();
        post.setId( PatientPostRef.getId());
        PatientPostRef.set(post)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context,"Posted successful",Toast.LENGTH_LONG).show();
                        Log.d("Firestore", "Document updated with ID: " + PatientPostRef.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context,"Failed to post problem",Toast.LENGTH_LONG).show();
                        Log.e("Firestore", "Error updating document", e);
                    }
                });
    }


    public static void addComment(Context context, Comment comment, String problemId){
        final DocumentReference commentRef;
        commentRef = mFireStore.collection("problems")
                .document(problemId)
                .collection("comments")
                .document();
//        comment.setId( commentRef.getId());
        commentRef.set(comment)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context,"Posted comment successful",Toast.LENGTH_LONG).show();
                        Log.d("Firestore", "Document updated with ID: " + commentRef.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context,"Failed to post comment",Toast.LENGTH_LONG).show();
                        Log.e("Firestore", "Error updating document", e);
                    }
                });

    }



// Add a new document with a generated ID


}
