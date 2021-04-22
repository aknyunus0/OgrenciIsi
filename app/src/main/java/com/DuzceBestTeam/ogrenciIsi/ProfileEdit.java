package com.DuzceBestTeam.ogrenciIsi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URL;
import java.util.UUID;

public class ProfileEdit extends AppCompatActivity {
    Spinner spinner;
    EditText editname, editsurname,KonumEdit,UniversiteEdit,BolumEdit,HakkimdaEdit,UzamanlikEdit;
    Button Btn_editprofile;
    Switch gizliswitch;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    ShapeableImageView imageView;
    public Uri imageUri;
    private FirebaseStorage mStorage;
    private StorageReference mStorageReferance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        initComponents();

        setUserInfoToEdit();

        //var olan profil resmini yükleme
        Uri uri=Uri.parse(User.userprofileimage);
        Glide
                .with(getApplicationContext())
                .load(uri) // the uri you got from Firebase
                .centerCrop()
                .into(imageView); //Your imageView variable


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
                mDatabase.child("Hakkında").setValue(HakkimdaEdit.getText().toString());
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

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChoosePicture();

                mStorageReferance.child("image").child(mAuth.getCurrentUser().getUid()).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                      //  Toast.makeText(getApplicationContext(),task.getResult().toString(),Toast.LENGTH_LONG).show();
                        mDatabase.child("Profil Resmi").setValue(task.getResult().toString());
                        User.userprofileimage = task.getResult().toString();
                    }
                });
            }
        });

    }

    private void ChoosePicture() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

           if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
               imageUri = data.getData();
               imageView.setImageURI(imageUri);
               uploadPicture();

           }
    }

    private void uploadPicture() {
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("Fotoğraf Yükleniyor...");
        pd.show();
        final  String randomkey= FirebaseAuth.getInstance().getCurrentUser().getUid();


        StorageReference mountainsRef = mStorageReferance.child("image/"+randomkey);
        mountainsRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pd.dismiss();
                Snackbar.make(findViewById(android.R.id.content),"image Uploaded",Snackbar.LENGTH_SHORT).show();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(),"BAŞARAMADIK ABİ",Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progresspercent=(100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                pd.setMessage("yüzde"+(int)progresspercent+"%");
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
        imageView=findViewById(R.id.profile_image1);
        mStorage=FirebaseStorage.getInstance();
        mStorageReferance=mStorage.getReference();

    }
}