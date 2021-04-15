package com.DuzceBestTeam.ogrenciIsi;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity {

    EditText userMail; // activity_main içindeki mail yazılan EditText'i tutacak değişken
    EditText userPassword; // activity_main içindeki şifre yazılan EditText'i tutacak değişken
    Button btnlogin,btnCreateAccPage; // activity_main içindeki giriş yapmayı sağlayan Button'u tutacak değişken
    FirebaseAuth mAuth;
    ProgressDialog registerProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        FirebaseAuth mAuth1=FirebaseAuth.getInstance();
        FirebaseUser mUser= mAuth1.getCurrentUser();
        if(mUser != null && mUser.isEmailVerified()){

            startActivity(new Intent(LoginPage.this,AnaSayfa.class));


        }

        initComponents(); // onCreate içinde yazılarak uygulama açılırken tüm elemanlar arka planda tutulur


        // btnGiris tıklandığında yapılacaklar
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerProgress.setTitle("Giriş Yapılıyor");
                registerProgress.setMessage("Hesaba giriş yapılıyor Lütfen bekleyiniz.");
                registerProgress.setCanceledOnTouchOutside(false);
                registerProgress.show();
                mAuth.signInWithEmailAndPassword(userMail.getText().toString(),userPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        registerProgress.dismiss();
                        if(task.isSuccessful()){
                            if(mAuth.getCurrentUser().isEmailVerified()){
                                startActivity(new Intent(LoginPage.this,AnaSayfa.class));
                            }
                            else {
                                Toast.makeText(LoginPage.this,"Lütfen E-mailinizi doğrulayın",Toast.LENGTH_LONG).show();
                            }

                        }
                        else{
                            Toast.makeText(LoginPage.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                });





            }
        });

        btnCreateAccPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginPage.this, createAccountPage.class);

                startActivity(intent);
            }
        });


    }

    // activity_main içindeki elemanları id'lerine göre bulup ilgili değişkenlere atayacak fonksiyon
    void initComponents(){
        userMail = findViewById(R.id.userMail); // id değerine göre bulup atama yapılır
        userPassword = findViewById(R.id.userPassword); // id değerine göre bulup atama yapılır
        btnlogin = findViewById(R.id.btnLogin); // id değerine göre bulup atama yapılır
        btnCreateAccPage=findViewById(R.id.btnCreateAccPage);
        registerProgress=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
    }



}