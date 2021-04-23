package com.DuzceBestTeam.ogrenciIsi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ProfileEdit_Other extends AppCompatActivity {
    ShapeableImageView other_profile_image1;
    EditText other_editname,other_editsurname,other_KonumEdit, other_HakkimdaEdit, other_sirketEdit,other_uzmanlikEdit;
    Button Btn_editprofile_other;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    private FirebaseStorage mStorage;
    private StorageReference mStorageReferance;
    public Uri imageUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit_other);
        initComponents();

        setUserInfoToEdit();

        //var olan profil resmini yükleme
        Uri uri = Uri.parse(User.userprofileimage);
        Glide
                .with(getApplicationContext())
                .load(uri) // the uri you got from Firebase
                .centerCrop()
                .into(other_profile_image1); //Your imageView variable


        Btn_editprofile_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mDatabase.child("Ad").setValue(other_editname.getText().toString());
                mDatabase.child("Hakkında").setValue(other_HakkimdaEdit.getText().toString());
                mDatabase.child("Soyad").setValue(other_editsurname.getText().toString());
                mDatabase.child("konum").setValue(other_KonumEdit.getText().toString());
                mDatabase.child("uzmanlık").setValue(other_uzmanlikEdit.getText().toString());
                mDatabase.child("Sirket").setValue(other_sirketEdit.getText().toString());


                User.userLocation = other_KonumEdit.getText().toString();
                User.userCompany = other_sirketEdit.getText().toString();
                User.userExpert = other_uzmanlikEdit.getText().toString();
                User.userAbout = other_HakkimdaEdit.getText().toString();
                User.userExpert = other_uzmanlikEdit.getText().toString();
                User.userName = other_editname.getText().toString();
                User.userSurName = other_editsurname.getText().toString();


                startActivity(new Intent(ProfileEdit_Other.this, AnaSayfa.class));
                ProfileEdit_Other.this.finish();

            }
        });

        other_profile_image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChoosePicture();

                mStorageReferance.child("image").child(mAuth.getCurrentUser().getUid()).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        //  Toast.makeText(getApplicationContext(),task.getResult().toString(),Toast.LENGTH_LONG).show();
                        mDatabase.child("Profil Resmi").setValue(task.getResult().toString());
                    }
                });
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User.userprofileimage = snapshot.child("Profil Resmi").getValue().toString();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

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
            other_profile_image1.setImageURI(imageUri);
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
        other_editname.setText(User.userName);
        other_editsurname.setText(User.userSurName);
        other_KonumEdit.setText(User.userLocation);
        other_HakkimdaEdit.setText(User.userAbout);
        other_uzmanlikEdit.setText(User.userExpert);
        other_sirketEdit.setText(User.userCompany);




    }

    private void initComponents() {

        other_editname=findViewById(R.id.other_editname);
        other_editsurname=findViewById(R.id.other_editsurname);
        other_HakkimdaEdit=findViewById(R.id.other_HakkimdaEdit);
        other_KonumEdit=findViewById(R.id.other_KonumEdit);
        other_profile_image1=findViewById(R.id.other_profile_image1);
        other_sirketEdit=findViewById(R.id.other_sirketEdit);
        other_uzmanlikEdit=findViewById(R.id.other_uzmanlikEdit);
        Btn_editprofile_other=findViewById(R.id.Btn_editprofile_other);
        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference().child(User.userDatabase).child(mAuth.getCurrentUser().getUid());
        mStorage= FirebaseStorage.getInstance();
        mStorageReferance=mStorage.getReference();

    }
}