package com.example.onlinedoctorappointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LoginpageforPatient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpagefor_patient);
    }

    public void patientpassword(View view) {
        Intent i=new Intent(this,FogotPageForPatient.class);
        startActivity(i);
    }

    public void patientnewuser(View view) {
        Intent i1=new Intent(this,PatientRegistrationPage.class);
        startActivity(i1);
    }

    public void plogok(View view) {

        Intent i2=new Intent(LoginpageforPatient.this,HomePage.class);
        startActivity(i2);
    }
}
