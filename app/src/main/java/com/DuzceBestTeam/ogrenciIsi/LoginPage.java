package com.DuzceBestTeam.ogrenciIsi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {

    EditText userName; // activity_main içindeki mail yazılan EditText'i tutacak değişken
    EditText userPassword; // activity_main içindeki şifre yazılan EditText'i tutacak değişken
    Button btnlogin,btnCreateAccPage; // activity_main içindeki giriş yapmayı sağlayan Button'u tutacak değişken


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        initComponents(); // onCreate içinde yazılarak uygulama açılırken tüm elemanlar arka planda tutulur


        // btnGiris tıklandığında yapılacaklar
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ekranda görülecek bir alert oluşturulurl
                AlertDialog.Builder alert = new AlertDialog.Builder(LoginPage.this);
                alert.setTitle("ÖĞRENCİ İŞİ"); // alert başlığı
                alert.setMessage("Başarılı"); // alert içindeki mesaj

                // alert'te bulunacak butonlar düzenlenir
                alert.setPositiveButton("Tamam", new DialogInterface.OnClickListener()
                {
                    // alert içindeki Tamam butonuna basıldığında yapılacaklar
                    @Override
                    public void onClick(DialogInterface dialog, int i){

                        // intent oluşturulur
                        // ilk parametre çalışacağı sayfa
                        // ikinci parametre yeni açacağı sayfanın class dosyası
                        Intent intent = new Intent(LoginPage.this, AnaSayfa.class);

                        // putExtra ile veri gönderme işlemi yapılır
                        // ilk parametre diğer tarafta bu veriyi almak için oluşturduğumuz bir key
                        // ikinci parametre göndermek istediğimiz veri
                        intent.putExtra("Mail", userName.getText().toString());
                        intent.putExtra("Şifre", userPassword.getText().toString());

                        // oluşturulan intent başlatılır
                        startActivity(intent);

                    }
                });
                alert.show();

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
        userName = findViewById(R.id.userName); // id değerine göre bulup atama yapılır
        userPassword = findViewById(R.id.userPassword); // id değerine göre bulup atama yapılır
        btnlogin = findViewById(R.id.btnLogin); // id değerine göre bulup atama yapılır
        btnCreateAccPage=findViewById(R.id.btnCreateAccPage);
    }



}