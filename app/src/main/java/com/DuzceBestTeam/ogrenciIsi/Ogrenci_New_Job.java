package com.DuzceBestTeam.ogrenciIsi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Ogrenci_New_Job extends AppCompatActivity {

    DatabaseReference mDatabase;
    EditText job_title,job_description;
    Button job_save_button;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogrenci__new__job);

        job_title = findViewById(R.id.job_title);
        job_description = findViewById(R.id.job_description);
        job_save_button = findViewById(R.id.job_save_button);


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Ogrenci_Ilanlar");


        job_save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User_id = mAuth.getCurrentUser().getUid();
                String jobTitle = job_title.getText().toString();
                String jobDescription = job_description.getText().toString();
                String jobKey = mDatabase.push().getKey();
                String userKey = mAuth.getCurrentUser().getUid();
                HashMap<String,String> userMap=new HashMap<>();
                userMap.put("Ilan Başlığı",jobTitle);
                userMap.put("İş Tanımı",jobDescription);
                userMap.put("İş Veren",userKey);

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                userMap.put("yayın tarihi", formatter.format(date).substring(0,10));


                mDatabase.child(jobKey).setValue(userMap);

                DatabaseReference newJob=FirebaseDatabase.getInstance().getReference().child(User.userDatabase).child(User_id).child("Ilanlarim").child(jobTitle);
                newJob.setValue(jobKey);

                Toast.makeText( Ogrenci_New_Job.this, "İlanınız Başarıyla Yayınlandı", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Ogrenci_New_Job.this,AnaSayfa.class));
                Ogrenci_New_Job.this.finish();

            }
        });
    }
}