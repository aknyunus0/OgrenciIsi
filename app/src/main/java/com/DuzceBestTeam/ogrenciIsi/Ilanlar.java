package com.DuzceBestTeam.ogrenciIsi;

import java.util.ArrayList;
import java.util.List;

public class Ilanlar {
    String ilanAdi;
    String isTanimi;
    String pozisyon;
    String basvuruTarihi;
    String calismaSekli;
    String arananOzellikler;


    List<Ilanlar> ilanlar;

    public Ilanlar(String ilanAdi, String isTanimi, String pozisyon, String basvuruTarihi, String calismaSekli, String arananOzellikler) {
        this.ilanAdi = ilanAdi;
        this.isTanimi = isTanimi;
        this.pozisyon = pozisyon;
        this.basvuruTarihi = basvuruTarihi;
        this.calismaSekli = calismaSekli;
        this.arananOzellikler = arananOzellikler;
    }

    private void initializeData(){
        ilanlar = new ArrayList<>();
        ilanlar.add(new Ilanlar("Senior Design","Orta Duzey Yonetici","Almanya","Amazon","Tam Zamanli","15 Mayis"));
        ilanlar.add(new Ilanlar("Senior Design","Orta Duzey Yonetici","Almanya","Amazon","Tam Zamanli","15 Mayis"));
        ilanlar.add(new Ilanlar("Senior Design","Orta Duzey Yonetici","Almanya","Amazon","Tam Zamanli","15 Mayis"));
        ilanlar.add(new Ilanlar("Senior Design","Orta Duzey Yonetici","Almanya","Amazon","Tam Zamanli","15 Mayis"));
    }
}

