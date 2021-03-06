package com.DuzceBestTeam.ogrenciIsi;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class other_notification_fragment extends Fragment {
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

    public other_notification_fragment() {
        // Required empty public constructor
    }

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
        View view = inflater.inflate(R.layout.fragment_other_notification, container, false);
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
        notifications.clear();
        mAuth = FirebaseAuth.getInstance();

        //ilan??n?? ??ekece??imiz other user oldu??u i??in o database referans?? atan??r
        mDatabase = FirebaseDatabase.getInstance().getReference().child("User_Other");

        //??u anki kullan??c??n??n kendi ilanlar??n?? g??rmesi gerekti??inden aktif kullan??c??n??n ilanlar??m tablosuna referans y??nlendirilir
        //ve o tablo dinlemeye al??n??r
        mDatabase.child(mAuth.getCurrentUser().getUid()).child("Ilanlarim")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //kullan??c??n??n t??m ilanlar??n?? listelemek gerekti??inden
                        //ilanlar tablosunun alt??ndaki b??t??n ilan keyleri ilanlar
                        //de??i??kenine atan??r
                        Iterator<DataSnapshot> ilanlar = snapshot.getChildren().iterator();
                        notifications.clear();

                        //sonraki eleman kalmay??ncaya kadar her bir ilan i??in d??ng?? kurulur
                        while (ilanlar.hasNext()) {

                            //d??ng?? s??ras??nda gelen yeni ilan?? tutacak de??i??ken
                            DataSnapshot ilan = ilanlar.next();
                            final String IlanKey = ilan.getKey();

                            //e??er ilana en az bir ba??vuran varsa ba??vurular alt tablosu olu??aca????ndan
                            //ilk ??nce bu tablonun olu??up olu??mad?????? kontrol edilir
                            if (ilan.hasChild("Basvurular")) {
                                //her bir ilana birden fazla ba??vuran olaca????ndan bu ba??vuralar??
                                //d??ng??de kullanabilmek i??in hepsini tutan de??i??ken
                                Iterator<DataSnapshot> basvurular = ilan.child("Basvurular").getChildren().iterator();


                                //sonraki ba??vuru kalmay??ncaya kadar d??ng?? kurulur
                                while (basvurular.hasNext()) {
                                    //d??ng?? s??ras??nda yeni ba??vuruyu tutacak de??i??ken
                                    DataSnapshot basvuru = basvurular.next();

                                    //ba??vuru yap??lan ilan??n ba??l??????
                                    final String ilanBasligi = ilan.child("Ilan Basligi").getValue().toString();

                                    //ba??vuran ki??inin durumu 2 ise
                                    //yani beklemedeyse g??sterilece??inden onun kontrolu yap??l??r
                                    if (basvuru.child("Durum").getValue().toString().equals("2")) {
                                        //basvuran ki??inin bilgilerini getirmek i??in kullan??c??lacak key
                                        String basvuranKey = basvuru.getKey();

                                        //other user ilanlar??na ????renciler ba??vurabilece??inden
                                        //????renciler database alt??nda ba??vuran ki??inin keyi referans?? al??n??r
                                        mDatabase1 = FirebaseDatabase.getInstance().getReference().child("User_Ogrenciler").child(basvuranKey);

                                        //ba??vuranan ????rencinin bilgileri dinlemeye al??n??r
                                        mDatabase1.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                //ba??vuran ki??inin bilgileri al??n??r
                                                String basvuranAd = snapshot.child("Ad").getValue().toString();
                                                String basvuranSoyAd = snapshot.child("Soyad").getValue().toString();
                                                String basvuranResim = snapshot.child("Profil Resmi").getValue().toString();

                                                //son olarak bu am??nakodu??umun bildirimine ekleme yap??l??r
                                                notifications.add(new Notification(basvuranAd + " " + basvuranSoyAd, ilanBasligi, "2", basvuranResim, snapshot.getKey(), IlanKey));
                                                recyclerView.setAdapter(new Other_RVAdapter_Notification(notifications));
                                                mDatabase.removeEventListener(this);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}