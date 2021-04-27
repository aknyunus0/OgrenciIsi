package com.DuzceBestTeam.ogrenciIsi;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class Notification_Fragment extends Fragment {


    FirebaseAuth mAuth;
    DatabaseReference mDatabase, mDatabase1;
    ArrayList<Notification> notifications;
    RecyclerView recyclerView;
    Context context;
    SwipeRefreshLayout swipeRefresh;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Notification_Fragment() {
        // Required empty public constructor
    }


    public static Notification_Fragment newInstance(String param1, String param2) {
        Notification_Fragment fragment = new Notification_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_notification_, container, false);
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
        notifications = new ArrayList<>();

        verileriCek();

        return view;
    }


    private void verileriCek() {
        /*
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("User_Other");
        mDatabase.child(mAuth.getCurrentUser().getUid()).child("Ilanlarim")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Iterator<DataSnapshot> items = snapshot.getChildren().iterator();
                        notifications.clear();

                        while (items.hasNext()) {
                            DataSnapshot item = items.next();
                            final String ilanBasligi = item.child("Ilan Basligi").getValue().toString();
                            notifications.add(new Notification("user name", ilanBasligi, "2", "https://kstu.edu.tr/kstu-file/uploads/default-user-image.png"));
                            recyclerView.setAdapter(new RVAdapter_Notification(notifications));
                            mDatabase.removeEventListener(this);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

         */
    }
}




















