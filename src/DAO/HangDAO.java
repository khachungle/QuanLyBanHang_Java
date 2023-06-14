/*
 * Võ Cao Thùy Huyên
 * 22211TT2935
 */

package DAO;

import Model.Hang;
import Model.SanPham;
import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HangDAO {

     // Lây tất cả
    public static ArrayList<Hang> layTatCaHang() {
        ArrayList<Hang> lOfHang = new ArrayList<>();
        try {
            Connection con = DAO.DatabaseUtil.getConnection();
            String sqlSua = "select* from hang";
            PreparedStatement ps = con.prepareStatement(sqlSua);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lOfHang.add(new Hang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            return lOfHang;

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
    
    // Add
    public static boolean themHang(Hang hang) throws Exception {
        int check = -1;
        try {
            Connection con = DAO.DatabaseUtil.getConnection();
            String sqlThem = "insert into hang values (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sqlThem);
            ps.setInt(1, hang.getMaHang());
            ps.setString(2, hang.getTenHang());
            ps.setString(3, hang.getDiaChi());
            ps.setString(4, hang.getEmail());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check == 1;
    }

    // Delete
    public static boolean xoaHang(int maHang) {
        int check = -1;
        try {
            Connection con = DAO.DatabaseUtil.getConnection();
            String sqlXoa = "delete from hang where mahang = (?)";
            PreparedStatement ps = con.prepareStatement(sqlXoa);
            ps.setInt(1, maHang);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check == 1;
    }
    
    // Update
    public static boolean suaHang(Hang hang) throws Exception {
        int check = -1;
        try {
            Connection con = DAO.DatabaseUtil.getConnection();
            String sqlSua = "update hang set tenhang = (?), diachi = (?), email =(?)where mahang = (?)";
            PreparedStatement ps = con.prepareStatement(sqlSua);
            ps.setString(1, hang.getTenHang());
            ps.setString(2, hang.getDiaChi());
            ps.setString(3, hang.getEmail());
            ps.setInt(4, hang.getMaHang());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check == 1;
    }
}
