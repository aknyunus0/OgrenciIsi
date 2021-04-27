package com.DuzceBestTeam.ogrenciIsi;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Other_New_Job extends AppCompatActivity  {

    Spinner spinner;
    DatabaseReference mDatabase;
    EditText job_title,job_description,job_position,job_needs;
    TextView job_deadline;
    Button job_save_button;
    FirebaseAuth mAuth;
    private Calendar calendar;
    private int year, month, dayOfMonth;
    private DatePickerDialog datePickerDialog;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_new_job);
       // setContentView(R.layout.activity_create_account_page);
        spinner = (Spinner) findViewById(R.id.spinner_ip);
        job_title = findViewById(R.id.job_title);
        job_description = findViewById(R.id.job_description);
        job_position = findViewById(R.id.job_position);
        job_deadline = findViewById(R.id.job_deadline);
        job_needs = findViewById(R.id.job_needs);
        job_save_button = findViewById(R.id.job_save_button);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Other_Ilanlar");


        //bugünkü tarihi gün/ay/yıl formatında yazdırır
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        job_deadline.setText(formatter.format(date).substring(0,10));

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.jobKind, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        job_deadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(Other_New_Job.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                job_deadline.setText(day + "/" + (month+1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
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
                userMap.put("Calışma Şekli",spinner.getSelectedItem().toString());

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                userMap.put("yayın tarihi", formatter.format(date).substring(0,10));


                mDatabase.child(jobKey).setValue(userMap);

                DatabaseReference newJob=FirebaseDatabase.getInstance().getReference().child(User.userDatabase).child(User_id).child("Ilanlarim").child(jobKey);
                newJob.child("Ilan Basligi").setValue(jobTitle);

                Toast.makeText( Other_New_Job.this, "İlanınız Başarıyla Yayınlandı", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Other_New_Job.this,AnaSayfa.class));
                Other_New_Job.this.finish();


            }
        });

    }
}