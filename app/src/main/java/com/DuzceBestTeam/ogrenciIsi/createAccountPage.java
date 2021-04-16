package com.DuzceBestTeam.ogrenciIsi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;



public class createAccountPage extends AppCompatActivity {
    EditText Cr_txtEmail,Cr_txtSifre,Cr_txtAd,Cr_txtSoyad;
    FirebaseAuth mAuth;
    ProgressDialog registerProgress;
    DatabaseReference mDatabase;



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
            User.isStudent = true;
        }
        else {
            Cr_txtEmail.setHint("Mail");
            User.isStudent = false;
        }
    }

    public void btn_signUpOnClick (View v){

        String Ad=Cr_txtAd.getText().toString();
        String Soyad=Cr_txtSoyad.getText().toString();
        String Email=Cr_txtEmail.getText().toString();
        String Sifre=Cr_txtSifre.getText().toString();

        if(!TextUtils.isEmpty(Ad)||!TextUtils.isEmpty(Soyad)||!TextUtils.isEmpty(Email)||!TextUtils.isEmpty(Sifre)){
            registerProgress.setTitle("Kaydediliyor...");
            registerProgress.setMessage("Hesap Oluşturuluyor,Lütfen Bekleyiniz.");
            registerProgress.setCanceledOnTouchOutside(false);
            registerProgress.show();
            register_User(Ad,Soyad,Email,Sifre);


        }


    }
    void register_User(final String ad, final String Soyad, final String Email, final String Sifre){
        mAuth.createUserWithEmailAndPassword(Email,Sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    registerProgress.dismiss();
                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                String User_id=mAuth.getCurrentUser().getUid();
                                String UserControlName = "User_Other";

                            if(Email.contains("edu.tr")&& Email.contains("@ogr")){
                                UserControlName="User_Ogrenciler";
                            }
                                 mDatabase=FirebaseDatabase.getInstance().getReference().child(UserControlName).child(User_id);
                                HashMap<String,String> userMap=new HashMap<>();
                                userMap.put("Ad",ad);
                                userMap.put("Soyad",Soyad);
                                userMap.put("EMail",Email);
                                userMap.put("Sifre",Sifre);
                                mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(createAccountPage.this,"Lütfen Maile gelen link ile hesabınızı onaylayıp giriş yapınız.",Toast.LENGTH_LONG).show();
                                            Intent LoginIntent=new Intent(createAccountPage.this,LoginPage.class);
                                            startActivity(LoginIntent);
                                        }

                                    }
                                });


                            }
                            else {
                                Toast.makeText(createAccountPage.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }


                        }
                    });




                }
                else {
                    registerProgress.dismiss();
                    Toast.makeText(getApplicationContext(),"hata:"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }


            }
        });
    }
    void initComponents(){
        Cr_txtEmail = findViewById(R.id.Cr_txtEmail); // id değerine göre bulup atama yapılır
        Cr_txtAd = findViewById(R.id.Cr_txtAd); // id değerine göre bulup atama yapılır
        Cr_txtSifre = findViewById(R.id.Cr_txtSifre); // id değerine göre bulup atama yapılır
        Cr_txtSoyad=findViewById(R.id.Cr_txtSoyad);
        mAuth=FirebaseAuth.getInstance();
        registerProgress=new ProgressDialog(this);

    }




}