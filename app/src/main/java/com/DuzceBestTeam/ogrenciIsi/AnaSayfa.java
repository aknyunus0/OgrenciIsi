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
        if (User.isStudent == true) {
            HomePage_Fragment HomePage = new HomePage_Fragment();
            loadFragment(HomePage);
        } else {
            other_homepage_fragment otherHomePage = new other_homepage_fragment();
            loadFragment(otherHomePage);
        }
        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void AddJobAdv_onClick(View v) {
        if (User.isStudent)
            startActivity(new Intent(AnaSayfa.this, Ogrenci_New_Job.class));
        else
            startActivity(new Intent(AnaSayfa.this, Other_New_Job.class));
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
            if (User.isStudent == true) {
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
            } else {
                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = new other_homepage_fragment();
                        break;
                    case R.id.Search:
                        fragment = new other_search_fragment();
                        break;
                    case R.id.Notification:
                        fragment = new other_notification_fragment();
                        break;
                    case R.id.Profile:
                        fragment = new Profile_Other();
                        break;
                }
            }
            return loadFragment(fragment);
        }


    };
}
