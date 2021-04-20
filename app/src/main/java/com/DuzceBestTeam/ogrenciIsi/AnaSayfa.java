package com.DuzceBestTeam.ogrenciIsi;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AnaSayfa extends AppCompatActivity {

    Fragment fragment = null;

    HomePage_Fragment HomePage = new HomePage_Fragment();

    public AnaSayfa(Fragment fragment1) {
        fragment = fragment1;
        loadFragment(fragment);
    }
    public AnaSayfa() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);
        loadFragment(HomePage);
        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

public void AddJobAdv_onClick(View v){
    startActivity(new Intent(AnaSayfa.this,newJobAdv.class));

}

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.home:
                    fragment = new HomePage_Fragment();
                    break;
                case R.id.Search:
                    fragment = new Search_Fragment();
                    break;
                case R.id.Notification:
                    fragment = new Notification_Fragment();
                    break;
                case R.id.Profile:
                    fragment = new Profile_Fragment();
                    break;
            }
            return loadFragment(fragment);
        }
    };
}
