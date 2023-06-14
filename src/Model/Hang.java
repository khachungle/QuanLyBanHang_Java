/*
 * Võ Cao Thùy Huyên
 * 22211TT2935
 */

package Model;

public class Hang {

    //field
    private int maHang;
    private String tenHang, diaChi, email;

    //constructor
    public int getMaHang() {
        return maHang;
    }

    public void setMaHang(int maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Hang() {
    }

    public Hang(int maHang, String tenHang, String diaChi, String email) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.diaChi = diaChi;
        this.email = email;
    }
    //Method

    public String toString() {
        return "Ma hang: " + maHang + " - " + "Ten hang: " + tenHang + " - " + "Dia chi: " + diaChi + " - " + "Email: " + email;
    }
    
}
