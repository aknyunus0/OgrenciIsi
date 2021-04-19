package com.DuzceBestTeam.ogrenciIsi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        holder.ilanbaslik.setText(ilanlar.get(position).getIlanAdi());
        holder.ilantanimi.setText(ilanlar.get(position).getIsTanimi());
        holder.ilanPozisyon.setText(ilanlar.get(position).getPozisyon());
        holder.ilanAciklama.setText(ilanlar.get(position).getArananOzellikler());
        holder.ilansontarih.setText(ilanlar.get(position).getBasvuruTarihi());
        holder.linearLayout.setTag(holder);

    }

    @Override
    public int getItemCount() {
        return ilanlar.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ilanbaslik;
        TextView ilantanimi;
        TextView ilanPozisyon, ilansontarih, ilanAciklama;
        Button btnBasvur;
        LinearLayout linearLayout;

        Button btnDevamGor;
        ConstraintLayout constraintDetay;



        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            ilanbaslik = itemView.findViewById(R.id.ilanBaslik);
            ilantanimi = itemView.findViewById(R.id.ilanTanimi);
            ilanPozisyon = itemView.findViewById(R.id.ilanPozisyon);
            ilansontarih = itemView.findViewById(R.id.ilansontarih);
            ilanAciklama = itemView.findViewById(R.id.ilanAciklama);
            btnBasvur = itemView.findViewById(R.id.btnBasvur);
            linearLayout = itemView.findViewById(R.id.linearLayout);

            btnDevamGor = itemView.findViewById(R.id.btnDevamGor);
            constraintDetay = itemView.findViewById(R.id.constraint_detay);
            btnDevamGor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    constraintDetay.setVisibility(View.VISIBLE);
                }
            });

        }
    }
}