/*
 * Võ Cao Thùy Huyên
 * 22211TT2935
 */

package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HangModel {
    //THÊM HÃNG
    public static boolean themHang(Hang hang) {
        try {
            return DAO.HangDAO.themHang(hang);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //XÓA HÃNG
    public static boolean xoaHang(int maHang) {
        try {
            return DAO.HangDAO.xoaHang(maHang);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //SỬA HÃNG
    public static boolean suaHang(Hang hang) {
        try {
            return DAO.HangDAO.suaHang(hang);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //LẤY TẤT CẢ HÃNG
    public static ArrayList<Hang> layTatCaHang() {
        try {
            return DAO.HangDAO.layTatCaHang();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //LẤY RA CỘT MÃ HÃNG ĐỂ KIỂM TRA
    public static ArrayList<String> takeColMaHang(){
        ArrayList<String> lst = new ArrayList<String>();
          try {

            Connection con = DAO.DatabaseUtil.getConnection();
            String sql = "SELECT mahang FROM hang";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lst.add(rs.getString(1));
            }
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
   
    
}
