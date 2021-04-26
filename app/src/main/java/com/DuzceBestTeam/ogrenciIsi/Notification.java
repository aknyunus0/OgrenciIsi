package com.DuzceBestTeam.ogrenciIsi;

public class Notification {
    String UserName;
    String IlanBasligi;
    String Durum;
    String PrifilPic;



    public Notification(String userName, String ilanBasligi, String durum, String prifilPic) {
        UserName = userName;
        IlanBasligi = ilanBasligi;
        Durum = durum;
        PrifilPic = prifilPic;
    }

    public String getUserName() {

        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }


    public String getIlanBasligi() {
        return IlanBasligi;
    }

    public void setIlanBasligi(String ilanBasligi) {
        IlanBasligi = ilanBasligi;
    }

    public String getDurum() {
        return Durum;
    }

    public void setDurum(String durum) {
        Durum = durum;
    }

    public String getPrifilPic() {
        return PrifilPic;
    }

    public void setPrifilPic(String prifilPic) {
        PrifilPic = prifilPic;
    }


}
