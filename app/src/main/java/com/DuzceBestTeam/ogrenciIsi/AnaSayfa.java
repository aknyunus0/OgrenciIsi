package com.DuzceBestTeam.ogrenciIsi;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AnaSayfa extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);
        
    }

public void AddJobAdv_onClick(View v){
    startActivity(new Intent(AnaSayfa.this,newJobAdv.class));

}

}