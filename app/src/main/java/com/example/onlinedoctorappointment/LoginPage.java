package com.example.onlinedoctorappointment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null){
            //EventLogTags.Description.setVisibility(View.INVISIBLE);
            new AlertDialog.Builder(LoginPage.this)
                    .setTitle(getResources().getString(R.string.app_name))
                    .setMessage("Please Check your Internetconnection")
                    .setPositiveButton("OK", null).show();
        }else {
            Toast.makeText(this,
                    "Iternet is Available", Toast.LENGTH_SHORT).show();
        }

    }

    public void password(View view) {
        Intent i=new Intent(this,ForgotPage.class);
        startActivity(i);
    }

    public void newuser(View view) {
        Intent i1=new Intent(this,DoctorRegistrationPage.class);
        startActivity(i1);
    }

    public void doctorforgot(View view) {
    }
}
