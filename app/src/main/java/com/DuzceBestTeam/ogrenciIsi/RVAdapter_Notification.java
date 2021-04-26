package com.DuzceBestTeam.ogrenciIsi;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

public class RVAdapter_Notification  extends RecyclerView.Adapter<RVAdapter_Notification.ViewHolder> {


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 0;
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
