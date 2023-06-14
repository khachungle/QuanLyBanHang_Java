/*
 * Võ Cao Thùy Huyên
 * 22211TT2935
 */

package Main;

import Controller.HangController;
import Controller.MenuController;
import Controller.SanPhamController;
import Model.HangModel;
import Model.SanPhamModel;
import View.HangView;
import View.MenuView;
import View.SanPhamView;

// Chạy chương trình ở đây
public class Main {
    public static void main(String[] args) {
        new MenuController(new MenuView());
    }
}
