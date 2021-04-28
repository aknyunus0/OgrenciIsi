package com.DuzceBestTeam.ogrenciIsi;

public class Notification {
    String UserName;
    String IlanBasligi;
    String Durum;
    String PrifilPic;
    String UserKey;
    String IlanKey;
    public Notification(String ilanBasligi, String durum, String prifilPic, String userKey,String ilanlKey) {
        IlanBasligi = ilanBasligi;
        Durum = durum;
        PrifilPic = prifilPic;
        UserKey = userKey;
        IlanKey=ilanlKey;
    }
    public Notification(String userName, String ilanBasligi, String durum, String prifilPic, String userKey,String ilanlKey) {
        UserName = userName;
        IlanBasligi = ilanBasligi;
        Durum = durum;
        PrifilPic = prifilPic;
        UserKey = userKey;
        IlanKey=ilanlKey;
    }

    public String getIlanKey() {
        return IlanKey;
    }

    public void setIlanKey(String ilanKey) {
        IlanKey = ilanKey;
    }



    public String getUserKey() {
        return UserKey;
    }

    public void setUserKey(String userKey) {
        UserKey = userKey;
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
