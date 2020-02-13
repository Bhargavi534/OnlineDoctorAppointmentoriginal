package com.example.onlinedoctorappointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DoctorDetails extends AppCompatActivity {
    ImageView doctor_image;
    EditText doctor_name, doctor_number, hospital_name, hospital_number,
            doctor_email, et_op;
    RadioButton doctor_female, doctor_male;
    Spinner doctor_category, doctor_experience, doctor_timings,statelocation;

    TextView tv;



    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv = findViewById(R.id.textview);
        setContentView(R.layout.activity_doctor_details);
        String image= getIntent().getStringExtra("a");
        String name= getIntent().getStringExtra("b");
        String number= getIntent().getStringExtra("c");
        String hname= getIntent().getStringExtra("d");
        String hnum= getIntent().getStringExtra("e");
        String state= getIntent().getStringExtra("f");
        String email= getIntent().getStringExtra("g");
        String gender= getIntent().getStringExtra("h");
        String cate= getIntent().getStringExtra("i");
        String op= getIntent().getStringExtra("j");
        String timings= getIntent().getStringExtra("k");
        String exper= getIntent().getStringExtra("l");
        tv.append(image+"\n"+name+"\n"+number+"\n"+hname+"\n"+hnum+"\n"+state+"\n"+email+gender+"\n"+cate+"\n"+op+"\n"+timings+"\n"+exper+"\n");




        /*doctor_image = findViewById(R.id.doctorimage);
        doctor_name = findViewById(R.id.dname);
        doctor_number = findViewById(R.id.dmobileno);
        hospital_name = findViewById(R.id.hname);
        hospital_number = findViewById(R.id.hnumber);
        doctor_email = findViewById(R.id.demail);
        et_op = findViewById(R.id.opfee);
        doctor_female = findViewById(R.id.dfemale);
        doctor_male = findViewById(R.id.dmale);
        doctor_category = findViewById(R.id.dcategory);
        doctor_experience = findViewById(R.id.dexperience);
        doctor_timings = findViewById(R.id.htimings);
        statelocation=findViewById(R.id.state);



        database = FirebaseDatabase.getInstance();
        reference = database.getReference();*/
    }

    public void doctorDetails(View view) {



       /* String category=sp_category.getSelectedItem().toString();
        String state=sp_state.getSelectedItem().toString();
        final List<DoctorData> list=new ArrayList<>();
        reference.child("DoctorDetails").child(category).child(state).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                 DoctorData d=snapshot.getValue(DoctorData.class);
                 list.add(d);

                }
                Toast.makeText(DoctorDetails.this, ""+list.size(), Toast.LENGTH_SHORT).show();
                for(int i=0;i<list.size();i++){
                    tv.append(list.get(i).getDname()+" \n"+list.get(i).getDphone()+"\n  "+list.get(i).getHosname()+
                            list.get(i).getHnumber()+" \n "+list.get(i).getState()+"\n  "
                            +list.get(i).getDemail()+" \n "+list.get(i).getDexperience()+"\n+"+list.get(i).getGender()+"\n  "+
                            list.get(i).getDcategory()+" \n "+list.get(i).getOpfee()+"\n"+list.get(i).getDtimings()+"\n");
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/
    }
}