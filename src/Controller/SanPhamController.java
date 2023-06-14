/*
 * Võ Cao Thùy Huyên
 * 22211TT2935
 */
package Controller;

import Model.SanPham;
import Model.SanPhamModel;
import View.SanPhamView;
import com.sun.java.swing.plaf.windows.resources.windows;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class SanPhamController {

    SanPhamModel model;
    SanPhamView view;

    public SanPhamController(SanPhamModel model, SanPhamView view) {
        this.model = model;
        this.view = view;
        init();
        view.setVisible(true);
    }

    private void init() {
        // Hiển thị bảng
        showTable();
        
        // Add
        view.getBtn_them().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (checkValueTextFields() == false) {
                    JOptionPane.showMessageDialog(view, "Không được để trống!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                } else if (checkValueDupdicate(Integer.parseInt(view.getTxt_ma().getText())) == false) {
                    JOptionPane.showMessageDialog(view, "Mã đã tồn tại, vui lòng thử lại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (checkValueTextFields() == true) {
                        int ma = Integer.parseInt(view.getTxt_ma().getText());
                        String ten = view.getTxt_ten().getText();
                        String loai = view.getTxt_loai().getText();
                        double gia = 0;
                        if (CheckNumDouble(view.getTxt_gia().getText()) == true) {
                            gia = Double.parseDouble(view.getTxt_gia().getText());
                        } else {

                            gia = Double.parseDouble(view.getTxt_gia().getText().substring(0, view.getTxt_gia().getText().length() - 2));

                        }
                        int soLuong = Integer.parseInt(view.getTxt_soLuong().getText());
                        int maHang = Integer.parseInt(view.getTxt_maHang().getText());
                        model.themSanPham(new SanPham(ma, ten, loai, gia, soLuong, maHang));
                        showTable();
                        reset();
                        JOptionPane.showMessageDialog(view, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(view, "Không được để trống !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            }
        });
        
        // Delete
        view.getBtn_xoa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getTxt_ma().getText().length() == 0) {
                    JOptionPane.showMessageDialog(view, "Vui lòng nhập mã cần xóa !", "Thông báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (checkValueDupdicate(Integer.parseInt(view.getTxt_ma().getText())) == true) {
                        JOptionPane.showMessageDialog(view, "Mã không tồn tại !", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    } else {
                        int maSP = Integer.valueOf(view.getTxt_ma().getText());
                        int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn xóa ?", "Thông báo!", JOptionPane.OK_OPTION);
                        if (confirm == JOptionPane.OK_OPTION) {
                            model.xoaSanPham(maSP);
                            showTable();
                            reset();
                            JOptionPane.showMessageDialog(view, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });
        
        // Update
        view.getBtn_sua().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getTxt_ma().getText().length() == 0) {
                    JOptionPane.showMessageDialog(view, "Vui lòng nhập mã sản phầm cần sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (checkValueDupdicate(Integer.parseInt(view.getTxt_ma().getText())) == true) {
                        JOptionPane.showMessageDialog(view, "Mã không tồn tại !", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    } else {
                        if (checkValueTextFields() == true) {
                            int masp = Integer.parseInt(view.getTxt_ma().getText());
                            String tensp = view.getTxt_ten().getText();
                            String loai = view.getTxt_loai().getText();
                            double gia = 0;
                            if (CheckNumDouble(view.getTxt_gia().getText()) == true) {
                                gia = Double.parseDouble(view.getTxt_gia().getText());
                            } else {

                                gia = Double.parseDouble(view.getTxt_gia().getText().substring(0, view.getTxt_gia().getText().length() - 2));

                            }
                            int soLuong = Integer.parseInt(view.getTxt_soLuong().getText());
                            int maHang = Integer.parseInt(view.getTxt_maHang().getText());
                            SanPham sp = new SanPham(masp, tensp, loai, gia, soLuong, maHang);
                            model.suaSanPham(sp);
                            showTable();
                            reset();
                            JOptionPane.showMessageDialog(view, "Sửa thành công !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(view, "Không được để trống !", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        });
        
        // Search
        view.getBtn_timKiem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = view.getTxt_timKiem().getText();
                DefaultTableModel tbModel = (DefaultTableModel) view.getTbl_sanPham().getModel();
                ArrayList<SanPham> listSanPham = model.timKiem(key);
                showTableNew(listSanPham);
                view.getTxt_timKiem().setText("");
            }
        });
        
        // Reset
        view.getBtn_reset().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        
        // Click table
        view.getTbl_sanPham().addMouseListener(tableLOfSanPhamListener());
        
        // Combobox chọn loại sản phẩm
        view.getCbo_loai().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = view.getCbo_loai().getSelectedIndex();
                ArrayList<SanPham> lst = new ArrayList<>();
                if (value == 0) {
                    lst = model.layTatCaSanPham();
                    showTableNew(lst);
                } else if (value == 1) {
                    lst = model.laySanPhamTheoLoai("Dien thoai");
                    showTableNew(lst);
                } else if (value == 2) {
                    lst = model.laySanPhamTheoLoai("May tinh bang");
                    showTableNew(lst);
                } else if (value == 3) {
                    lst = model.laySanPhamTheoLoai("Dong ho");
                    showTableNew(lst);
                }
            }
        });
        
        // Combobox sắp xếp sản phẩm
        view.getCbo_sapXep().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = view.getCbo_sapXep().getSelectedIndex();

                ArrayList<SanPham> lOfSP = takeDataFromJTable();
                if (value == 0) {
                    Collections.sort(lOfSP, new Compare.CompareDefaut());
                    showTableNew(lOfSP);
                } else if (value == 1) {
                    lOfSP = model.sapXepSanPhamTangDanTheoMaHang(lOfSP);
                    showTableNew(lOfSP);
                } else if (value == 2) {
                    lOfSP = model.sapXepSanPhamTangDanTheoMaHangDenMaSanPham(lOfSP);
                    showTableNew(lOfSP);
                } else {
                    lOfSP = model.sapXepSanPhamTangDanTheoMaHangDenMaSanPhamGiam(lOfSP);
                    showTableNew(lOfSP);
                }
            }
        });
        
        // Exit
        view.getBtn_thoat().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                view.dispose();
            }
        });
    }

    // Hiển thị dữ liệu vào các Text Fields khi click vào ô trong bảng
    private MouseAdapter tableLOfSanPhamListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                int i = view.getTbl_sanPham().getSelectedRow();
                view.getTxt_ma().setText((view.getTbl_sanPham().getValueAt(i, 0)).toString());
                view.getTxt_ten().setText((String) view.getTbl_sanPham().getValueAt(i, 1));
                view.getTxt_loai().setText((String) view.getTbl_sanPham().getValueAt(i, 2));
                view.getTxt_gia().setText((view.getTbl_sanPham().getValueAt(i, 3)).toString());
                view.getTxt_soLuong().setText((view.getTbl_sanPham().getValueAt(i, 4)).toString());
                view.getTxt_maHang().setText((view.getTbl_sanPham().getValueAt(i, 5)).toString());
            }
        };
    }

    // Lấy dữ liệu từ bảng
    private ArrayList<SanPham> takeDataFromJTable() {
        ArrayList<SanPham> listSanPham = new ArrayList<>();
        int col = view.getTbl_sanPham().getColumnCount();
        int row = view.getTbl_sanPham().getRowCount();
        int masp = -1;
        String tensp = "";
        String loai = "";
        double gia = 0;
        int soLuong = 0;
        int maHang = 0;
        for (int i = 0; i < row; i++) {

            masp = Integer.parseInt(view.getTbl_sanPham().getValueAt(i, 0).toString());
            tensp = (String) view.getTbl_sanPham().getValueAt(i, 1);
            loai = (view.getTbl_sanPham().getValueAt(i, 2)).toString();
            gia = Double.parseDouble(view.getTbl_sanPham().getValueAt(i, 3).toString());
            soLuong = Integer.parseInt(view.getTbl_sanPham().getValueAt(i, 4).toString());
            maHang = Integer.parseInt(view.getTbl_sanPham().getValueAt(i, 5).toString());
            listSanPham.add(new SanPham(masp, tensp, loai, gia, soLuong, maHang));
        }
        return listSanPham;
    }

    // Hiển thị dữ liệu
    private void showTable() {
        DefaultTableModel tbModel = (DefaultTableModel) view.getTbl_sanPham().getModel();
        tbModel.setColumnCount(0);
        tbModel.setRowCount(0);
        tbModel.addColumn("Mã");
        tbModel.addColumn("Tên");
        tbModel.addColumn("Loại");
        tbModel.addColumn("Giá");
        tbModel.addColumn("Số lượng");
        tbModel.addColumn("Mã hàng");
        ArrayList<SanPham> lOfSP = SanPhamModel.layTatCaSanPham();
        for (SanPham item : lOfSP) {
            tbModel.addRow(new Object[]{item.getMaSP(), item.getTenSP(), item.getLoaiSP(), item.getGiaSP(), item.getSoLuongSP(), item.getMaHang()});
        }
        view.getTbl_sanPham().setModel(tbModel);
        view.getTbl_sanPham().setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    }

    private void showTableNew(ArrayList<SanPham> lOfSP) {
        DefaultTableModel tbModel = (DefaultTableModel) view.getTbl_sanPham().getModel();
        tbModel.setRowCount(0);
        tbModel.addColumn("Mã");
        tbModel.addColumn("Tên");
        tbModel.addColumn("Loại");
        tbModel.addColumn("Giá");
        tbModel.addColumn("Số lượng");
        tbModel.addColumn("Mã hàng");
        
        // Thêm dữ liệu vào bảng
        for (SanPham item : lOfSP) {
            tbModel.addRow(new Object[]{item.getMaSP(), item.getTenSP(), item.getLoaiSP(), item.getGiaSP(), item.getSoLuongSP(), item.getMaHang()});
        }
        view.getTbl_sanPham().setModel(tbModel);
        view.getTbl_sanPham().setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    }

    // Làm mới dữ liệu cho các TextFields
    private void reset() {
        view.getTxt_ma().setText("");
        view.getTxt_ten().setText("");
        view.getTxt_loai().setText("");
        view.getTxt_gia().setText("");
        view.getTxt_soLuong().setText("");
        view.getTxt_maHang().setText("");
    }

    public static void main(String[] args) {
    }
    
    // Kiểm tra trùng mã
    public boolean checkValueDupdicate(int ma) {

        ArrayList<String> lstMaSP = model.takeColMaSP();
        for (String string : lstMaSP) {
            if (Integer.parseInt(string.toString()) == ma) {
                return false;
            }
        }
        return true;
    }

    // Hàm kiểm tra có TextField nào trống không
    public boolean checkValueTextFields() {
        if (view.getTxt_ma().getText().length() == 0) {
            return false;
        }
        if (view.getTxt_gia().getText().length() == 0) {
            return false;
        }
        if (view.getTxt_loai().getText().length() == 0) {
            return false;
        }
        if (view.getTxt_maHang().getText().length() == 0) {
            return false;
        }
        if (view.getTxt_soLuong().getText().length() == 0) {
            return false;
        }
        if (view.getTxt_ten().getText().length() == 0) {
            return false;
        }
        return true;
    }

    // Kiểm tra chuỗi trả về có phải kiểu Int hay Double không
    public boolean CheckNumDouble(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
