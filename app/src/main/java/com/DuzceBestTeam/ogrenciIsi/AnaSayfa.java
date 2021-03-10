package com.DuzceBestTeam.ogrenciIsi;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AnaSayfa extends AppCompatActivity {

    TextView txtMail; // activity_ana_sayfa içindeki mail yazılacak TextView tutacak değişken
    TextView txtSifre; // activity_ana_sayfa içindeki şifre yazılacak TextView tutacak değişken

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);

        initComponents(); // onCreate içinde yazılarak uygulama açılırken tüm elemanlar arka planda tutulur

        // intent oluşturulur
        // buraya gelen verileri yazacağımız için getIntent ile bulunduğumuz sayfayı seçeriz
        Intent intent = getIntent();

        // string veri gönderimi yapıldığı için string tipinde değişken oluşturulur
        // getStringExtra diğer taraftan gönderdiğimiz veriye verdiğimiz key yazılır
        String mail = intent.getStringExtra("Mail");
        String sifre = intent.getStringExtra("Şifre");

        // aldığımız verileri ilgili TextView'ların text'leri olarak atama işlemi yapılır
        txtMail.setText(mail);
        txtSifre.setText(sifre);
    }

    // activity_ana_sayfa içindeki elemanları id'lerine göre bulup ilgili değişkenlere atayacak fonksiyon
    void initComponents(){
        txtMail = findViewById(R.id.txtMail);
        txtSifre = findViewById(R.id.txtSifre);
    }
}