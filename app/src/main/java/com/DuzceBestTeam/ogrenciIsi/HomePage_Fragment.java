package com.DuzceBestTeam.ogrenciIsi;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class HomePage_Fragment extends Fragment {

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

        ilanlar.add(new Ilan("Deneme1","Deneme1 is tanimi","deneme1 pozisyon","deneme1 pozisyon",
                "deneme1 çalışma şekli", "deneme1 aranan özellikler"));
        ilanlar.add(new Ilan("deneme2","deneme2 is tanimi","deneme2 pozisyon","deneme2 pozisyon",
                "deneme2 çalışma şekli", "deneme2 aranan özellikler"));
        ilanlar.add(new Ilan("deneme3","deneme3 is tanimi","deneme3 pozisyon","deneme3 pozisyon",
                "deneme3 çalışma şekli", "deneme3 aranan özellikler"));
        ilanlar.add(new Ilan("Deneme1","Deneme1 is tanimi","deneme1 pozisyon","deneme1 pozisyon",
                "deneme1 çalışma şekli", "deneme1 aranan özellikler"));
        ilanlar.add(new Ilan("deneme2","deneme2 is tanimi","deneme2 pozisyon","deneme2 pozisyon",
                "deneme2 çalışma şekli", "deneme2 aranan özellikler"));
        ilanlar.add(new Ilan("deneme3","deneme3 is tanimi","deneme3 pozisyon","deneme3 pozisyon",
                "deneme3 çalışma şekli", "deneme3 aranan özellikler"));
        ilanlar.add(new Ilan("Deneme1","Deneme1 is tanimi","deneme1 pozisyon","deneme1 pozisyon",
                "deneme1 çalışma şekli", "deneme1 aranan özellikler"));
        ilanlar.add(new Ilan("deneme2","deneme2 is tanimi","deneme2 pozisyon","deneme2 pozisyon",
                "deneme2 çalışma şekli", "deneme2 aranan özellikler"));
        ilanlar.add(new Ilan("deneme3","deneme3 is tanimi","deneme3 pozisyon","deneme3 pozisyon",
                "deneme3 çalışma şekli", "deneme3 aranan özellikler"));

        recyclerView.setAdapter(new RVAdapter(ilanlar));

        return view;
    }



}