package com.example.medicalconsultation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private Button btnpatient, btndoctor;
    public static final String APP_USER="state user";
    public static final String USER_PATIENT="patient";
    public static final String USER_DOCTOR="doctor";
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CircleImageView pic = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.picid);



        btnpatient = findViewById(R.id.buttonpatient);
        btndoctor = findViewById(R.id.buttondoctor);
        btnpatient.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LogInPage.class);
            intent.putExtra(APP_USER,USER_PATIENT);
            startActivity(intent);
        });

        btndoctor.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LogInPage.class);
            intent.putExtra(APP_USER,USER_DOCTOR);
            startActivity(intent);
        });
    }


}
