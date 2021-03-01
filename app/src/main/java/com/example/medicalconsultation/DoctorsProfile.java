package com.example.medicalconsultation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicalconsultation.HelperClasses.Doctor;
import com.example.medicalconsultation.HelperClasses.Model;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.medicalconsultation.LogInPage.DOCTOR_DETAILS;


public class DoctorsProfile extends AppCompatActivity {

    private ImageView profileimage;
    private Button btnupload;
    private ProgressBar progressBar;

    //vars
    private final DatabaseReference root = FirebaseDatabase.getInstance().getReference("Image");
    private final StorageReference reference = FirebaseStorage.getInstance().getReference();
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_profile);
        Intent myIntent = getIntent();
        Doctor docDetails= (Doctor) myIntent.getSerializableExtra(DOCTOR_DETAILS);



        CircleImageView pic = findViewById(R.id.imageview1);

        profileimage = findViewById(R.id.imageview1);
        btnupload = findViewById(R.id.buttonupload);
        progressBar = findViewById(R.id.progressBarupload);
        TextView tvDocName=findViewById(R.id.textViewDocName);
        TextView tvDocEmail=findViewById(R.id.textViewDocEmail);
        TextView tvDocPhoneNo=findViewById(R.id.textViewDocPhoneNo);
        TextView tvDocDesc=findViewById(R.id.textViewDocDesc);
        TextView tvDocLoc=findViewById(R.id.textViewDocLocation);
        tvDocName.setText(docDetails.getDoctorname());
        tvDocDesc.setText(docDetails.getDoctordescription());
        tvDocEmail.setText(docDetails.getDoctoremail());
        tvDocPhoneNo.setText(docDetails.getDoctorphone());
        tvDocLoc.setText(docDetails.getDoctorlocation());

        progressBar.setVisibility(View.INVISIBLE);

        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 2);
            }
        });


        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri != null) {
                    uploadToFirebase(imageUri);
                } else {
                    Toast.makeText(DoctorsProfile.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            profileimage.setImageURI(imageUri);
        }

    }

    private void uploadToFirebase(Uri uri) {

        final StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Model model = new Model(uri.toString());
                        String modelId = root.push().getKey();
                        root.child(modelId).setValue(model);
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), "Uploaded Successful", Toast.LENGTH_LONG).show();


                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                progressBar.setVisibility(View.VISIBLE);

            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Uploading Failed", Toast.LENGTH_LONG).show();
            }
        });


    }

    private String getFileExtension(Uri mUri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }
}

