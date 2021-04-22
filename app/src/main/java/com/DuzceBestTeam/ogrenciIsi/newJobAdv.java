package com.DuzceBestTeam.ogrenciIsi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class newJobAdv extends AppCompatActivity  {

    Spinner spinner;
    TextView Spinner_Text;
    DatabaseReference mDatabase;
    EditText job_title,job_description,job_position,job_deadline,spinner_ip,job_needs;
    Button job_save_button;
    CheckBox checkbox_ogr;
    FirebaseAuth mAuth;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job_adv);
       // setContentView(R.layout.activity_create_account_page);
        spinner = (Spinner) findViewById(R.id.spinner_ip);
        Spinner_Text = findViewById(R.id.Spinner_Text);
        job_title = findViewById(R.id.job_title);
        job_description = findViewById(R.id.job_description);
        job_position = findViewById(R.id.job_position);
        job_deadline = findViewById(R.id.job_deadline);
        job_needs = findViewById(R.id.job_needs);
        job_save_button = findViewById(R.id.job_save_button);
        checkbox_ogr = findViewById(R.id.checkbox_ogr);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Ilanlar");

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.jobKind, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner_Text.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        job_save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User_id=mAuth.getCurrentUser().getUid();
                String jobTitle=job_title.getText().toString();
                String jobDescription=job_description.getText().toString();
                String jobPosition=job_position.getText().toString();
                String jobDeadline=job_deadline.getText().toString();
                String jobNeeds=job_needs.getText().toString();
                String jobKey = mDatabase.push().getKey();
                String userKey = mAuth.getCurrentUser().getUid();
                String UserControlName = "User_Other";
                HashMap<String,String> userMap=new HashMap<>();
                userMap.put("Ilan Başlığı",jobTitle);
                userMap.put("İş Tanımı",jobDescription);
                userMap.put("Pozisyon",jobPosition);
                userMap.put("Son Başvuru Tarihi",jobDeadline);
                userMap.put("Aranan Özellikler",jobNeeds);
                userMap.put("İş Veren",userKey);
                userMap.put("Calışma Şekli",Spinner_Text.getText().toString());

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                userMap.put("yayın tarihi", formatter.format(date));


                mDatabase.child(jobKey).setValue(userMap);
                if (User.isStudent == true){
                    UserControlName = "User_Ogrenciler";

                }
                else {
                    UserControlName = "User_Other";
                }
                DatabaseReference newJob=FirebaseDatabase.getInstance().getReference().child(UserControlName).child(User_id).child("Ilanlarim").child(jobTitle);
                newJob.setValue(jobKey);

                startActivity(new Intent(newJobAdv.this,AnaSayfa.class));

            }
        });

    }
}