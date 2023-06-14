/*
 * Võ Cao Thùy Huyên
 * 22211TT2935
 */

package Model;

import java.util.Comparator;

public class SanPham implements Comparable<SanPham>{

    //field
    private int maSP;
    private String tenSP, loaiSP;
    private double giaSP;
    private int soLuongSP, maHang;

    //constructor
    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public double getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(double giaSP) {
        this.giaSP = giaSP;
    }

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

    public int getMaHang() {
        return maHang;
    }

    public void setMaHang(int maHang) {
        this.maHang = maHang;
    }

    public SanPham() {
    }

    public SanPham(int maSP, String tenSP, String loaiSP, double giaSP, int soLuongSP, int maHang) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.loaiSP = loaiSP;
        this.giaSP = giaSP;
        this.soLuongSP = soLuongSP;
        this.maHang = maHang;
    }
    //method

    public String toString() {
        return "Ma: " + maSP + " - " + "Ten: " + tenSP + " - " + "Loai: " + loaiSP + " - " + "Gia: " + giaSP + " - " + "So luong: " + soLuongSP + " - " + "Ma hang: " + maHang;
    }

    
    // sap xep theo ma hang
    @Override
    public int compareTo(SanPham o) {
        if (maHang == o.getMaHang()) {
            return 0;
        }else if (maHang > o.getMaHang()) {
            return 1;
        }else{
            return -1;
        }
    }
}
 