package com.DuzceBestTeam.ogrenciIsi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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


public class HomePage_Fragment extends Fragment {

    FirebaseAuth mAuth;
    DatabaseReference mDatabase,mDatabase1;
    ArrayList<Ilan> ilanlar;
    RecyclerView recyclerView;
    Context context;
    SwipeRefreshLayout swipeRefresh;
    StorageReference mStorageReference = FirebaseStorage.getInstance().getReference();
    String ilanVerenProfilResmiLink;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomePage_Fragment() {
        // Required empty public constructor
    }




    public static HomePage_Fragment newInstance(String param1, String param2) {
        HomePage_Fragment fragment = new HomePage_Fragment();
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

        View view = inflater.inflate(R.layout.fragment_home_page_, container, false);
        context = view.getContext().getApplicationContext();
        recyclerView = view.findViewById(R.id.recyclerView);
        swipeRefresh = view.findViewById(R.id.swipRefresh);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                verileriCek();
                swipeRefresh.setRefreshing(false);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ilanlar =  new ArrayList<>();

        verileriCek();




        return view;
    }

    private void verileriCek() {
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
                    final String ilanAranan = item.child("Aranan Özellikler").getValue().toString();
                    final String ilanSonTarih = item.child("Son Başvuru Tarihi").getValue().toString();
                    final String ilanPozisyon = item.child("Pozisyon").getValue().toString();
                    final String ilanVeren = item.child("İş Veren").getValue().toString();
                    final String ilanCalismaTuru = item.child("Calışma Şekli").getValue().toString();
                    final String ilanYayinTarihi = item.child("yayın tarihi").getValue().toString();

                    mDatabase1 = FirebaseDatabase.getInstance().getReference().child("User_Other").child(ilanVeren);
                    mDatabase1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot2) {
                            String Ad= snapshot2.child("Ad").getValue(String.class);
                            String Soyad=snapshot2.child("Soyad").getValue(String.class);
                            String ProfilPic=snapshot2.child("Profil Resmi").getValue(String.class);
                            ilanlar.add(new Ilan(ilanAdi, isTanimi, ilanPozisyon, ilanSonTarih, ilanCalismaTuru, ilanAranan,
                                    Ad+" "+Soyad, ilanYayinTarihi,ProfilPic));
                            Collections.reverse(ilanlar);

                            recyclerView.setAdapter(new RVAdapter(ilanlar));

                            mDatabase.removeEventListener(this);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });

                    /*
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
                    */


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}