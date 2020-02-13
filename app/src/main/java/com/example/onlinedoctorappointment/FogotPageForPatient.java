package com.example.onlinedoctorappointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FogotPageForPatient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fogot_page_for_patient);
    }

    public void patientforgot(View view) {
        Intent i2=new Intent(this,HomePage.class);
        startActivity(i2);

    }
}
