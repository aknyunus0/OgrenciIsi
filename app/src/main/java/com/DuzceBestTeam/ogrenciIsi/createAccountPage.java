package com.DuzceBestTeam.ogrenciIsi;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

class OgrenciUser{
    String Ad;
    String Soyad;
    String Email;
    String Sifre;
    String key;
    public OgrenciUser(String Ad,
            String Soyad,
            String Email,
            String Sifre,
            String key){
        this.Ad=Ad;
        this.Soyad=Soyad;
        this.Email=Email;
        this.Sifre=Sifre;
        this.key=key;
    }
}

public class createAccountPage extends AppCompatActivity {
    EditText Cr_txtEmail,Cr_txtSifre,Cr_txtAd,Cr_txtSoyad;











    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_page);
        initComponents();


    }
    public void checkedOgr(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){
            Cr_txtEmail.setHint("@edu-Mail");
        }
        else {
            Cr_txtEmail.setHint("Mail");
        }
    }

    public void btn_signUpOnClick (View v){
        DatabaseReference mDatabase;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = firebaseDatabase.getReference("OgrenciUsers");
        mDatabase = FirebaseDatabase.getInstance().getReference();

        String key = dbRef.push().getKey();
        OgrenciUser user= new OgrenciUser(
                Cr_txtEmail.getText().toString(),
                Cr_txtSifre.getText().toString(),
                Cr_txtAd.getText().toString(),
                Cr_txtSoyad.getText().toString(),key);
        Gson gson = new Gson();
        String json = gson.toJson(user);
        mDatabase.child("OgrenciUsers").setValue(user);



    }
    void initComponents(){
        Cr_txtEmail = findViewById(R.id.Cr_txtEmail); // id değerine göre bulup atama yapılır
        Cr_txtAd = findViewById(R.id.Cr_txtAd); // id değerine göre bulup atama yapılır
        Cr_txtSifre = findViewById(R.id.Cr_txtSifre); // id değerine göre bulup atama yapılır
        Cr_txtSoyad=findViewById(R.id.Cr_txtSoyad);

    }




}