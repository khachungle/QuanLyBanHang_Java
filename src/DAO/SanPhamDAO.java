/*
 * Võ Cao Thùy Huyên
 * 22211TT2935
 */

package DAO;

import Model.SanPham;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SanPhamDAO {

    // Lấy tất cả
    public static ArrayList<SanPham> layTatCaSanPham() {
        ArrayList<SanPham> lOfSP = new ArrayList<>();
        try {
            Connection con = DAO.DatabaseUtil.getConnection();
            String sqlSua = "select* from sanpham";
            PreparedStatement ps = con.prepareStatement(sqlSua);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lOfSP.add(new SanPham(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6)));
            }
            return lOfSP;

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
    
    // Add
    public static boolean themSanPham(SanPham sp) throws Exception {
        int check = -1;
        try {
            Connection con = DAO.DatabaseUtil.getConnection();
            String sqlThem = "insert into sanpham values (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sqlThem);
            ps.setInt(1, sp.getMaSP());
            ps.setString(2, sp.getTenSP());
            ps.setString(3, sp.getLoaiSP());
            ps.setDouble(4, sp.getGiaSP());
            ps.setInt(5, sp.getSoLuongSP());
            ps.setInt(6, sp.getMaHang());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check == 1;
    }

    // Delete
    public static boolean xoaSanPham(int maSP) throws Exception {
        int check = -1;
        try {
            Connection con = DAO.DatabaseUtil.getConnection();
            String sqlXoa = "delete from sanpham where masp = (?)";
            PreparedStatement ps = con.prepareStatement(sqlXoa);
            ps.setInt(1, maSP);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check == 1;
    }

    // Update
    public static boolean suaSanPham(SanPham sp) {

        int check = -1;
        try {
            Connection con = DAO.DatabaseUtil.getConnection();
            String sqlSua = "update sanpham set tensp = (?), loaisp = (?), giasp =(?), soluongsp =(?), mahang = (?) where masp = (?)";
            PreparedStatement ps = con.prepareStatement(sqlSua);
            ps.setString(1, sp.getTenSP());
            ps.setString(2, sp.getLoaiSP());
            ps.setDouble(3, sp.getGiaSP());
            ps.setInt(4, sp.getSoLuongSP());
            ps.setInt(5, sp.getMaHang());
            ps.setInt(6, sp.getMaSP());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check == 1;
    }
}
