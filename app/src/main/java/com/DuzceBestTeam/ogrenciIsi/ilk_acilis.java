package com.DuzceBestTeam.ogrenciIsi;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;


public class ilk_acilis extends AppCompatActivity {
    TextView txt1,txt2,txt3;
    Button btnLoginPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilk_acilis);


        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt2.setVisibility(View.GONE);//txt görünürlüğü kapınıyor
        txt3=findViewById(R.id.txt3);
        txt3.setVisibility(View.GONE);//txt görünürlüğü kapınıyor
        btnLoginPage=findViewById(R.id.btnLoginPage);
        btnLoginPage.setVisibility(View.GONE);//btn görünürlüğü kapınıyor
        //res/anim/ilk_acilis_page.xml animasyon xml dosyasi bir değikene atanıyor
        final Animation ilkAcilisAnim = AnimationUtils.loadAnimation(this, R.anim.ilk_acilis_page);
        //belitilen belirlenen şekilde animasyon başlatılıyor
        txt1.startAnimation(ilkAcilisAnim);


        //yapilacak olan işlemlerde süre belirleme fonksiyonu
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //txt2 görünürlğe açılıyor ve anımasyonu başlatılıyor
                txt2.setVisibility(View.VISIBLE);
                txt2.startAnimation(ilkAcilisAnim);
            }

        },2000); //sayfa açıldıktan 2 saniye sonra gerçekşecek işlem
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txt3.setVisibility(View.VISIBLE);
                txt3.startAnimation(ilkAcilisAnim);
            }

        },4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnLoginPage.setVisibility(View.VISIBLE);
                btnLoginPage.startAnimation(ilkAcilisAnim);
            }

        },7000);



        btnLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new  Intent(ilk_acilis.this, LoginPage.class);
                startActivity(it);

            }
        });

    }
}