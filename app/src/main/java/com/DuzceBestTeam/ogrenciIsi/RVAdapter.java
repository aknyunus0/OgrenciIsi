package com.DuzceBestTeam.ogrenciIsi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.embersoft.expandabletextview.ExpandableTextView;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    ArrayList<Ilan> ilanlar = new ArrayList<>();

    public RVAdapter(ArrayList<Ilan> ilanlar) {
        this.ilanlar = ilanlar;
    }

    //Her bir satır için temsil edilece arayüz seçilir
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycle_item, parent, false);
        return new ViewHolder(view);
    }


    //Her bir görünümün içeriği belirlenir
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txt_ilanbaslik.setText(ilanlar.get(position).getIlanAdi());
        holder.txt_ilantanimi.setText(ilanlar.get(position).getIsTanimi());
        holder.item_constraint.setTag(holder);

    }

    @Override
    public int getItemCount() {
        return ilanlar.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_ilanbaslik;
        ExpandableTextView txt_ilantanimi;
        LinearLayout item_constraint;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            txt_ilanbaslik = itemView.findViewById(R.id.ilanBaslik);
            txt_ilantanimi = itemView.findViewById(R.id.ilanTanimi);
            item_constraint = itemView.findViewById(R.id.item_constraint);

        }
    }
}
