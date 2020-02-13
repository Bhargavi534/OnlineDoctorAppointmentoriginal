package com.example.onlinedoctorappointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int images[]={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,
                R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,
                R.drawable.j};

        viewFlipper=findViewById( R.id.flipimage );
        for (int image: images){
            flipperImages( image );
        }
    }
    public void flipperImages(int image)
    {
        ImageView imageView=new ImageView( this );
        imageView.setBackgroundResource( image );
        viewFlipper.addView( imageView );
        viewFlipper.setFlipInterval( 2000 );
        viewFlipper.setAutoStart( true );

        viewFlipper.setInAnimation( this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation( this,android.R.anim.slide_out_right);


    }
    public void patient(View view) {
        Intent i=new Intent(this,LoginpageforPatient.class);
        startActivity(i);
    }

    public void doctor(View view) {
        Intent i=new Intent(this,LoginPage.class);
        startActivity(i);
    }
}
