package com.DuzceBestTeam.ogrenciIsi;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Ogrenci_RVAdapter_Notification  extends RecyclerView.Adapter<Ogrenci_RVAdapter_Notification.ViewHolder> {

    ArrayList<Notification> notifications = new ArrayList<>();
    DatabaseReference mDatabaseReference;
    FirebaseAuth mAuth;
    Context mContext = null;

    public Ogrenci_RVAdapter_Notification(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recyclerview_nothification_ogrenci, parent, false);
        mContext=parent.getContext();
        return new Ogrenci_RVAdapter_Notification.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ilanBaslik.setText(notifications.get(position).IlanBasligi);

        Log.i("yunus",notifications.get(position).getDurum());


        if(notifications.get(position).getDurum().equals("1")){
            holder.onayCheck.setVisibility(View.VISIBLE);
            holder.onayRed.setVisibility(View.GONE);
            holder.onayBekle.setVisibility(View.GONE);
            holder.mesajGonder.setVisibility(View.VISIBLE);
        }
        if(notifications.get(position).getDurum().equals("0")){
            holder.onayCheck.setVisibility(View.GONE);
            holder.onayRed.setVisibility(View.VISIBLE);
            holder.onayBekle.setVisibility(View.GONE);
            holder.mesajGonder.setVisibility(View.GONE);
        }
        if(notifications.get(position).getDurum().equals("2")){
            holder.onayCheck.setVisibility(View.GONE);
            holder.onayRed.setVisibility(View.GONE);
            holder.onayBekle.setVisibility(View.VISIBLE);
            holder.mesajGonder.setVisibility(View.GONE);
        }
        Uri uri = Uri.parse(notifications.get(position).getPrifilPic());
        Glide.with(holder.AdayPic.getContext()).load(uri).centerCrop().into(holder.AdayPic);


        holder.linearLayout.setTag(holder);



    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ilanSahibi;
        TextView ilanBaslik;
        ImageView onayCheck,onayRed,onayBekle;
        LinearLayout linearLayout;
        ShapeableImageView AdayPic;
        Button mesajGonder;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            ilanSahibi = itemView.findViewById(R.id.ilanSahibi);
            ilanBaslik = itemView.findViewById(R.id.ilanBaslik);
            AdayPic =  itemView.findViewById(R.id.AdayPic);
            onayCheck =  itemView.findViewById(R.id.onayCheck);
            onayRed =  itemView.findViewById(R.id.onayRed);
            onayBekle =  itemView.findViewById(R.id.onayBekle);
            mesajGonder =  itemView.findViewById(R.id.mesajGonder);
            linearLayout = itemView.findViewById(R.id.other_linearLayout);
            mDatabaseReference= FirebaseDatabase.getInstance().getReference();
        }
    }
}

