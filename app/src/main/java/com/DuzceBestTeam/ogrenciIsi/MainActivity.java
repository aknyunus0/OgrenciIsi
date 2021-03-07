package com.DuzceBestTeam.ogrenciIsi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etxtMail; // activity_main içindeki mail yazılan EditText'i tutacak değişken
    EditText etxtSifre; // activity_main içindeki şifre yazılan EditText'i tutacak değişken
    Button btnGiris; // activity_main içindeki giriş yapmayı sağlayan Button'u tutacak değişken

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents(); // onCreate içinde yazılarak uygulama açılırken tüm elemanlar arka planda tutulur


        // btnGiris tıklandığında yapılacaklar
        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ekranda görülecek bir alert oluşturulur
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
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
                        Intent intent = new Intent(MainActivity.this, AnaSayfa.class);

                        // putExtra ile veri gönderme işlemi yapılır
                        // ilk parametre diğer tarafta bu veriyi almak için oluşturduğumuz bir key
                        // ikinci parametre göndermek istediğimiz veri
                        intent.putExtra("Mail", etxtMail.getText().toString());
                        intent.putExtra("Şifre", etxtSifre.getText().toString());

                        // oluşturulan intent başlatılır
                        startActivity(intent);

                    }
                });
                alert.show();

            }
        });
    }

    // activity_main içindeki elemanları id'lerine göre bulup ilgili değişkenlere atayacak fonksiyon
    void initComponents(){
        etxtMail = findViewById(R.id.etxtMail); // id değerine göre bulup atama yapılır
        etxtSifre = findViewById(R.id.etxtSifre); // id değerine göre bulup atama yapılır
        btnGiris = findViewById(R.id.btnGiris); // id değerine göre bulup atama yapılır
    }




}