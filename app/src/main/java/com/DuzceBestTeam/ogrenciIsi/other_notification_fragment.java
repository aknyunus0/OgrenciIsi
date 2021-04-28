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
        mAuth = FirebaseAuth.getInstance();

        //ilanını çekeceğimiz other user olduğu için o database referansı atanır
        mDatabase = FirebaseDatabase.getInstance().getReference().child("User_Other");

        //şu anki kullanıcının kendi ilanlarını görmesi gerektiğinden aktif kullanıcının ilanlarım tablosuna referans yönlendirilir
        //ve o tablo dinlemeye alınır
        mDatabase.child(mAuth.getCurrentUser().getUid()).child("Ilanlarim")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //kullanıcının tüm ilanlarını listelemek gerektiğinden
                        //ilanlar tablosunun altındaki bütün ilan keyleri ilanlar
                        //değişkenine atanır
                        Iterator<DataSnapshot> ilanlar = snapshot.getChildren().iterator();
                        notifications.clear();

                        //sonraki eleman kalmayıncaya kadar her bir ilan için döngü kurulur
                        while (ilanlar.hasNext()) {

                            //döngü sırasında gelen yeni ilanı tutacak değişken
                            DataSnapshot ilan = ilanlar.next();
                            final String IlanKey=ilan.getKey();

                            //eğer ilana en az bir başvuran varsa başvurular alt tablosu oluşacağından
                            //ilk önce bu tablonun oluşup oluşmadığı kontrol edilir
                            if (ilan.hasChild("Basvurular")) {
                                //her bir ilana birden fazla başvuran olacağından bu başvuraları
                                //döngüde kullanabilmek için hepsini tutan değişken
                                Iterator<DataSnapshot> basvurular = ilan.child("Basvurular").getChildren().iterator();


                                //sonraki başvuru kalmayıncaya kadar döngü kurulur
                                while (basvurular.hasNext()) {
                                    //döngü sırasında yeni başvuruyu tutacak değişken
                                    DataSnapshot basvuru = basvurular.next();

                                    //başvuru yapılan ilanın başlığı
                                    final String ilanBasligi = ilan.child("Ilan Basligi").getValue().toString();

                                    //başvuran kişinin durumu 2 ise
                                    //yani beklemedeyse gösterileceğinden onun kontrolu yapılır
                                    if(basvuru.child("Durum").getValue().toString().equals("2"))
                                    {
                                        //basvuran kişinin bilgilerini getirmek için kullanıcılacak key
                                        String basvuranKey = basvuru.getKey();

                                        //other user ilanlarına öğrenciler başvurabileceğinden
                                        //öğrenciler database altında başvuran kişinin keyi referansı alınır
                                        mDatabase1 = FirebaseDatabase.getInstance().getReference().child("User_Ogrenciler").child(basvuranKey);

                                        //başvuranan öğrencinin bilgileri dinlemeye alınır
                                        mDatabase1.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                //başvuran kişinin bilgileri alınır
                                                String basvuranAd = snapshot.child("Ad").getValue().toString();
                                                String basvuranSoyAd = snapshot.child("Soyad").getValue().toString();
                                                String basvuranResim = snapshot.child("Profil Resmi").getValue().toString();

                                                //son olarak bu amınakoduğumun bildirimine ekleme yapılır
                                                notifications.add(new Notification(basvuranAd + " " + basvuranSoyAd, ilanBasligi, "2", basvuranResim, snapshot.getKey(),IlanKey));
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