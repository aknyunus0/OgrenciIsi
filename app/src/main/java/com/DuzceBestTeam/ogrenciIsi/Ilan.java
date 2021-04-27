package com.DuzceBestTeam.ogrenciIsi;

public class Ilan {
    String ilanKey;
    String ilanBasligi;
    String ilanTanimi;
    String ilanPozisyon;
    String ilanSonBasvuruTarih;
    String ilanCalismaSekli;
    String ilanArananOzellikler;
    String ilanVeren;
    String ilanYayinTarihi;
    String ProfilPic;
    String UserKey;


    public String getProfilPic() {
        return ProfilPic;
    }

    public void setProfilPic(String profilPic) {
        ProfilPic = profilPic;
    }

    public String getUserKey() {
        return UserKey;
    }

    public void setUserKey(String userKey) {
        UserKey = userKey;
    }

    public String getIlanKey() {
        return ilanKey;
    }

    public void setIlanKey(String ilanKey) {
        this.ilanKey = ilanKey;
    }

    public Ilan(String ilanBasligi, String ilanTanimi, String ilanVeren, String ilanYayinTarihi, String ProfilPic, String UserKey, String ilanKey
    ) {
        this.ilanBasligi = ilanBasligi;
        this.ilanTanimi = ilanTanimi;
        this.ilanVeren = ilanVeren;
        this.ilanYayinTarihi = ilanYayinTarihi;
        this.ProfilPic = ProfilPic;
        this.UserKey=UserKey;
        this.ilanKey=ilanKey;
    }

    public Ilan(String ilanBasligi, String ilanTanimi, String ilanPozisyon, String ilanSonBasvuruTarih, String ilanCalismaSekli, String ilanArananOzellikler, String ilanVeren, String ilanYayinTarihi,String ProfilPic,String ilanKey,String UserKey
    ) {
        this.ilanBasligi = ilanBasligi;
        this.ilanTanimi = ilanTanimi;
        this.ilanPozisyon = ilanPozisyon;
        this.ilanSonBasvuruTarih = ilanSonBasvuruTarih;
        this.ilanCalismaSekli = ilanCalismaSekli;
        this.ilanArananOzellikler = ilanArananOzellikler;
        this.ilanVeren = ilanVeren;
        this.ilanYayinTarihi = ilanYayinTarihi;
        this.ProfilPic = ProfilPic;
        this.ilanKey=ilanKey;
        this.UserKey=UserKey;
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
