/*
 * Võ Cao Thùy Huyên
 * 22211TT2935
 */

package Controller;

import Model.Hang;
import Model.HangModel;
import Model.SanPham;
import Model.SanPhamModel;

import View.HangView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HangController {

    HangModel model;
    HangView view;

    public HangController(HangModel hang, HangView view) {
        this.model = model;
        this.view = view;
        init();
        view.setVisible(true);
    }

    private void init() {
        // Hiển thị table
        showTable();
        
        // btn Them
        view.getBtn_them().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (checkValueTextfields() == false) {
                    JOptionPane.showMessageDialog(view, "Không được để trống !", "Thông báo !", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (checkCodeDupdicate(Integer.parseInt(view.getTxt_maHang().getText())) == true) {
                        JOptionPane.showMessageDialog(view, "Mã hàng đã tồn tại, vui lòng thử lại!", "Thông báo !", JOptionPane.WARNING_MESSAGE);

                    } else {
                        int ma = Integer.parseInt(view.getTxt_maHang().getText());
                        String ten = (String) (view.getTxt_ten().getText());
                        String diaChi = (String) (view.getTxt_diaChi().getText());
                        String email = (String) (view.getTxt_email().getText());
                        model.themHang(new Hang(ma, ten, diaChi, email));
                        showTable();

                        JOptionPane.showMessageDialog(view, "Thêm thành công!", "Thông báo !", JOptionPane.INFORMATION_MESSAGE);
                    }
                    reset();
                }

            }
        });
        //NÚT XÓA
        view.getBtn_xoa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getTxt_maHang().getText().length() == 0) {
                    JOptionPane.showMessageDialog(view, "Vui lòng nhập mã", "Thông báo !", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (checkCodeDupdicate(Integer.parseInt(view.getTxt_maHang().getText())) == false) {
                        JOptionPane.showMessageDialog(view, "Mã hãng không tồn tại!", "Thông báo !", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                    } else {
                        int ma = Integer.parseInt(view.getTxt_maHang().getText());
                        int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc muốn xóa ?", "Thông báo !", JOptionPane.OK_CANCEL_OPTION);
                        if (confirm == JOptionPane.OK_OPTION) {
                            model.xoaHang(ma);
                            JOptionPane.showMessageDialog(view, "Xóa thành công !", "Thông báo !", JOptionPane.INFORMATION_MESSAGE);
                            showTable();
                        }
                        reset();
                    }
                }
            }
        });
        //NÚT SỬA
        view.getBtn_sua().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getTxt_maHang().getText().length() == 0) {
                    JOptionPane.showMessageDialog(view, "Vui lòng nhập mã cần sửa !", "Thông báo !", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (checkCodeDupdicate(Integer.parseInt(view.getTxt_maHang().getText())) == true) {
                        if (checkValueTextfields() == false) {
                            JOptionPane.showMessageDialog(view, "Không được để trống !", "Thông báo !", JOptionPane.WARNING_MESSAGE);
                        } else {
                            int ma = Integer.parseInt(view.getTxt_maHang().getText());
                            String ten = (String) (view.getTxt_ten().getText());
                            String diaChi = (String) (view.getTxt_diaChi().getText());
                            String email = (String) (view.getTxt_email().getText());
                            model.suaHang(new Hang(ma, ten, diaChi, email));
                            showTable();
                            reset();
                            JOptionPane.showMessageDialog(view, "Sửa thành công !", "Thông báo !", JOptionPane.INFORMATION_MESSAGE);

                        }
                    } else {
                        JOptionPane.showMessageDialog(view, "Mã hàng không tồn tại !", "Thông báo !", JOptionPane.WARNING_MESSAGE);

                    }

                }

            }
        });

        //NÚT RESET
        view.getBtn_reset().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        //NÚT THOÁT
        view.getBtn_thoat().addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent ae) {
                view.dispose();
            }

        });
        //CLICK TABLE
        view.getTbl_hang().addMouseListener(tableLOfHangListener());

    }

    //SHOW BẢNG
    private void showTable() {
        DefaultTableModel tbModel = (DefaultTableModel) view.getTbl_hang().getModel();
        tbModel.setColumnCount(0);
        tbModel.setRowCount(0);
        tbModel.addColumn("Ma");
        tbModel.addColumn("Ten");
        tbModel.addColumn("Dia chi");
        tbModel.addColumn("Email");

        ArrayList<Hang> lOfHang = model.layTatCaHang();
        for (Hang item : lOfHang) {
            tbModel.addRow(new Object[]{item.getMaHang(), item.getTenHang(), item.getDiaChi(), item.getEmail()});
        }
        view.getTbl_hang().setModel(tbModel);
        view.getTbl_hang().setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    }

    private void showTableNew(ArrayList<Hang> lOfHang) {
        DefaultTableModel tbModel = (DefaultTableModel) view.getTbl_hang().getModel();
        tbModel.setColumnCount(0);
        tbModel.setRowCount(0);
        tbModel.addColumn("Ma");
        tbModel.addColumn("Ten");
        tbModel.addColumn("Dia chi");
        tbModel.addColumn("Email");
        for (Hang item : lOfHang) {
            tbModel.addRow(new Object[]{item.getMaHang(), item.getTenHang(), item.getDiaChi(), item.getEmail()});
        }
        view.getTbl_hang().setModel(tbModel);
        view.getTbl_hang().setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    }

    //RESET
    private void reset() {
        view.getTxt_ten().setText("");
        view.getTxt_diaChi().setText("");
        view.getTxt_email().setText("");
        view.getTxt_maHang().setText("");
    }

    //CELL CLICK   
    private MouseAdapter tableLOfHangListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                int i = view.getTbl_hang().getSelectedRow();
                view.getTxt_maHang().setText((view.getTbl_hang().getValueAt(i, 0)).toString());
                view.getTxt_ten().setText((String) view.getTbl_hang().getValueAt(i, 1));
                view.getTxt_diaChi().setText((String) view.getTbl_hang().getValueAt(i, 2));
                view.getTxt_email().setText((view.getTbl_hang().getValueAt(i, 3)).toString());
            }
        };
    }

    private boolean checkValueTextfields() {
        if (view.getTxt_ten().getText().length() == 0) {
            return false;
        }
        if (view.getTxt_diaChi().getText().length() == 0) {
            return false;
        }
        if (view.getTxt_email().getText().length() == 0) {
            return false;
        }
        if (view.getTxt_maHang().getText().length() == 0) {
            return false;
        }
        return true;
    }

    private boolean checkCodeDupdicate(int maHang) {
        int index = view.getTbl_hang().getRowCount();
        ArrayList<String> lst = model.takeColMaHang();
        for (String string : lst) {
            if (Integer.parseInt(string.toString()) == maHang) {
                return true;
            }
        }
        return false;
    }
}
