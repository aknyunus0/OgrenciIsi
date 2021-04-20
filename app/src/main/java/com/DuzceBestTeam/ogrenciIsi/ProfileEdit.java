package com.DuzceBestTeam.ogrenciIsi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileEdit extends AppCompatActivity {
    Spinner spinner;
    EditText editname, editsurname,KonumEdit,UniversiteEdit,BolumEdit,HakkimdaEdit,UzamanlikEdit;
    Button Btn_editprofile;
    Switch gizliswitch;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;


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



        Btn_editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( gizliswitch.isChecked() ){
                    User.isSecretProfile=true;
                }
                else{
                    User.isSecretProfile=false;
                }
                mDatabase.child("Ad").setValue(editname.getText().toString());
                mDatabase.child("Bölüm").setValue(BolumEdit.getText().toString());
                mDatabase.child("Gizli mi?").setValue(User.isSecretProfile);
                mDatabase.child("Hakkında").setValue(editname.getText().toString());
                mDatabase.child("Soyad").setValue(editsurname.getText().toString());
                mDatabase.child("eğitim düzeyi").setValue(spinner.getSelectedItem().toString());
                mDatabase.child("konum").setValue(KonumEdit.getText().toString());
                mDatabase.child("uzmanlık").setValue(UzamanlikEdit.getText().toString());
                mDatabase.child("üniversite").setValue(UniversiteEdit.getText().toString());

                User.userLocation=KonumEdit.getText().toString();
                User.userUniversity=UniversiteEdit.getText().toString();
                 User.userDepartman=BolumEdit.getText().toString();
                 User.userGrade=spinner.getSelectedItem().toString();
              User.userAbout=HakkimdaEdit.getText().toString();
               User.userExpert=UzamanlikEdit.getText().toString();

                startActivity(new Intent(ProfileEdit.this,AnaSayfa.class));
                ProfileEdit.this.finish();

            }
        });

    }

    private void setUserInfoToEdit() {
        editname.setText(User.userName);
        editsurname.setText(User.userSurName);
        KonumEdit.setText(User.userLocation);
        UniversiteEdit.setText(User.userUniversity);
        BolumEdit.setText(User.userDepartman);
        HakkimdaEdit.setText(User.userAbout);
        UzamanlikEdit.setText(User.userExpert);
        gizliswitch.setChecked(User.isSecretProfile);

    }

    private void initComponents() {
        spinner = findViewById(R.id.Spinnerprofileedit);
        editname = findViewById(R.id.editname);
        editsurname=findViewById(R.id.editsurname);
        KonumEdit=findViewById(R.id.KonumEdit);
        UniversiteEdit=findViewById(R.id.UniversiteEdit);
        BolumEdit=findViewById(R.id.BolumEdit);
        HakkimdaEdit=findViewById(R.id.HakkimdaEdit);
        UzamanlikEdit=findViewById(R.id.UzamanlikEdit);
        Btn_editprofile=findViewById(R.id.Btn_editprofile);
        gizliswitch=findViewById(R.id.gizliswitch);
        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference().child(User.userDatabase).child(mAuth.getCurrentUser().getUid());


    }
}