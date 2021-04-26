package com.DuzceBestTeam.ogrenciIsi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class showProfil extends AppCompatActivity {

    private  Button sendMessage;

    private TextView Konum;
    private TextView profile_name;
    private TextView Üniversite;
    private TextView hakkindatext;
    private TextView uzmanlikalantext;
    private TextView bolum;
    private TextView Egitimduzeyi;
    ShapeableImageView profileImage;
    DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profil);
        initComponents();
        String Key= getIntent().getExtras().getString("UserKey");

        mDatabaseReference = mDatabaseReference.child("User_Ogrenciler").child(Key);

        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Profil_picUrl;
                profile_name.setText(snapshot.child("Ad").getValue().toString()+" "+snapshot.child("Soyad").getValue().toString());
                 Konum.setText(snapshot.child("konum").getValue().toString());
                hakkindatext.setText( snapshot.child("Hakkında").getValue().toString());
                uzmanlikalantext.setText(snapshot.child("uzmanlık").getValue().toString());
                Profil_picUrl= snapshot.child("Profil Resmi").getValue().toString();
                bolum.setText(snapshot.child("Bölüm").getValue().toString());
                Egitimduzeyi.setText(snapshot.child("eğitim düzeyi").getValue().toString());
                  Üniversite.setText(snapshot.child("üniversite").getValue().toString());

                Uri uri=Uri.parse(Profil_picUrl);
                Glide
                        .with(getApplicationContext())
                        .load(uri) // the uri you got from Firebase
                        .centerCrop()
                        .into(profileImage); //Your imageView variable



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });


    }

    private void initComponents() {
        sendMessage=findViewById(R.id.sendMessage);
        profile_name = findViewById(R.id.profile_name);
        Konum = findViewById(R.id.Konum);
        Üniversite = findViewById(R.id.Üniversite);
        hakkindatext =findViewById(R.id.hakkindatext);
        uzmanlikalantext = findViewById(R.id.uzmanlikalantext);
        bolum = findViewById(R.id.bolum);
        Egitimduzeyi =findViewById(R.id.Egitimduzeyi);
        profileImage = findViewById(R.id.profile_image1);
        mDatabaseReference= FirebaseDatabase.getInstance().getReference();

    }

}