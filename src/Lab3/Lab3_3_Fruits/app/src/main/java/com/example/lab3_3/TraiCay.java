package com.example.lab3_3;

public class TraiCay {
    private String Ten;
    private String Mota;
    private int Hinh = 0;
    private String URLHinh;

    public TraiCay(String ten, String mota, int hinh) {
        Ten = ten;
        Mota = mota;
        Hinh = hinh;
    }

    public TraiCay(String ten, String mota, String urlHinh) {
        Ten = ten;
        Mota = mota;
        URLHinh = urlHinh;
    }

    public String getURLHinh() {
        return URLHinh;
    }

    public void setURLHinh(String URLHinh) {
        this.URLHinh = URLHinh;
    }

    public String getTen() {
        return Ten;
    }

    public String getMota() {
        return Mota;
    }

    public int getHinh() {
        return Hinh;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public void setMota(String mota) {
        Mota = mota;
    }

    public void setHinh(int hinh) {
        Hinh = hinh;
    }
}
