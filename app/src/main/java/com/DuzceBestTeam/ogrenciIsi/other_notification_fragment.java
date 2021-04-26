package com.DuzceBestTeam.ogrenciIsi;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link other_notification_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class other_notification_fragment extends Fragment {
    FirebaseAuth mAuth;
    DatabaseReference mDatabase,mDatabase1;
    ArrayList<Notification> Notifications;
    RecyclerView recyclerView;
    Context context;
    SwipeRefreshLayout swipeRefresh;
    StorageReference mStorageReference = FirebaseStorage.getInstance().getReference();
    String ilanVerenProfilResmiLink;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public other_notification_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment other_notification_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static other_notification_fragment newInstance(String param1, String param2) {
        other_notification_fragment fragment = new other_notification_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // verileriCek();
        return inflater.inflate(R.layout.fragment_other_notification, container, false);




    }

  /*  private void verileriCek() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Other_Ilanlar");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterator<DataSnapshot> items = snapshot.getChildren().iterator();
                ilanlar.clear();
                while (items.hasNext())
                {
                    DataSnapshot item = items.next();
                    final String ilanAdi = item.child("Ilan Başlığı").getValue().toString();
                    final String isTanimi = item.child("İş Tanımı").getValue().toString();
                    final String ilanVerenKey = item.child("İş Veren").getValue().toString();
                    final String ilanYayinTarihi = item.child("yayın tarihi").getValue().toString();
                    final String ilanKey=item.getKey();
                    mDatabase1 = FirebaseDatabase.getInstance().getReference().child("User_Ogrenciler").child(ilanVerenKey);
                    mDatabase1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot2) {
                            String Ad= snapshot2.child("Ad").getValue(String.class);
                            String Soyad=snapshot2.child("Soyad").getValue(String.class);
                            String ProfilPic=snapshot2.child("Profil Resmi").getValue(String.class);

                            ilanlar.add(new Ilan(ilanAdi,isTanimi,Ad+" "+Soyad,ilanYayinTarihi,ProfilPic,ilanVerenKey,ilanKey));
                            Collections.reverse(ilanlar);

                            recyclerView.setAdapter(new Ogrenci_RVAdapter(ilanlar));

                            mDatabase.removeEventListener(this);



                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            throw error.toException();

                        }
                    });





                    mStorageReference.child("image").child(ilanVeren).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful())
                            {
                                ilanVerenProfilResmiLink = task.getResult().toString();
                                Toast.makeText(context,ilanVerenProfilResmiLink , Toast.LENGTH_LONG).show();
                            }
                        }
                    });



                }

                Collections.reverse(ilanlar);

                recyclerView.setAdapter(new Ogrenci_RVAdapter(ilanlar));

                mDatabase.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }*/
}