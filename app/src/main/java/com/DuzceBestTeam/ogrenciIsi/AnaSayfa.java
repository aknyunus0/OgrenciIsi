package com.DuzceBestTeam.ogrenciIsi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

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