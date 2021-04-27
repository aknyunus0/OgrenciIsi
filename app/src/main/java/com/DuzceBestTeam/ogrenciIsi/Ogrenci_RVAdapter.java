package com.DuzceBestTeam.ogrenciIsi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class Ogrenci_RVAdapter extends RecyclerView.Adapter<Ogrenci_RVAdapter.ViewHolder> {

    ArrayList<Ilan> ilanlar = new ArrayList<>();
    StorageReference mStorageReference;
    DatabaseReference mDatabaseReference;
    Context mContext = null;

    public Ogrenci_RVAdapter(ArrayList<Ilan> ilanlar) {
        this.ilanlar = ilanlar;
    }

    //Her bir satır için temsil edilece arayüz seçilir
    @NonNull
    @Override
    public Ogrenci_RVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recyclerview_other_item, parent, false);
        mContext=parent.getContext();
        return new Ogrenci_RVAdapter.ViewHolder(view);
    }
    //Her bir görünümün içeriği belirlenir
    @Override
    public void onBindViewHolder(@NonNull final Ogrenci_RVAdapter.ViewHolder holder, final int position) {



        holder.ilanbaslik.setText(ilanlar.get(position).getIlanBasligi());
        holder.ilantanimi.setText(ilanlar.get(position).getIlanTanimi());
        holder.ilanYayinTarihi.setText(ilanlar.get(position).getIlanYayinTarihi());
        holder.ilanSahibi.setText(ilanlar.get(position).getIlanVeren());


        holder.İlanVerenProfil.setOnClickListener(new View.OnClickListener() {

            //Hatali olmuyor
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(mContext, showProfil.class);
                myIntent.putExtra("UserKey",ilanlar.get(position).getUserKey().toString());
                mContext.startActivity(myIntent);

            }
        });



        Uri uri = Uri.parse(ilanlar.get(position).getProfilPic());

        Glide.with(holder.İlanVerenProfil.getContext()).load(uri).centerCrop().into(holder.İlanVerenProfil);


        holder.linearLayout.setTag(holder);



    }
    @Override
    public int getItemCount() {
        return ilanlar.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ilanbaslik;
        TextView ilantanimi;
        Button Btn_contact;
        LinearLayout linearLayout;
        TextView ilanSahibi, ilanYayinTarihi;
        ShapeableImageView İlanVerenProfil;

        Button saklaGoster;
        ConstraintLayout constraintDetay;

        public String UserNameReturn(String User_Id){
            final String[] Ad = new String[1];
            final String Soyad;



            return ilanSahibi.getText().toString();


        }



        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            ilanbaslik = itemView.findViewById(R.id.ilanBaslik);
            ilantanimi = itemView.findViewById(R.id.ilanTanimi);
            ilanSahibi = itemView.findViewById(R.id.ilanSahibi);
            ilanYayinTarihi = itemView.findViewById(R.id.ilanYayinTarihi);
            İlanVerenProfil =  itemView.findViewById(R.id.İlanVerenProfil);


            Btn_contact = itemView.findViewById(R.id.Btn_contact);
            linearLayout = itemView.findViewById(R.id.other_linearLayout);

            saklaGoster = itemView.findViewById(R.id.saklaGoster);
            constraintDetay = itemView.findViewById(R.id.other_constraint_detay);
            saklaGoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (constraintDetay.getVisibility()==View.VISIBLE)
                    {
                        saklaGoster.setBackgroundResource(R.drawable.arrow_down);
                        constraintDetay.setVisibility(View.GONE);
                    }
                    else if (constraintDetay.getVisibility()==View.GONE){
                        saklaGoster.setBackgroundResource(R.drawable.arrow_up);
                        constraintDetay.setVisibility(View.VISIBLE);
                    }
                }
            });



        }
    }
}