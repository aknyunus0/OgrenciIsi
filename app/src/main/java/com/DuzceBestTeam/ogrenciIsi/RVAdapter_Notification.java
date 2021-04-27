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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class RVAdapter_Notification  extends RecyclerView.Adapter<RVAdapter_Notification.ViewHolder> {

    ArrayList<Notification> notifications = new ArrayList<>();
    DatabaseReference mDatabaseReference;
    FirebaseAuth mAuth;
    Context mContext = null;

    public RVAdapter_Notification(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recyclerview_notification, parent, false);
        mContext=parent.getContext();
        return new RVAdapter_Notification.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.ilanBaslik.setText(notifications.get(position).getIlanBasligi());
        holder.adayIsim.setText(notifications.get(position).getUserName());

        Uri uri = Uri.parse(notifications.get(position).getPrifilPic());
        Glide.with(holder.AdayPic.getContext()).load(uri).centerCrop().into(holder.AdayPic);

        holder.linearLayout.setTag(holder);

        holder.AdayPic.setOnClickListener(new View.OnClickListener() {

            //Hatali olmuyor
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(mContext, showProfil.class);
                myIntent.putExtra("UserKey",notifications.get(position).getUserKey());
                mContext.startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView adayIsim;
        TextView ilanBaslik;
        Button kabulEt,redEt;
        LinearLayout linearLayout;
        ShapeableImageView AdayPic;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            adayIsim = itemView.findViewById(R.id.adayIsim);
            ilanBaslik = itemView.findViewById(R.id.ilanBaslik);
            kabulEt = itemView.findViewById(R.id.kabulEt);
            redEt = itemView.findViewById(R.id.redEt);
            AdayPic =  itemView.findViewById(R.id.AdayPic);
            linearLayout = itemView.findViewById(R.id.other_linearLayout);
        }
    }
}
