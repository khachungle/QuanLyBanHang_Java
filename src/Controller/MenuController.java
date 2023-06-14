/*
 * Võ Cao Thùy Huyên
 * 22211TT2935
 */

package Controller;

import Model.HangModel;
import Model.SanPhamModel;
import View.HangView;
import View.MenuView;
import View.SanPhamView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController {

    MenuView view;

    public MenuController(MenuView view) {
        this.view = view;
        init();
        view.setVisible(true);
    }

    private void init() {
        view.getBtn_sanPham().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new SanPhamController(new SanPhamModel(), new SanPhamView());
            }
        });
        
        view.getBtn_hang().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new HangController(new HangModel(), new HangView());
            }
        });
    }
}
