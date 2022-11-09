/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import domainmodels.BoNhoTrong;
import domainmodels.ChiTietSP;
import domainmodels.MauSac;
import domainmodels.SanPham;
import java.awt.CardLayout;
import java.awt.Image;
import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.AnhService;
import services.BoNhoTrongServices;
import services.ChiTietSPServices;
import services.IBoNhoTrongServices;
import services.IChiTietSPServices;
import services.IMauSacServices;
import services.ISanPhamServices;
import services.ImeiServices;
import services.MauSacServices;
import services.PhanQuyenServices;
import services.SanPhamService;
import viewmodels.ChiTietSPViewModels;

/**
 *
 * @author HANGOCHAN
 */
public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */
    CardLayout cardLayout;
    CardLayout cl;
    CardLayout c2;
    private ISanPhamServices sanPhamServices;
    private IMauSacServices mauSacServices;
    private IBoNhoTrongServices boNhoTrongServices;
    private IChiTietSPServices chiTietSPServices;

    public MainForm() {
        initComponents();
        
        setTitle("Hệ Thống Bán Điện Thoại");
       
        sanPhamServices = new SanPhamService();
        mauSacServices = new MauSacServices();
        boNhoTrongServices = new BoNhoTrongServices();
        chiTietSPServices = new ChiTietSPServices();
        cardLayout = (CardLayout) giaodien.getLayout();
        PhanQuyenServices pqs = new PhanQuyenServices();
        lbChao.setText("Xin chào : " + pqs.getHoTen());
        String chucVu = "";
        if (pqs.getVaiTro() == 0) {
            chucVu = "Quản lý";
        } else {
            chucVu = "Nhân viên";
        }
        lbChucVu.setText("Chức vụ : " + chucVu);

        cbbSanPham.removeAllItems();
        cbbMauSac.removeAllItems();
        cbbBoNhoTrong.removeAllItems();
        cbbImei.removeAllItems();
        List<SanPham> sanPhams = sanPhamServices.getALL();
        for (SanPham sanPham : sanPhams) {
            if (sanPham.getTrangThai() ==0) {
                cbbSanPham.addItem(sanPham.getTenSP());
            }
        }
        List<MauSac> mauSacs = mauSacServices.getALL();
        for (MauSac mauSac : mauSacs) {
            if (mauSac.getTrangThai() == 0) {
                cbbMauSac.addItem(mauSac.getTenMauSac());
            }
        }

        List<BoNhoTrong> bnts = boNhoTrongServices.getALL();
        for (BoNhoTrong bnt : bnts) {
            if (bnt.getTrangThai() == 0) {
                 cbbBoNhoTrong.addItem(String.valueOf(bnt.getDungLuong()));
            }
        }

    }

    public void hienThiSanPham() {
        DefaultTableModel model = (DefaultTableModel) tbSanPham.getModel();
        model.setRowCount(0);
        String maSP = "";
        List<SanPham> sanPhams = sanPhamServices.getALL();
        for (SanPham sanPham : sanPhams) {
            maSP = sanPham.getMaSP();
            ChiTietSPViewModels c = chiTietSPServices.load(maSP);
            if (c == null) {
                continue;
            }
            if (c.getTrangThai() == 0) {

                Object[] data = new Object[]{
                    c.getTenSP(),
                    c.getNsx(),
                    c.getMauSac(),
                    c.getBoNho(),
                    c.getRam(),
                    c.getCpu(),
                    c.getTonKho(),
                    c.getGiaBan()
                };
                model.addRow(data);
        }
//        List<ChiTietSPViewModels> list = chiTietSPServices.getALL();
//        for (ChiTietSPViewModels c : list) {
//            if (c.getTrangThai() == 0) {
//
//                Object[] data = new Object[]{
//                    c.getTenSP(),
//                    c.getNsx(),
//                    c.getMauSac(),
//                    c.getBoNho(),
//                    c.getRam(),
//                    c.getCpu(),
//                    c.getTonKho(),
//                    c.getGiaBan()
//                };
//                model.addRow(data);
//                
//            }

        }
    }

    public void fillSanPham() {
        int index = tbSanPham.getSelectedRow();
        String tenSP = tbSanPham.getValueAt(index, 0).toString();
        String soLuong = tbSanPham.getValueAt(index, 6).toString();
        List<SanPham> sanPhams = sanPhamServices.getALL();
        String maSP = "";
        for (SanPham sanPham : sanPhams) {
            if (tenSP.equals(sanPham.getTenSP())) {
                maSP = sanPham.getMaSP();
            }
        }
        cbbImei.removeAllItems();
        List<ChiTietSP> imei = chiTietSPServices.getImeibyMaSP(maSP);
        for (ChiTietSP chiTietSP : imei) {
            if (chiTietSP.getTrangThai() == 0) {
                cbbImei.addItem(chiTietSP.getMaImei());
            }
        }
        ChiTietSP c = chiTietSPServices.fill(maSP);
        ImageIcon icon = new ImageIcon(c.getAnh());
        Image image = icon.getImage().getScaledInstance(lbAnh.getWidth(), lbAnh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon m = new ImageIcon(image);
        lbAnh.setIcon(m);
        cbbSanPham.setSelectedItem(tenSP);
        txtGiaNhap.setText(String.valueOf(c.getGiaNhap()));
        txtGiaBan.setText(String.valueOf(c.getGiaBan()));
        txtGhiChu.setText(c.getMoTa());
        txtSoLuongTon.setText(soLuong);
        txtXuatXu.setText(c.getXuatXu());
        txtCpu.setText(c.getCpu());
        txtManHinh.setText(c.getManHinh());
        txtPin.setText(String.valueOf(c.getDungLuongPin()));
        cbbCamera.setSelectedItem(c.getCamera());
        cbbHeDieuHanh.setSelectedItem(c.getHeDieuHanh());
        cbbRam.setSelectedItem(String.valueOf(c.getRam()));
        cbbBoNhoTrong.setSelectedItem(String.valueOf(c.getBoNhoTrong().getDungLuong()));
        cbbMauSac.setSelectedItem(c.getMauSac().getTenMauSac());
        String ngayBh = "";
        if (c.getThoiGianBH() == 30) {
            ngayBh = "1 Tháng";
        }
        if (c.getThoiGianBH() == 90) {
            ngayBh = "3 Tháng";
        }if (c.getThoiGianBH() == 180) {
            ngayBh = "6 Tháng";
        } if (c.getThoiGianBH() == 360) {
            ngayBh = "12 Tháng";
        }
        cbbTGBH.setSelectedItem(ngayBh);
    }

    public ChiTietSP layTTSanPham() throws ParseException {
        String Imei = "";
        for (int i = 0; i < cbbImei.getHeight(); i++) {
            Imei = cbbImei.getItemAt(i);
        }
        double giaNhapdb = Double.parseDouble(txtGiaNhap.getText());
        BigDecimal giaNhap = BigDecimal.valueOf(giaNhapdb);
        double giaBandb = Double.parseDouble(txtGiaBan.getText());
        BigDecimal giaBan = BigDecimal.valueOf(giaBandb);
        String moTa = txtGhiChu.getText();
        Integer thoiGianBaoHanh = 0;
        String thoiGian = (String) cbbTGBH.getSelectedItem();
        if(thoiGian.equals("1 Tháng")) {
            thoiGianBaoHanh = 30;
        } else if (thoiGian.equals("3 Tháng")) {
            thoiGianBaoHanh = 90;
        } else if (thoiGian.equals("6 tháng")) {
            thoiGianBaoHanh = 180;
        } else {
            thoiGianBaoHanh = 360;
        }
        AnhService anhService = new AnhService();
        String anh = anhService.getAnh();
        String camera = (String) cbbCamera.getSelectedItem();
        String heDieuHanh = (String) cbbHeDieuHanh.getSelectedItem();
        String ramString = (String) cbbRam.getSelectedItem();
        Integer ram = Integer.parseInt(ramString);
        String cpu = txtCpu.getText();
        String manHinh = txtManHinh.getText();
        Integer pin = Integer.parseInt(txtPin.getText());
        String xuatXu = txtXuatXu.getText();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        ZonedDateTime now = ZonedDateTime.now();
        String ngayTao = dtf.format(now);
        Date date = new SimpleDateFormat("MM-dd-yyyy").parse(ngayTao);
        SanPham sp = null;
        String tenSP = (String) cbbSanPham.getSelectedItem();
        List<SanPham> sanPhams = sanPhamServices.getALL();
        for (SanPham sanPham : sanPhams) {
            if (tenSP.equals(sanPham.getTenSP())) {
                sp = sanPham;
            }
        }
        MauSac mauSac = null;
        String tenMauSac = (String) cbbMauSac.getSelectedItem();
        List<MauSac> mauSacs = mauSacServices.getALL();
        for (MauSac mauSac1 : mauSacs) {
            if (tenMauSac.equals(mauSac1.getTenMauSac())) {
                mauSac = mauSac1;
            }
        }
        BoNhoTrong bnt = null;
        String boNho = (String) cbbBoNhoTrong.getSelectedItem();
        List<BoNhoTrong> boNhoTrongs = boNhoTrongServices.getALL();
        for (BoNhoTrong boNhoTrong : boNhoTrongs) {
            if (Integer.parseInt(boNho) == boNhoTrong.getDungLuong()) {
                bnt = boNhoTrong;
            }
        }
        ChiTietSP c = new ChiTietSP();
        c.setMaImei(Imei);
        c.setGiaNhap(giaNhap);
        c.setGiaBan(giaBan);
        c.setMoTa(moTa);
        c.setThoiGianBH(thoiGianBaoHanh);
        c.setAnh(anh);
        c.setCamera(camera);
        c.setHeDieuHanh(heDieuHanh);
        c.setRam(ram);
        c.setCpu(cpu);
        c.setManHinh(manHinh);
        c.setDungLuongPin(pin);
        c.setXuatXu(xuatXu);
        c.setNgayTao(date);
        c.setTrangThai(0);
        c.setSanPham(sp);
        c.setMauSac(mauSac);
        c.setBoNhoTrong(bnt);
        return c;

    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        kGradientPanel3 = new keeptoo.KGradientPanel();
        jLabel3 = new javax.swing.JLabel();
        kGradientPanel4 = new keeptoo.KGradientPanel();
        jLabel4 = new javax.swing.JLabel();
        kGradientPanel12 = new keeptoo.KGradientPanel();
        jLabel2 = new javax.swing.JLabel();
        kGradientPanel14 = new keeptoo.KGradientPanel();
        jLabel5 = new javax.swing.JLabel();
        kGradientPanel15 = new keeptoo.KGradientPanel();
        jLabel6 = new javax.swing.JLabel();
        kGradientPanel16 = new keeptoo.KGradientPanel();
        jLabel7 = new javax.swing.JLabel();
        kGradientPanel17 = new keeptoo.KGradientPanel();
        jLabel8 = new javax.swing.JLabel();
        kGradientPanel18 = new keeptoo.KGradientPanel();
        jLabel9 = new javax.swing.JLabel();
        kGradientPanel19 = new keeptoo.KGradientPanel();
        jLabel10 = new javax.swing.JLabel();
        kGradientPanel20 = new keeptoo.KGradientPanel();
        btnDangXuat = new javax.swing.JLabel();
        lbChao = new javax.swing.JLabel();
        lbChucVu = new javax.swing.JLabel();
        giaodien = new javax.swing.JPanel();
        main = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        sanpham = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        cbbSanPham = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbbImei = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        txtSoLuongTon = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        cbbBoNhoTrong = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        cbbMauSac = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        cbbTGBH = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        cbbCamera = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        cbbHeDieuHanh = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        cbbRam = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtXuatXu = new javax.swing.JTextField();
        txtCpu = new javax.swing.JTextField();
        txtManHinh = new javax.swing.JTextField();
        txtPin = new javax.swing.JTextField();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        btnAdd = new javax.swing.JLabel();
        kGradientPanel5 = new keeptoo.KGradientPanel();
        btnReset = new javax.swing.JLabel();
        kGradientPanel6 = new keeptoo.KGradientPanel();
        btnDelete = new javax.swing.JLabel();
        kGradientPanel7 = new keeptoo.KGradientPanel();
        btnUpdate = new javax.swing.JLabel();
        lbAnh = new javax.swing.JLabel();
        btnUpload = new javax.swing.JLabel();
        btnSanPham = new javax.swing.JLabel();
        btnImei = new javax.swing.JLabel();
        btnBoNho = new javax.swing.JLabel();
        btnMauSac = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        kGradientPanel1.setkGradientFocus(1000);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logomini.png"))); // NOI18N

        kGradientPanel3.setkEndColor(new java.awt.Color(204, 102, 0));
        kGradientPanel3.setkGradientFocus(300);
        kGradientPanel3.setkStartColor(new java.awt.Color(204, 204, 0));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_users_30px.png"))); // NOI18N
        jLabel3.setText("Quản Lý Khách Hàng");

        javax.swing.GroupLayout kGradientPanel3Layout = new javax.swing.GroupLayout(kGradientPanel3);
        kGradientPanel3.setLayout(kGradientPanel3Layout);
        kGradientPanel3Layout.setHorizontalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addContainerGap())
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        kGradientPanel4.setkEndColor(new java.awt.Color(204, 102, 0));
        kGradientPanel4.setkGradientFocus(300);
        kGradientPanel4.setkStartColor(new java.awt.Color(204, 204, 0));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_product_30px_1.png"))); // NOI18N
        jLabel4.setText("Quản Lý Sản Phẩm");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel4Layout = new javax.swing.GroupLayout(kGradientPanel4);
        kGradientPanel4.setLayout(kGradientPanel4Layout);
        kGradientPanel4Layout.setHorizontalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        kGradientPanel4Layout.setVerticalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        kGradientPanel12.setkEndColor(new java.awt.Color(204, 102, 0));
        kGradientPanel12.setkGradientFocus(300);
        kGradientPanel12.setkStartColor(new java.awt.Color(204, 204, 0));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_add_user_group_man_man_30px.png"))); // NOI18N
        jLabel2.setText("Quản Lý Nhân Viên");

        javax.swing.GroupLayout kGradientPanel12Layout = new javax.swing.GroupLayout(kGradientPanel12);
        kGradientPanel12.setLayout(kGradientPanel12Layout);
        kGradientPanel12Layout.setHorizontalGroup(
            kGradientPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        kGradientPanel12Layout.setVerticalGroup(
            kGradientPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        kGradientPanel14.setkEndColor(new java.awt.Color(204, 102, 0));
        kGradientPanel14.setkGradientFocus(300);
        kGradientPanel14.setkStartColor(new java.awt.Color(204, 204, 0));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_click_&_collect_30px.png"))); // NOI18N
        jLabel5.setText("Quản Lý Bán Hàng");

        javax.swing.GroupLayout kGradientPanel14Layout = new javax.swing.GroupLayout(kGradientPanel14);
        kGradientPanel14.setLayout(kGradientPanel14Layout);
        kGradientPanel14Layout.setHorizontalGroup(
            kGradientPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        kGradientPanel14Layout.setVerticalGroup(
            kGradientPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        kGradientPanel15.setkEndColor(new java.awt.Color(204, 102, 0));
        kGradientPanel15.setkGradientFocus(300);
        kGradientPanel15.setkStartColor(new java.awt.Color(204, 204, 0));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_purchase_order_30px.png"))); // NOI18N
        jLabel6.setText("Quản Lý Hóa Đơn");

        javax.swing.GroupLayout kGradientPanel15Layout = new javax.swing.GroupLayout(kGradientPanel15);
        kGradientPanel15.setLayout(kGradientPanel15Layout);
        kGradientPanel15Layout.setHorizontalGroup(
            kGradientPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        kGradientPanel15Layout.setVerticalGroup(
            kGradientPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        kGradientPanel16.setkEndColor(new java.awt.Color(204, 102, 0));
        kGradientPanel16.setkGradientFocus(300);
        kGradientPanel16.setkStartColor(new java.awt.Color(204, 204, 0));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_analytics_30px.png"))); // NOI18N
        jLabel7.setText("Thống Kê Doanh Thu");

        javax.swing.GroupLayout kGradientPanel16Layout = new javax.swing.GroupLayout(kGradientPanel16);
        kGradientPanel16.setLayout(kGradientPanel16Layout);
        kGradientPanel16Layout.setHorizontalGroup(
            kGradientPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        kGradientPanel16Layout.setVerticalGroup(
            kGradientPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        kGradientPanel17.setkEndColor(new java.awt.Color(204, 102, 0));
        kGradientPanel17.setkGradientFocus(300);
        kGradientPanel17.setkStartColor(new java.awt.Color(204, 204, 0));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_gift_card_30px_1.png"))); // NOI18N
        jLabel8.setText("Quản Lý Khuyến Mãi");

        javax.swing.GroupLayout kGradientPanel17Layout = new javax.swing.GroupLayout(kGradientPanel17);
        kGradientPanel17.setLayout(kGradientPanel17Layout);
        kGradientPanel17Layout.setHorizontalGroup(
            kGradientPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        kGradientPanel17Layout.setVerticalGroup(
            kGradientPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        kGradientPanel18.setkEndColor(new java.awt.Color(204, 102, 0));
        kGradientPanel18.setkGradientFocus(300);
        kGradientPanel18.setkStartColor(new java.awt.Color(204, 204, 0));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_refresh_barcode_30px.png"))); // NOI18N
        jLabel9.setText("Quản Lý Bảo Hành");

        javax.swing.GroupLayout kGradientPanel18Layout = new javax.swing.GroupLayout(kGradientPanel18);
        kGradientPanel18.setLayout(kGradientPanel18Layout);
        kGradientPanel18Layout.setHorizontalGroup(
            kGradientPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        kGradientPanel18Layout.setVerticalGroup(
            kGradientPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        kGradientPanel19.setkEndColor(new java.awt.Color(204, 102, 0));
        kGradientPanel19.setkGradientFocus(300);
        kGradientPanel19.setkStartColor(new java.awt.Color(204, 204, 0));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_available_updates_30px.png"))); // NOI18N
        jLabel10.setText("Đổi Mật Khẩu");

        javax.swing.GroupLayout kGradientPanel19Layout = new javax.swing.GroupLayout(kGradientPanel19);
        kGradientPanel19.setLayout(kGradientPanel19Layout);
        kGradientPanel19Layout.setHorizontalGroup(
            kGradientPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        kGradientPanel19Layout.setVerticalGroup(
            kGradientPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        kGradientPanel20.setkEndColor(new java.awt.Color(204, 102, 0));
        kGradientPanel20.setkGradientFocus(300);
        kGradientPanel20.setkStartColor(new java.awt.Color(204, 204, 0));

        btnDangXuat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDangXuat.setForeground(new java.awt.Color(255, 255, 255));
        btnDangXuat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_shutdown_30px.png"))); // NOI18N
        btnDangXuat.setText("Đăng Xuất");
        btnDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDangXuatMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel20Layout = new javax.swing.GroupLayout(kGradientPanel20);
        kGradientPanel20.setLayout(kGradientPanel20Layout);
        kGradientPanel20Layout.setHorizontalGroup(
            kGradientPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        kGradientPanel20Layout.setVerticalGroup(
            kGradientPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        lbChao.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lbChao.setForeground(new java.awt.Color(255, 255, 255));
        lbChao.setText("jLabel12");

        lbChucVu.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        lbChucVu.setForeground(new java.awt.Color(255, 255, 255));
        lbChucVu.setText("jLabel12");

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbChao, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbChao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(kGradientPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kGradientPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kGradientPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kGradientPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kGradientPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kGradientPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kGradientPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kGradientPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kGradientPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kGradientPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        giaodien.setBackground(new java.awt.Color(255, 255, 255));
        giaodien.setLayout(new java.awt.CardLayout());

        main.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setText("miain");

        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(main);
        main.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(836, Short.MAX_VALUE))
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addGap(294, 294, 294)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(420, Short.MAX_VALUE))
        );

        giaodien.add(main, "main");

        sanpham.setBackground(new java.awt.Color(255, 255, 255));

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên SP", "NSX", "Màu Sắc", "Bộ Nhớ", "Ram", "CPU", "Tồn kho", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbSanPhamMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbSanPham);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Sản phẩm ");

        cbbSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Giá nhập");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Giá bán");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("List Imei");

        cbbImei.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbImei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbImeiActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Số lượng tồn");

        txtSoLuongTon.setText("0");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Ghi Chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane2.setViewportView(txtGhiChu);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("Bộ Nhớ");

        cbbBoNhoTrong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("Màu Sắc");

        cbbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMauSacActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setText("Thời gian bảo hành");

        cbbTGBH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 Tháng", "3 Tháng", "6 Tháng", "12 Tháng" }));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setText("Camera");

        cbbCamera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "32MP", "48MP", "64MP" }));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("Hệ điều hành");

        cbbHeDieuHanh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IOS", "ANDROID" }));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setText("Ram");

        cbbRam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3", "4", "6", "8", "12" }));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setText("Cpu");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setText("Màn hình");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setText("Pin");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setText("Xuất xứ");

        kGradientPanel2.setkGradientFocus(150);

        btnAdd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_add_30px_6.png"))); // NOI18N
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAddMousePressed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        kGradientPanel5.setkGradientFocus(150);

        btnReset.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_available_updates_30px.png"))); // NOI18N
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnResetMousePressed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel5Layout = new javax.swing.GroupLayout(kGradientPanel5);
        kGradientPanel5.setLayout(kGradientPanel5Layout);
        kGradientPanel5Layout.setHorizontalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        kGradientPanel5Layout.setVerticalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        kGradientPanel6.setkGradientFocus(150);

        btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_waste_30px.png"))); // NOI18N
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDeleteMousePressed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel6Layout = new javax.swing.GroupLayout(kGradientPanel6);
        kGradientPanel6.setLayout(kGradientPanel6Layout);
        kGradientPanel6Layout.setHorizontalGroup(
            kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        kGradientPanel6Layout.setVerticalGroup(
            kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        kGradientPanel7.setkGradientFocus(150);

        btnUpdate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_update_30px.png"))); // NOI18N
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnUpdateMousePressed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel7Layout = new javax.swing.GroupLayout(kGradientPanel7);
        kGradientPanel7.setLayout(kGradientPanel7Layout);
        kGradientPanel7Layout.setHorizontalGroup(
            kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        kGradientPanel7Layout.setVerticalGroup(
            kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        lbAnh.setText("Image");
        lbAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnUpload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_vintage_camera_30px.png"))); // NOI18N
        btnUpload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnUploadMousePressed(evt);
            }
        });

        btnSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_joyent_30px.png"))); // NOI18N
        btnSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSanPhamMousePressed(evt);
            }
        });

        btnImei.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_joyent_30px.png"))); // NOI18N
        btnImei.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnImeiMousePressed(evt);
            }
        });

        btnBoNho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_joyent_30px.png"))); // NOI18N
        btnBoNho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBoNhoMousePressed(evt);
            }
        });

        btnMauSac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_joyent_30px.png"))); // NOI18N

        javax.swing.GroupLayout sanphamLayout = new javax.swing.GroupLayout(sanpham);
        sanpham.setLayout(sanphamLayout);
        sanphamLayout.setHorizontalGroup(
            sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sanphamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(cbbCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(cbbHeDieuHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel24)
                    .addComponent(jLabel21)
                    .addComponent(cbbTGBH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbRam, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sanphamLayout.createSequentialGroup()
                        .addComponent(cbbBoNhoTrong, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBoNho)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(kGradientPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(kGradientPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(kGradientPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))
                    .addGroup(sanphamLayout.createSequentialGroup()
                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(sanphamLayout.createSequentialGroup()
                                .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnMauSac)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(sanphamLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sanphamLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sanphamLayout.createSequentialGroup()
                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(sanphamLayout.createSequentialGroup()
                                .addComponent(cbbImei, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnImei))))
                    .addGroup(sanphamLayout.createSequentialGroup()
                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sanphamLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(sanphamLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(14, 14, 14)))
                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sanphamLayout.createSequentialGroup()
                                .addComponent(lbAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpload, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                            .addGroup(sanphamLayout.createSequentialGroup()
                                .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtGiaNhap)
                                    .addComponent(cbbSanPham, 0, 222, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSanPham)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(sanphamLayout.createSequentialGroup()
                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(jLabel18))
                        .addGap(23, 23, 23)
                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtManHinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCpu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtXuatXu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25))
        );
        sanphamLayout.setVerticalGroup(
            sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sanphamLayout.createSequentialGroup()
                .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(sanphamLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(sanphamLayout.createSequentialGroup()
                                .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(sanphamLayout.createSequentialGroup()
                                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(sanphamLayout.createSequentialGroup()
                                                .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lbAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cbbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cbbImei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(btnImei, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCpu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtManHinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, 0)
                                .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(sanphamLayout.createSequentialGroup()
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbbCamera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(sanphamLayout.createSequentialGroup()
                                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel19)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cbbRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbbBoNhoTrong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(btnBoNho, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 55, Short.MAX_VALUE)
                        .addComponent(btnMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sanphamLayout.createSequentialGroup()
                        .addGap(650, 650, 650)
                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(sanphamLayout.createSequentialGroup()
                                .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(kGradientPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(kGradientPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(kGradientPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14))
                            .addGroup(sanphamLayout.createSequentialGroup()
                                .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(sanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbHeDieuHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbTGBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        giaodien.add(sanpham, "sanpham");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(giaodien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(giaodien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        giaodien.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangXuatMouseClicked
        // TODO add your handling code here:
        int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn đăng xuất ?");
        if (check != JOptionPane.YES_OPTION) {
            return;
        }
        JOptionPane.showMessageDialog(this, "Đăng xuất thành công !");
        new LoginForm().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDangXuatMouseClicked

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        // TODO add your handling code here:
        cardLayout.show(giaodien, "sanpham");
        hienThiSanPham();
    }//GEN-LAST:event_jLabel4MousePressed

    private void cbbImeiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbImeiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbImeiActionPerformed

    private void cbbMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMauSacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbMauSacActionPerformed

    private void btnUploadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUploadMousePressed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser("D:\\PRO1041");
        chooser.showOpenDialog(null);
//        if (JFileChooser.CANCEL_OPTION == 1) {
//            return;
//        }
        File s = chooser.getSelectedFile();
        
        String fileName = s.getAbsolutePath();
        AnhService anhService = new AnhService();
        anhService.setAnh(fileName);
        Image getAbso = null;
        ImageIcon icon = new ImageIcon(fileName);
        Image image = icon.getImage().getScaledInstance(lbAnh.getWidth(), lbAnh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon m = new ImageIcon(image);
        lbAnh.setIcon(m);

    }//GEN-LAST:event_btnUploadMousePressed

    private void btnBoNhoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBoNhoMousePressed
        // TODO add your handling code here:
        new BoNhoTrongForm().setVisible(true);
    }//GEN-LAST:event_btnBoNhoMousePressed

    private void btnAddMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMousePressed
        try {
            // TODO add your handling code here:
            String Imei = "";
            for (int i = 0; i < cbbImei.getItemCount(); i++) {
                Imei = cbbImei.getItemAt(i);
                ChiTietSP c = layTTSanPham();
                c.setMaImei(Imei);
                chiTietSPServices.add(c);
            }
            JOptionPane.showMessageDialog(this, "Thêm thành công !");
            hienThiSanPham();

//            if (chiTietSPServices.add(c) == true) {
//                JOptionPane.showMessageDialog(this, "Thêm thành công !");
//                hienThiSanPham();
//            } else {
//                JOptionPane.showMessageDialog(this, "Thêm thất bại !");
//            }
        } catch (ParseException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddMousePressed

    private void btnSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSanPhamMousePressed
        // TODO add your handling code here:
        new SanPhamForm().setVisible(true);
    }//GEN-LAST:event_btnSanPhamMousePressed

    private void btnImeiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImeiMousePressed
        // TODO add your handling code here:
        
        ImeiServices services = new ImeiServices();
        List<String> list = new ArrayList();
        String imei = "";
        for (int i = 0; i < cbbImei.getItemCount(); i++) {
            imei = cbbImei.getItemAt(i);
            list.add(imei);
            services.setList(list);
        }
        new ImeiForm().setVisible(true);
    }//GEN-LAST:event_btnImeiMousePressed

    private void btnUpdateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMousePressed
        // TODO add your handling code here:


        try {
             int index = tbSanPham.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn bản ghi nào !");
                return;
            }
           int e = tbSanPham.getSelectedRowCount();
            if (e > 1) {
                JOptionPane.showMessageDialog(this, "Bạn chỉ được chọn 1 bản ghi !");
                return;
            }
            int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa ?");
            if (check != JOptionPane.YES_OPTION) {
                return;
            }
            // TODO add your handling code here:
            String Imei = "";
            for (int i = 0; i < cbbImei.getItemCount(); i++) {
                Imei = cbbImei.getItemAt(i);
                ChiTietSP c = layTTSanPham();
//                ChiTietSP sp = chiTietSPServices.fill(c.getSanPham().getMaSP());
//                c.setAnh(sp.getAnh());
               
                c.setMaImei(Imei);
                chiTietSPServices.update(c);
            }
            JOptionPane.showMessageDialog(this, "Sửa thành công !");
            hienThiSanPham();

//            if (chiTietSPServices.add(c) == true) {
//                JOptionPane.showMessageDialog(this, "Thêm thành công !");
//                hienThiSanPham();
//            } else {
//                JOptionPane.showMessageDialog(this, "Thêm thất bại !");
//            }
        } catch (ParseException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateMousePressed

    private void btnDeleteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMousePressed
        String Imei = "";
         int index = tbSanPham.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn bản ghi nào !");
                return;
            }
           int e = tbSanPham.getSelectedRowCount();
            if (e > 1) {
                JOptionPane.showMessageDialog(this, "Bạn chỉ được chọn 1 bản ghi !");
                return;
            }
            int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa ?");
            if (check != JOptionPane.YES_OPTION) {
                return;
            }
        for (int i = 0; i < cbbImei.getItemCount(); i++) {
            try {
                
                Imei = cbbImei.getItemAt(i);
                ChiTietSP c = layTTSanPham();
                c.setMaImei(Imei);
                chiTietSPServices.delete(c);
            } catch (ParseException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        JOptionPane.showMessageDialog(this, "Xóa thành công !");
        hienThiSanPham();
    }//GEN-LAST:event_btnDeleteMousePressed

    private void tbSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMousePressed
        // TODO add your handling code here:
        fillSanPham();
    }//GEN-LAST:event_tbSanPhamMousePressed

    private void btnResetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMousePressed

                ImeiServices imeiServices = new ImeiServices();
        List<String> list = imeiServices.getList();
        cbbImei.removeAllItems();
        for (String string : list) {
            cbbImei.addItem(string);
        }
        txtSoLuongTon.setText(String.valueOf(list.size()));
    }//GEN-LAST:event_btnResetMousePressed

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jPanel1MousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAdd;
    private javax.swing.JLabel btnBoNho;
    private javax.swing.JLabel btnDangXuat;
    private javax.swing.JLabel btnDelete;
    private javax.swing.JLabel btnImei;
    private javax.swing.JLabel btnMauSac;
    private javax.swing.JLabel btnReset;
    private javax.swing.JLabel btnSanPham;
    private javax.swing.JLabel btnUpdate;
    private javax.swing.JLabel btnUpload;
    private javax.swing.JComboBox<String> cbbBoNhoTrong;
    private javax.swing.JComboBox<String> cbbCamera;
    private javax.swing.JComboBox<String> cbbHeDieuHanh;
    private javax.swing.JComboBox<String> cbbImei;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbRam;
    private javax.swing.JComboBox<String> cbbSanPham;
    private javax.swing.JComboBox<String> cbbTGBH;
    private javax.swing.JPanel giaodien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel12;
    private keeptoo.KGradientPanel kGradientPanel14;
    private keeptoo.KGradientPanel kGradientPanel15;
    private keeptoo.KGradientPanel kGradientPanel16;
    private keeptoo.KGradientPanel kGradientPanel17;
    private keeptoo.KGradientPanel kGradientPanel18;
    private keeptoo.KGradientPanel kGradientPanel19;
    private keeptoo.KGradientPanel kGradientPanel2;
    private keeptoo.KGradientPanel kGradientPanel20;
    private keeptoo.KGradientPanel kGradientPanel3;
    private keeptoo.KGradientPanel kGradientPanel4;
    private keeptoo.KGradientPanel kGradientPanel5;
    private keeptoo.KGradientPanel kGradientPanel6;
    private keeptoo.KGradientPanel kGradientPanel7;
    private javax.swing.JLabel lbAnh;
    private javax.swing.JLabel lbChao;
    private javax.swing.JLabel lbChucVu;
    private javax.swing.JPanel main;
    private javax.swing.JPanel sanpham;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTextField txtCpu;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtManHinh;
    private javax.swing.JTextField txtPin;
    private javax.swing.JLabel txtSoLuongTon;
    private javax.swing.JTextField txtXuatXu;
    // End of variables declaration//GEN-END:variables
}
