package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindDoctorActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor2);

        CardView exit= findViewById(R.id.cardFDBack);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindDoctorActivity2.this,HomeActivity2.class));

            }
        });

        CardView FamilyPhysician= findViewById(R.id.cardFDFamilyPhysician);
       FamilyPhysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(FindDoctorActivity2.this,DoctorDetailsActivity2.class);
                it.putExtra("title","Family Physician");
                startActivity(it);
            }
        });

        CardView dietitian = findViewById(R.id.cardFDDietitian);
        dietitian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(FindDoctorActivity2.this,DoctorDetailsActivity2.class);
                it.putExtra("title","Dietitian");
                startActivity(it);
            }
        });

        CardView dentist= findViewById(R.id.cardFDDentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(FindDoctorActivity2.this,DoctorDetailsActivity2.class);
                it.putExtra("title","Dentist");
                startActivity(it);
            }
        });

        CardView surgeon= findViewById(R.id.cardFDSurgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(FindDoctorActivity2.this,DoctorDetailsActivity2.class);
                it.putExtra("title","Surgeon");
                startActivity(it);
            }
        });

        CardView cardiologist= findViewById(R.id.cardFDCardiologists);
        cardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(FindDoctorActivity2.this,DoctorDetailsActivity2.class);
                it.putExtra("title","Cardiologists");
                startActivity(it);
            }
        });

    }
}