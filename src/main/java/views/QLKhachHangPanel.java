/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import domainmodels.KhachHang;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pagination.EventPagination;
import services.IKhachHangService;
import services.KhachHangService;

/**
 *
 * @author HANGOCHAN
 */
public class QLKhachHangPanel extends javax.swing.JPanel {

    /**
     * Creates new form QLKhachHangPanel
     */
    private IKhachHangService service;

    public QLKhachHangPanel() {
        initComponents();
        service = new KhachHangService();
//        hienThi();
        loadMaKH();
        loadData(1);
        phantrang.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                loadData(page);
            }
        });
        //loadData(2);

    }

    public void loadData(Integer page) {
        DefaultTableModel model = (DefaultTableModel) tbbBang.getModel();
        model.setRowCount(0);
        Integer limit = 10;
        List<KhachHang> khachHangs = service.getALL();
        Integer count = khachHangs.size();
//        Integer soDu = count % 10;
//        Integer soLamTron = 0;
//        if (soDu == 0) {
//            soLamTron = count / 10;
//        }
//        if (soDu != 0) {
//            soLamTron = ((count - soDu) / 10) + 1;
//        }
Integer toltalPage =(int) Math.ceil(count/ (float) limit);

       
        List<KhachHang> kh = service.phanTrang(limit, page);
        for (KhachHang khachHang : kh) {
            if (khachHang.getTrangThai() == 0) {
                Date date = khachHang.getNgaySinh();
                String sdf = new SimpleDateFormat("dd/MM/yyyy").format(date);
                Object[] row = new Object[]{
                    khachHang.getMaKH(),
                    khachHang.getTenKH(),
                    sdf,
                    khachHang.getSdt(),
                    khachHang.getDiaChi()
                };
                model.addRow(row);
            }
        }
        phantrang.setPagegination(page, toltalPage);
    }

    public void loadMaKH() {

        String ma = "";
        List<KhachHang> khachHangs = service.getALL();
        if (khachHangs.size() == 0) {
            ma = "KH0";
        } else {
            KhachHang sp = service.layMa();
//          
            ma = sp.getMaKH();
        }

        String mangString[] = ma.split("");
        String so = "";
        for (int i = 2; i < mangString.length; i++) {
            so += mangString[i];
        }

        Integer sofinal = Integer.parseInt(so) + 1;
        String maMoi = "KH" + sofinal;
        txtMa.setText(maMoi);
    }

    public void hienThi() {
        loadMaKH();
        DefaultTableModel model = (DefaultTableModel) tbbBang.getModel();
        model.setRowCount(0);
        List<KhachHang> lst = service.getALL();
        for (KhachHang khachHang : lst) {
            if (khachHang.getTrangThai() == 0) {
                Object[] row = new Object[]{
                    khachHang.getMaKH(),
                    khachHang.getTenKH(),
                    khachHang.getNgaySinh(),
                    khachHang.getSdt(),
                    khachHang.getDiaChi()
                };
                model.addRow(row);

            }
        }

    }

    public KhachHang layTTKH() throws ParseException {
        String ma = txtMa.getText();
        String ten = txtTen.getText();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        ZonedDateTime now = ZonedDateTime.now();
        String ngaySinh = dtf.format(now);
        Date date = new SimpleDateFormat("MM-dd-yyyy").parse(ngaySinh);
        String sdt = txtsdt.getText();
        String diaChi = txtQueQuan.getText();

        KhachHang kh = new KhachHang();
        kh.setMaKH(ma);
        kh.setTenKH(ten);
        kh.setNgaySinh(date);
        kh.setSdt(sdt);
        kh.setDiaChi(diaChi);
        kh.setTrangThai(0);
        return kh;
    }

    public KhachHang layTTSua() throws ParseException {
        String ma = txtMa.getText();
        String ten = txtTen.getText();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        ZonedDateTime now = ZonedDateTime.now();
        String ngaySinh = dtf.format(now);
        Date date = new SimpleDateFormat("MM-dd-yyyy").parse(ngaySinh);
        String sdt = txtsdt.getText();
        String diaChi = txtQueQuan.getText();

        KhachHang kh = new KhachHang();
        kh.setMaKH(ma);
        kh.setTenKH(ten);
        kh.setNgaySinh(date);
        kh.setSdt(sdt);
        kh.setDiaChi(diaChi);
        kh.setTrangThai(0);
        return kh;
    }

    public void clear() {
        loadData(1);
        loadMaKH();
        txtTen.setText("");
        txtNgaySinh.setDate(null);
        txtsdt.setText("");
        txtQueQuan.setText("");
    }

    public void fill() throws ParseException {
        int index = tbbBang.getSelectedRow();
        String ma = tbbBang.getValueAt(index, 0).toString();
        String ten = tbbBang.getValueAt(index, 1).toString();
        String ngaySinh = tbbBang.getValueAt(index, 2).toString();
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(ngaySinh);
        String sdt = tbbBang.getValueAt(index, 3).toString();
        String diaChi = tbbBang.getValueAt(index, 4).toString();

        txtMa.setText(ma);
        txtTen.setText(ten);
        txtNgaySinh.setDate(date);
        txtsdt.setText(sdt);
        txtQueQuan.setText(diaChi);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        khachhang = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbbBang = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtsdt = new javax.swing.JTextField();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        btnAdd = new javax.swing.JLabel();
        kGradientPanel5 = new keeptoo.KGradientPanel();
        btnReset = new javax.swing.JLabel();
        kGradientPanel6 = new keeptoo.KGradientPanel();
        btnDelete = new javax.swing.JLabel();
        kGradientPanel7 = new keeptoo.KGradientPanel();
        btnUpdate = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtQueQuan = new javax.swing.JTextArea();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        txtMa = new javax.swing.JLabel();
        phantrang = new pagination.Pagination();
        txtTim = new javax.swing.JTextField();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        btnTimTheoMa = new javax.swing.JLabel();

        khachhang.setBackground(new java.awt.Color(255, 255, 255));

        tbbBang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Ngày sinh", "Số điện thoại", "Địa chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbbBang.setRowHeight(40);
        tbbBang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbbBangMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbbBang);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Mã");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Tên");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Ngày sinh");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setText("Địa chỉ");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setText("SÐT");

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

        txtQueQuan.setColumns(20);
        txtQueQuan.setRows(5);
        jScrollPane2.setViewportView(txtQueQuan);

        txtNgaySinh.setDateFormatString("dd/MM/yyyy");

        txtMa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMa.setText("jLabel1");

        phantrang.setOpaque(false);

        btnTimTheoMa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTimTheoMa.setForeground(new java.awt.Color(255, 255, 255));
        btnTimTheoMa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnTimTheoMa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_search_30px.png"))); // NOI18N
        btnTimTheoMa.setText("Tìm theo mã");
        btnTimTheoMa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnTimTheoMaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addComponent(btnTimTheoMa, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnTimTheoMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout khachhangLayout = new javax.swing.GroupLayout(khachhang);
        khachhang.setLayout(khachhangLayout);
        khachhangLayout.setHorizontalGroup(
            khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khachhangLayout.createSequentialGroup()
                .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(khachhangLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(khachhangLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, khachhangLayout.createSequentialGroup()
                                            .addComponent(jLabel14)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, khachhangLayout.createSequentialGroup()
                                            .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel28)
                                                .addComponent(jLabel25)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(31, 31, 31)
                                            .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtTen)
                                                .addComponent(jScrollPane2)
                                                .addComponent(txtsdt)
                                                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(khachhangLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(kGradientPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(kGradientPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(kGradientPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(khachhangLayout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(khachhangLayout.createSequentialGroup()
                        .addGap(351, 351, 351)
                        .addComponent(phantrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        khachhangLayout.setVerticalGroup(
            khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, khachhangLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(khachhangLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(khachhangLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(kGradientPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(kGradientPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(kGradientPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(86, 86, 86))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, khachhangLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(phantrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );

        phantrang.getAccessibleContext().setAccessibleParent(phantrang);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(khachhang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(khachhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbbBangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbbBangMousePressed
        try {
            // TODO add your handling code here:
            fill();
        } catch (ParseException ex) {
            Logger.getLogger(QLKhachHangPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbbBangMousePressed

    private void btnAddMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMousePressed
        try {
            if (txtMa.getText().trim().isEmpty() || txtTen.getText().trim().isEmpty() || txtsdt.getText().trim().isEmpty() || txtNgaySinh.getDate() == null || txtQueQuan.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bạn không được để trống !");
                return;
            }
            KhachHang kh = layTTKH();
            if (service.add(kh) == true) {
                JOptionPane.showMessageDialog(this, "Thêm thành công !");
//                hienThi();
                loadData(1);
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại !");
            }

            //            if (chiTietSPServices.add(c) == true) {
            //                JOptionPane.showMessageDialog(this, "Thêm thành công !");
            //                hienThiSanPham();
            //            } else {
            //                JOptionPane.showMessageDialog(this, "Thêm thất bại !");
            //            }
        } catch (ParseException ex) {
            Logger.getLogger(QLNhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddMousePressed

    private void btnResetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMousePressed
        clear();
    }//GEN-LAST:event_btnResetMousePressed

    private void btnDeleteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMousePressed

        int index = tbbBang.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn bản ghi nào !");
            return;
        }
        int e = tbbBang.getSelectedRowCount();
        if (e > 1) {
            JOptionPane.showMessageDialog(this, "Bạn chỉ được chọn 1 bản ghi !");
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa ?");
        if (check != JOptionPane.YES_OPTION) {
            return;
        }

        String ma = tbbBang.getValueAt(index, 0).toString();
        if (service.delete(ma) == true) {
            JOptionPane.showMessageDialog(this, "Xóa thành công !");
//            hienThi();
            loadData(1);
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại !");
        }
    }//GEN-LAST:event_btnDeleteMousePressed

    private void btnUpdateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMousePressed
        // TODO add your handling code here:

        try {
            int index = tbbBang.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn bản ghi nào !");
                return;
            }
            int e = tbbBang.getSelectedRowCount();
            if (e > 1) {
                JOptionPane.showMessageDialog(this, "Bạn chỉ được chọn 1 bản ghi !");
                return;
            }
            if (txtMa.getText().trim().isEmpty() || txtTen.getText().trim().isEmpty() || txtsdt.getText().trim().isEmpty() || txtNgaySinh.getDate() == null || txtQueQuan.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bạn không được để trống !");
                return;
            }
            int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa ?");
            if (check != JOptionPane.YES_OPTION) {
                return;
            }
            // TODO add your handling code here:
            KhachHang kh = layTTSua();
            if (service.update(kh) == true) {
                JOptionPane.showMessageDialog(this, "Sửa thành công !");
//                hienThi();
                loadData(1);
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại !");
            }
        } catch (ParseException ex) {
            Logger.getLogger(QLNhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateMousePressed

    private void btnTimTheoMaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimTheoMaMousePressed
        // TODO add your handling code here:
        if (txtTim.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn cần điền mã để tìm");
            return;
        }
        if (txtTim.getText().length() > 10) {
            JOptionPane.showMessageDialog(this, "Mã điền phải nhỏ hơn 10");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tbbBang.getModel();
        model.setRowCount(0);
        KhachHang khachHang = service.seachbyMa(txtTim.getText());
        if (khachHang == null) {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu");
            return;
        }
        if (khachHang.getTrangThai() == 0) {
            Date date = khachHang.getNgaySinh();
            String sdf = new SimpleDateFormat("dd/MM/yyyy").format(date);
            Object[] row = new Object[]{
                khachHang.getMaKH(),
                khachHang.getTenKH(),
                sdf,
                khachHang.getSdt(),
                khachHang.getDiaChi()
            };
            model.addRow(row);
        }
        JOptionPane.showMessageDialog(this, "Tìm thành công");


    }//GEN-LAST:event_btnTimTheoMaMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAdd;
    private javax.swing.JLabel btnDelete;
    private javax.swing.JLabel btnReset;
    private javax.swing.JLabel btnTimTheoMa;
    private javax.swing.JLabel btnUpdate;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private keeptoo.KGradientPanel kGradientPanel5;
    private keeptoo.KGradientPanel kGradientPanel6;
    private keeptoo.KGradientPanel kGradientPanel7;
    private javax.swing.JPanel khachhang;
    private pagination.Pagination phantrang;
    private javax.swing.JTable tbbBang;
    private javax.swing.JLabel txtMa;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextArea txtQueQuan;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtsdt;
    // End of variables declaration//GEN-END:variables
}
