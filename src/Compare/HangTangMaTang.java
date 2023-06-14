/*
 * Võ Cao Thùy Huyên
 * 22211TT2935
 */

package Compare;

import Model.SanPham;
import java.util.Comparator;

//SẮP XẾP HÃNG TĂNG DẦN THEO SẢN PHẨM ĐẾN MÃ TĂNG DẦN
public class HangTangMaTang implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        SanPham s1 = (SanPham) o1;
        SanPham s2 = (SanPham) o2;
        if (s1.getMaHang() == s2.getMaHang()) {
            if (s1.getMaSP() == s2.getMaSP()) {
                return 0;
            } else if (s1.getMaSP() > s2.getMaSP()) {
                return 1;
            } else {
                return -1;
            }
        } else if (s1.getMaHang() > s2.getMaHang()) {
            return 1;
        } else {
            return -1;
        }
    }
}
