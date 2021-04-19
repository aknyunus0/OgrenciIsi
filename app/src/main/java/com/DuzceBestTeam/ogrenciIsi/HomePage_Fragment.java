package com.DuzceBestTeam.ogrenciIsi;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;


public class HomePage_Fragment extends Fragment {

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    ArrayList<Ilan> ilanlar;
    RecyclerView recyclerView;
    Context context;
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

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ilanlar =  new ArrayList<>();

        verileriCek();




        return view;
    }

    private void verileriCek() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Ilanlar");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterator<DataSnapshot> items = snapshot.getChildren().iterator();
                ilanlar.clear();
                while (items.hasNext())
                {
                    DataSnapshot item = items.next();
                    String ilanAdi = item.child("Ilan Başlığı").getValue().toString();
                    String isTanimi = item.child("İş Tanımı").getValue().toString();
                    String ilanAranan = item.child("Aranan Özellikler").getValue().toString();
                    String ilanSonTarih = item.child("Son Başvuru Tarihi").getValue().toString();
                    String ilanPozisyon = item.child("Pozisyon").getValue().toString();
                    ilanlar.add(new Ilan(ilanAdi, isTanimi,ilanPozisyon,ilanSonTarih,"a",ilanAranan));

                }

                recyclerView.setAdapter(new RVAdapter(ilanlar));

                mDatabase.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}