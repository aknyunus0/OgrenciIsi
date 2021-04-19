package com.DuzceBestTeam.ogrenciIsi;

import java.util.ArrayList;
import java.util.List;

public class Ilan {
    String ilanAdi;
    String isTanimi;
    String pozisyon;
    String basvuruTarihi;
    String calismaSekli;
    String arananOzellikler;
    String ilanVeren;
    String ilanYayinTarihi;

    public Ilan(String ilanAdi, String isTanimi, String pozisyon, String basvuruTarihi, String calismaSekli, String arananOzellikler, String ilanVeren, String ilanYayinTarihi) {
        this.ilanAdi = ilanAdi;
        this.isTanimi = isTanimi;
        this.pozisyon = pozisyon;
        this.basvuruTarihi = basvuruTarihi;
        this.calismaSekli = calismaSekli;
        this.arananOzellikler = arananOzellikler;
        this.ilanVeren = ilanVeren;
        this.ilanYayinTarihi = ilanYayinTarihi;
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

    public String getIlanAdi() {
        return ilanAdi;
    }

    public void setIlanAdi(String ilanAdi) {
        this.ilanAdi = ilanAdi;
    }

    public String getIsTanimi() {
        return isTanimi;
    }

    public void setIsTanimi(String isTanimi) {
        this.isTanimi = isTanimi;
    }

    public String getPozisyon() {
        return pozisyon;
    }

    public void setPozisyon(String pozisyon) {
        this.pozisyon = pozisyon;
    }

    public String getBasvuruTarihi() {
        return basvuruTarihi;
    }

    public void setBasvuruTarihi(String basvuruTarihi) {
        this.basvuruTarihi = basvuruTarihi;
    }

    public String getCalismaSekli() {
        return calismaSekli;
    }

    public void setCalismaSekli(String calismaSekli) {
        this.calismaSekli = calismaSekli;
    }

    public String getArananOzellikler() {
        return arananOzellikler;
    }

    public void setArananOzellikler(String arananOzellikler) {
        this.arananOzellikler = arananOzellikler;
    }

}
