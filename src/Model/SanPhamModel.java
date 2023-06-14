/*
 * Võ Cao Thùy Huyên
 * 22211TT2935
 */

package Model;

import Compare.HangTangMaTang;
import com.mysql.cj.xdevapi.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.objects.NativeArray.map;
import static jdk.nashorn.internal.objects.NativeDebug.map;

public class SanPhamModel {

    //THÊM SẢN PHẨM
    public static boolean themSanPham(SanPham sp) {
        try {
            return DAO.SanPhamDAO.themSanPham(sp);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //XÓA SẢN PHẨM
    public static boolean xoaSanPham(int maSP) {
        try {
            return DAO.SanPhamDAO.xoaSanPham(maSP);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //SỬA SẢN PHẨM
    public static boolean suaSanPham(SanPham sp) {
        try {
            return DAO.SanPhamDAO.suaSanPham(sp);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // LẤY TẤT XẢ SẢN PHẨM
    public static ArrayList<SanPham> layTatCaSanPham() {
        try {
            return DAO.SanPhamDAO.layTatCaSanPham();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //TÌM KIẾM SẢN PHẨM THEO TÊN SẢN PHẨM
    public static ArrayList<SanPham> timKiem(String tenSP) {
        ArrayList<SanPham> listSanPham = new ArrayList<>();
        try {
            Connection con = DAO.DatabaseUtil.getConnection();
            String sqltimKiem = "select* from sanpham where tensp like" + "'%" + tenSP + "%'";
            PreparedStatement ps = con.prepareStatement(sqltimKiem);
            ResultSet rs = (ResultSet) ps.executeQuery();
            while (rs.next()) {
                listSanPham.add(new SanPham(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6)));
            }
            return listSanPham;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //SẮP XẾP SẢN PHẨM TĂNG DẦN THEO MÃ HÃNG

    public static ArrayList<SanPham> sapXepSanPhamTangDanTheoMaHang(ArrayList<SanPham> lOfSP) {
        Collections.sort(lOfSP);
        return lOfSP;
    }

    //SẮP XẾP SẢN PHẨM TĂNG DẦN THEO MÃ HÃNG RỒI ĐẾN MÃ SẢN PHẨM
    public static ArrayList<SanPham> sapXepSanPhamTangDanTheoMaHangDenMaSanPham(ArrayList<SanPham> lOfSP) {
        Collections.sort(lOfSP, new Compare.HangTangMaTang());
        return lOfSP;
    }
    //SẮP XẾP SẢN PHẨM TĂNG DẦN THEO MÃ HÃNG RỒI ĐẾN MÃ SẢN PHẨM GIẢM

    public static ArrayList<SanPham> sapXepSanPhamTangDanTheoMaHangDenMaSanPhamGiam(ArrayList<SanPham> lOfSP) {
        Collections.sort(lOfSP, new Compare.HangTangMaGiam());
        return lOfSP;
    }

    //LẤY SẢN PHẨM THEO MÃ (KẾT BẢNG)"LAY SAN PHAM THEO LOAI"
    public static ArrayList<SanPham> laySanPhamTheoLoai(String loai) {
        ArrayList<SanPham> lst = new ArrayList<>();
        try {
            Connection con = DAO.DatabaseUtil.getConnection();
            String sql = "select * from sanpham where loaisp = (?) ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, loai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lst.add(new SanPham(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6)));
            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

  

    ////    
    //lay ma sp tu db
    public static ArrayList<String> takeColMaSP() {
        ArrayList<String> arr = new ArrayList<String>();
        try {

            Connection con = DAO.DatabaseUtil.getConnection();
            String sql = "select masp from sanpham";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                arr.add(rs.getString(1));
            }
            return arr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
