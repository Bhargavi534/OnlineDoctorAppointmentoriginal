package com.example.onlinedoctorappointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PatientRegistrationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration_page);
    }

    public void patientokbutton(View view) {
        Intent i2=new Intent(this,HomePage.class);
        startActivity(i2);
    }
}
