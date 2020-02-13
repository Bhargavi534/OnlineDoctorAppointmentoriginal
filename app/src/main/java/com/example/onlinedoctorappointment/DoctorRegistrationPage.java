package com.example.onlinedoctorappointment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.UUID;

public class DoctorRegistrationPage extends AppCompatActivity implements View.OnClickListener {
    ImageView doctor_image;
    EditText doctor_name, doctor_number, hospital_name, hospital_number, doctor_username, doctor_password,
            doctor_email, et_op;
    RadioButton doctor_female, doctor_male;
    Spinner doctor_category, doctor_experience, doctor_timings,statelocation;
    Button doctor_savebutton, hospital_location, b_gallery, b_camera;
    TextView tv_location;


    LocationManager manager;

    public static final int REQUEST_CODE_CAMERA = 22;
    public static final int REQUEST_CODE_GALLERY = 33;

    FirebaseStorage storage;
    StorageReference storageReference;

    FirebaseDatabase database;
    DatabaseReference reference;

     String gender=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration_page);
        doctor_image = findViewById(R.id.doctorimage);
        doctor_name = findViewById(R.id.dname);
        doctor_number = findViewById(R.id.dmobileno);
        hospital_name = findViewById(R.id.hname);
        hospital_number = findViewById(R.id.hnumber);
        doctor_username = findViewById(R.id.dusername);
        doctor_password = findViewById(R.id.dpassword);
        doctor_email = findViewById(R.id.demail);
        et_op = findViewById(R.id.opfee);
        doctor_female = findViewById(R.id.dfemale);
        doctor_male = findViewById(R.id.dmale);
        doctor_category = findViewById(R.id.dcategory);
        doctor_experience = findViewById(R.id.dexperience);
        doctor_timings = findViewById(R.id.htimings);
        statelocation=findViewById(R.id.state);
        doctor_savebutton = findViewById(R.id.dsavebutton);
        hospital_location = findViewById(R.id.hlocation);
        b_camera = findViewById(R.id.camera);
        b_gallery = findViewById(R.id.gallery);
        tv_location = findViewById(R.id.latlangTextview);


        hospital_location.setOnClickListener(this);
        b_camera.setOnClickListener(this);
        b_gallery.setOnClickListener(this);
        doctor_savebutton.setOnClickListener(this);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();




    }

    public void imageUpload(Uri filepath) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading File");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setProgress(0);
        pd.setMax(100);
        pd.setCancelable(false);
        pd.show();
        storageReference = storageReference.child("Images/" + UUID.randomUUID().toString());
        storageReference.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(DoctorRegistrationPage.this, "image uploaded successfully", Toast.LENGTH_SHORT).show();
                pd.dismiss();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DoctorRegistrationPage.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                pd.setProgress((int) progress);
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera:
                openCamera();
                break;
            case R.id.gallery:
                openGallery();
                break;
            case R.id.hlocation:
                getCurrentLocation();
                break;
            case R.id.dsavebutton:
                saveDoctorData();
                break;


        }


    }

    private void saveDoctorData() {
        final String dname=doctor_name.getText().toString();
       final String dphone=doctor_number.getText().toString();
       final  String hosname=hospital_name.getText().toString();
       final String hnumber=hospital_number.getText().toString();
       final String state=statelocation.getSelectedItem().toString();
       final String dusername=doctor_username.getText().toString();
       final String dpassword=doctor_password.getText().toString();
       final String demail=doctor_email.getText().toString();
       final String latlong=tv_location.getText().toString();
        if (doctor_female.isChecked()) {
            gender = doctor_female.getText().toString();
        }
        if (doctor_male.isChecked()) {
            gender = doctor_male.getText().toString();
        }
        final String dcaterory=doctor_category.getSelectedItem().toString();
        final  String opfee=et_op.getText().toString();
        final String dtimings=doctor_timings.getSelectedItem().toString();
        final String dexperience=doctor_experience.getSelectedItem().toString();

        if(dname.isEmpty()||dphone.isEmpty()||hosname.isEmpty()||hnumber.isEmpty()||dusername.isEmpty()||
        dpassword.isEmpty()||demail.isEmpty()||gender.isEmpty()||dexperience.isEmpty()){
            Toast.makeText(this, "Please Fill All The Details.....", Toast.LENGTH_SHORT).show();
        }
        else {
            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                    final String imagePath = uri.toString();
                    DoctorData d=new DoctorData(dname,dphone,hosname,hnumber,state,dusername,dpassword,demail,
                            latlong,gender,dcaterory,opfee,dtimings,dexperience,imagePath);
                    reference.child("Doctors").child(dcaterory).child(state).push().setValue(d).addOnSuccessListener(
                            new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(DoctorRegistrationPage.this,
                                            "Your Details are " + "saved successfully..", Toast.LENGTH_SHORT).show();
                                    doctor_name.setText("");
                                    doctor_number.setText("");
                                    hospital_name.setText("");
                                    hospital_number.setText("");
                                    doctor_username.setText("");
                                    doctor_password.setText("");
                                    et_op.setText("");
                                    doctor_email.setText("");
                                    tv_location.setText("");
                                    doctor_image.setImageURI(Uri.parse(""));
                                    Intent i=new Intent(DoctorRegistrationPage.this,DoctorDetails.class);

                                     i.putExtra("a",imagePath);
                                     i.putExtra("b",dname);
                                     i.putExtra("c",dphone);
                                     i.putExtra("d",hosname);
                                     i.putExtra("e" , hnumber);
                                     i.putExtra("f",state);
                                     i.putExtra("g",demail);
                                     i.putExtra("h",gender);
                                     i.putExtra("i",dcaterory);
                                     i.putExtra("j",opfee);
                                     i.putExtra("k",dtimings);
                                     i.putExtra("l",dexperience);
                                    startActivity(i);

                                }
                            });
                }
            });
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getCurrentLocation() {
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                tv_location.setText("" + latitude + "," + longitude);


            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, listener);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,1,listener);

    }

    private void openGallery() {
        Intent i=new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,REQUEST_CODE_GALLERY);

    }

    private void openCamera() {
        Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,REQUEST_CODE_CAMERA);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA) {
            if (resultCode == RESULT_OK) {
                Bitmap b = (Bitmap) data.getExtras().get("data");
                //URL URI URN
                Uri u = getImageUri(this, b);
                doctor_image.setImageURI(u);
                imageUpload(u);

            }
        } else if (requestCode == REQUEST_CODE_GALLERY) {
            if (resultCode == RESULT_OK) {
                Uri u = data.getData();
                doctor_image.setImageURI(u);
                imageUpload(u);

            }
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}
