package com.DuzceBestTeam.ogrenciIsi;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
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


public class other_homepage_fragment extends Fragment {
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
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

    public other_homepage_fragment() {

    }


    public static other_homepage_fragment newInstance(String param1, String param2) {
        other_homepage_fragment fragment = new other_homepage_fragment();
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
        View view = inflater.inflate(R.layout.fragment_other_homepage, container, false);
        context = view.getContext().getApplicationContext();
        recyclerView = view.findViewById(R.id.other_recyclerView);
        swipeRefresh = view.findViewById(R.id.other_swipRefresh);

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
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Ogrenci_Ilanlar");
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
                    String ilanVerenKey = item.child("İş Veren").getValue().toString();
                    String ilanYayinTarihi = item.child("yayın tarihi").getValue().toString();



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

                    ilanlar.add(new Ilan(ilanAdi,isTanimi, "İlan Verenin Adı Gelecek \n" + ilanVerenKey,ilanYayinTarihi));
                }

                Collections.reverse(ilanlar);

                recyclerView.setAdapter(new Ogrenci_RVAdapter(ilanlar));

                mDatabase.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}