package com.DuzceBestTeam.ogrenciIsi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Iterator;

public class LoginPage extends AppCompatActivity {

    EditText userMail; // activity_main içindeki mail yazılan EditText'i tutacak değişken
    EditText userPassword; // activity_main içindeki şifre yazılan EditText'i tutacak değişken
    Button btnlogin,btnCreateAccPage; // activity_main içindeki giriş yapmayı sağlayan Button'u tutacak değişken
    FirebaseAuth mAuth;
    ProgressDialog registerProgress;
    DatabaseReference mDatabaseReference;
    ArrayList<Ilan> ilanlar;
    String userControlMail;
    StorageReference storageReference;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        initComponents(); // onCreate içinde yazılarak uygulama açılırken tüm elemanlar arka planda tutulur

      if(mAuth.getCurrentUser() != null && mAuth.getCurrentUser().isEmailVerified()){

            userControlMail = mAuth.getCurrentUser().getEmail();
            setUserinfo();

           startActivity(new Intent(LoginPage.this,AnaSayfa.class));
            this.finish();
      }



        // btnGiris tıklandığında yapılacaklar
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(userPassword.getText().toString()) || !TextUtils.isEmpty(userMail.getText().toString()))
                {
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
                                    userControlMail = userMail.getText().toString();
                                    setUserinfo();

                                  startActivity(new Intent(LoginPage.this,AnaSayfa.class));
                                  LoginPage.this.finish();
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
                else{
                    Toast.makeText(getApplicationContext(), "Bilgiler eksik girilemez", Toast.LENGTH_SHORT).show();
                }
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

    private void getUserDatabase(String userControlMail)
    {
        if(userControlMail.contains("edu.tr") && userControlMail.contains("@ogr"))
        {
            User.isStudent = true;
            User.userDatabase = "User_Ogrenciler";
        }
        else
        {
            User.isStudent = false;
            User.userDatabase = "User_Other";
        }
    }

    private void setUserinfo() {

        getUserDatabase(userControlMail);

        mDatabaseReference = mDatabaseReference.child(User.userDatabase).child(mAuth.getCurrentUser().getUid());

        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User.userName = snapshot.child("Ad").getValue().toString();
                User.userSurName = snapshot.child("Soyad").getValue().toString();
                User.userMail = snapshot.child("EMail").getValue().toString();
                User.userPassword = snapshot.child("Sifre").getValue().toString();
                User.userLocation = snapshot.child("konum").getValue().toString();
                User.userAbout = snapshot.child("Hakkında").getValue().toString();
                User.userExpert = snapshot.child("uzmanlık").getValue().toString();
                User.userprofileimage = snapshot.child("Profil Resmi").getValue().toString();
                if(User.isStudent==true){
                    User.userDepartman = snapshot.child("Bölüm").getValue().toString();
                    User.userGrade = snapshot.child("eğitim düzeyi").getValue().toString();
                    User.userUniversity = snapshot.child("üniversite").getValue().toString();
                    User.isSecretProfile = Boolean.parseBoolean(snapshot.child("Gizli mi?").getValue().toString());
                }
                else{
                    User.userCompany = snapshot.child("Sirket").getValue().toString();
                }




             /*   storageReference.child("image").child(mAuth.getCurrentUser().getUid()).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        //  Toast.makeText(getApplicationContext(),task.getResult().toString(),Toast.LENGTH_LONG).show();
                     User.profileuriurl=task.getResult().toString();
                    }
                });*/

                Uri uri=Uri.parse(User.userprofileimage);
                Glide
                        .with(getApplicationContext())
                        .load(uri) // the uri you got from Firebase
                        .centerCrop()
                        .into(imageView); //Your imageView variable

                Iterator<DataSnapshot> items = snapshot.child("Ilanlarim").getChildren().iterator();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference= FirebaseStorage.getInstance().getReference();
        imageView=findViewById(R.id.loginprofile);
    }



}