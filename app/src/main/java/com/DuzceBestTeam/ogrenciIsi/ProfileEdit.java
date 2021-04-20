package com.DuzceBestTeam.ogrenciIsi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class ProfileEdit extends AppCompatActivity {
    Spinner spinner;
    EditText editname, editsurname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        initComponents();

        setUserInfoToEdit();


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
            R.array.egitimduzeyi, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

    private void setUserInfoToEdit() {
        editname.setText(User.userName);
        editsurname.setText(User.userSurName);

    }

    private void initComponents() {
        spinner = findViewById(R.id.Spinnerprofileedit);
        editname = findViewById(R.id.editname);
        editsurname=findViewById(R.id.editsurname);

    }
}