package com.DuzceBestTeam.ogrenciIsi;

import java.util.ArrayList;
import java.util.List;

public class Ilan {
    String ilanBasligi;
    String ilanTanimi;
    String ilanPozisyon;
    String ilanSonBasvuruTarih;
    String ilanCalismaSekli;
    String ilanArananOzellikler;
    String ilanVeren;
    String ilanYayinTarihi;


    public Ilan(String ilanBasligi, String ilanTanimi, String ilanPozisyon, String ilanSonBasvuruTarih, String ilanCalismaSekli, String ilanArananOzellikler, String ilanVeren, String ilanYayinTarihi
    ) {
        this.ilanBasligi = ilanBasligi;
        this.ilanTanimi = ilanTanimi;
        this.ilanPozisyon = ilanPozisyon;
        this.ilanSonBasvuruTarih = ilanSonBasvuruTarih;
        this.ilanCalismaSekli = ilanCalismaSekli;
        this.ilanArananOzellikler = ilanArananOzellikler;
        this.ilanVeren = ilanVeren;
        this.ilanYayinTarihi = ilanYayinTarihi;
    }

    public String getIlanBasligi() {
        return ilanBasligi;
    }

    public void setIlanBasligi(String ilanBasligi) {
        this.ilanBasligi = ilanBasligi;
    }

    public String getIlanTanimi() {
        return ilanTanimi;
    }

    public void setIlanTanimi(String ilanTanimi) {
        this.ilanTanimi = ilanTanimi;
    }

    public String getIlanPozisyon() {
        return ilanPozisyon;
    }

    public void setIlanPozisyon(String ilanPozisyon) {
        this.ilanPozisyon = ilanPozisyon;
    }

    public String getIlanSonBasvuruTarih() {
        return ilanSonBasvuruTarih;
    }

    public void setIlanSonBasvuruTarih(String ilanSonBasvuruTarih) {
        this.ilanSonBasvuruTarih = ilanSonBasvuruTarih;
    }

    public String getIlanCalismaSekli() {
        return ilanCalismaSekli;
    }

    public void setIlanCalismaSekli(String ilanCalismaSekli) {
        this.ilanCalismaSekli = ilanCalismaSekli;
    }

    public String getIlanArananOzellikler() {
        return ilanArananOzellikler;
    }

    public void setIlanArananOzellikler(String ilanArananOzellikler) {
        this.ilanArananOzellikler = ilanArananOzellikler;
    }

    public String getIlanVeren() {
        return ilanVeren;
    }

    public void setIlanVeren(String ilanVeren) {
        this.ilanVeren = ilanVeren;
    }

    public String getIlanYayinTarihi() {
        return ilanYayinTarihi;
    }

    public void setIlanYayinTarihi(String ilanYayinTarihi) {
        this.ilanYayinTarihi = ilanYayinTarihi;
    }

}
