/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import domainmodels.DanhMuc;
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
import services.DanhMucServices;
import services.IDanhMucServices;

/**
 *
 * @author adm
 */
public class QLDanhMuc extends javax.swing.JFrame {

    /**
     * Creates new form QLDanhMuc
     */
    private IDanhMucServices services;

    public QLDanhMuc() {
        initComponents();

        services = new DanhMucServices();
        load();
        loadMaDM();

    }

    public void loadMaDM() {

        String ma = "";
        List<DanhMuc> danhMucs = services.getALL();
        if (danhMucs.size() == 0) {
            ma = "DM0";
        } else {
            DanhMuc sp = services.layMa();
//          
            ma = sp.getMaDanhMuc();
        }

        String mangString[] = ma.split("");
        String so = "";
        for (int i = 2; i < mangString.length; i++) {
            so += mangString[i];
        }

        Integer sofinal = Integer.parseInt(so) + 1;
        String maMoi = "DM" + sofinal;
        txtMa.setText(maMoi);
    }

    public void loadCbbDanhMuc() {
        List<DanhMuc> items = services.getALL();
        QLSanPham.loadCbbDanhMuc(items);
    }

    public void load() {
        loadMaDM();
        DefaultTableModel model = (DefaultTableModel) tbDanhMuc.getModel();
        model.setRowCount(0);
        List<DanhMuc> list = services.getALL();
        for (DanhMuc d : list) {
            if (d.getTrangThai() == 0) {
                Object[] data = new Object[]{
                    d.getMaDanhMuc(),
                    d.getTenDanhMuc(),};
                model.addRow(data);
            }
        }
    }

//    public void reset() {
//        SanPhamForm sanPhamForm = new SanPhamForm();
//        sanPhamForm.loadCbbNSX();
//    }
//    public void loadCBBNSX(){
//         List<DanhMuc> items = services.getALL();
//        SanPhamForm.loadCbbNSX(items);
//    }
    public DanhMuc layTT() throws ParseException {
        String ma = txtMa.getText();
        String ten = txtTen.getText();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        ZonedDateTime now = ZonedDateTime.now();
        String ngayTao = dtf.format(now);
        Date date = new SimpleDateFormat("MM-dd-yyyy").parse(ngayTao);
        DanhMuc n = new DanhMuc();
        n.setMaDanhMuc(ma);
        n.setTenDanhMuc(ten);
        n.setNgayTao(date);
        n.setTrangThai(0);
        return n;
    }

    public DanhMuc layTTSua() throws ParseException {
        String ma = txtMa.getText();
        String ten = txtTen.getText();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        ZonedDateTime now = ZonedDateTime.now();
        String ngayTao = dtf.format(now);
        Date date = new SimpleDateFormat("MM-dd-yyyy").parse(ngayTao);
        DanhMuc n = new DanhMuc();
        n.setMaDanhMuc(ma);
        n.setTenDanhMuc(ten);
        n.setNgaySua(date);
        n.setTrangThai(0);
        return n;
    }

    public void fill() {
        int index = tbDanhMuc.getSelectedRow();
        String ma = tbDanhMuc.getValueAt(index, 0).toString();
        String ten = tbDanhMuc.getValueAt(index, 1).toString();

        txtMa.setText(ma);
        txtTen.setText(ten);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDanhMuc = new javax.swing.JTable();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        btnUpdate = new javax.swing.JLabel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        btnAdd = new javax.swing.JLabel();
        kGradientPanel3 = new keeptoo.KGradientPanel();
        btnDelete = new javax.swing.JLabel();
        kGradientPanel4 = new keeptoo.KGradientPanel();
        btnSearch = new javax.swing.JLabel();
        txtMa = new javax.swing.JLabel();
        kGradientPanel5 = new keeptoo.KGradientPanel();
        btnClear = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 102, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Danh Mục");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mã");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tên");

        tbDanhMuc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã", "Tên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDanhMucMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbDanhMucMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbDanhMuc);

        btnUpdate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_update_30px.png"))); // NOI18N
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnUpdateMousePressed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        btnAdd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_add_30px_6.png"))); // NOI18N
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAddMousePressed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_waste_30px.png"))); // NOI18N
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDeleteMousePressed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel3Layout = new javax.swing.GroupLayout(kGradientPanel3);
        kGradientPanel3.setLayout(kGradientPanel3Layout);
        kGradientPanel3Layout.setHorizontalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        btnSearch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_search_30px.png"))); // NOI18N
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSearchMousePressed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel4Layout = new javax.swing.GroupLayout(kGradientPanel4);
        kGradientPanel4.setLayout(kGradientPanel4Layout);
        kGradientPanel4Layout.setHorizontalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );
        kGradientPanel4Layout.setVerticalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        txtMa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMa.setText("jLabel4");

        btnClear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_available_updates_30px.png"))); // NOI18N
        btnClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnClearMousePressed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel5Layout = new javax.swing.GroupLayout(kGradientPanel5);
        kGradientPanel5.setLayout(kGradientPanel5Layout);
        kGradientPanel5Layout.setHorizontalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
        );
        kGradientPanel5Layout.setVerticalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kGradientPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kGradientPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kGradientPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTen)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtMa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbDanhMucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDanhMucMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDanhMucMouseClicked

    private void tbDanhMucMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDanhMucMousePressed
        // TODO add your handling code here:
        fill();
    }//GEN-LAST:event_tbDanhMucMousePressed

    private void btnUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseClicked

    }//GEN-LAST:event_btnUpdateMouseClicked

    private void btnUpdateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMousePressed
        String checkSo = "^[+?0-9]*$";
        try {

            // TODO add your handling code here:
            int index = tbDanhMuc.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn bản ghi nào !");
                return;
            }
            int i = tbDanhMuc.getSelectedRowCount();
            if (i > 1) {
                JOptionPane.showMessageDialog(this, "Bạn chỉ được chọn 1 bản ghi !");
                return;
            }
//            if (txtTen.getText().length() >= 30) {
//                JOptionPane.showMessageDialog(this, "Độ dài tên vượt quá độ dài cho phép!", "Thông báo", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
            if (txtMa.getText().length() > 10) {
                JOptionPane.showMessageDialog(this, "Mã nhỏ hơn hoặc bằng 10 kí tự");
                return;
            }
            if (txtTen.getText().length() > 50) {
                JOptionPane.showMessageDialog(this, "Tên nhỏ hơn hoặc bằng 50 kí tự");
                return;
            }
            DanhMuc n = layTTSua();
            if (n.getMaDanhMuc().trim().isEmpty() || n.getTenDanhMuc().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống !");
                return;
            }
            int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn sửa không ?");
            if (check != JOptionPane.YES_OPTION) {
                return;
            }
            if (services.update(n) == true) {
                JOptionPane.showMessageDialog(this, "Sửa thành công !");
                load();
                loadCbbDanhMuc();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại !");
            }
        } catch (ParseException ex) {
            Logger.getLogger(QLDanhMuc.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnUpdateMousePressed

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked

    }//GEN-LAST:event_btnAddMouseClicked

    private void btnAddMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMousePressed

        try {
            // TODO add your handling code here:
            String ma = txtMa.getText().trim();
            String ten = txtTen.getText().trim();
            String chu = "^[a-zA-Z\\s]*$";
            if (ma.isEmpty() || ten.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống !");
                return;
            }
//            if (ten.replaceAll("[^ ]", "").length() > 0) {
//                JOptionPane.showMessageDialog(this, "Tên viết liền và không chứa khoảng trắng.Vui lòng xóa các khoảng trắng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
            if (ten.length() >= 30) {
                JOptionPane.showMessageDialog(this, "Độ dài tên vượt quá độ dài cho phép!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (ten.contains(",")) {
                JOptionPane.showMessageDialog(this, "Tên không được chứ dấu','", "Thông Báo", JOptionPane.ERROR_MESSAGE);
                return;
            }
//            if (!ten.matches(chu)) {
//                JOptionPane.showMessageDialog(this, "Tên phải là chữ!", "Thông báo", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
            DanhMuc n = layTT();
            if (services.seachbyMa(n.getMaDanhMuc()) != null) {
                JOptionPane.showMessageDialog(this, "Mã đã tồn tại !");
                return;
            }
            if (services.add(n) == true) {
                JOptionPane.showMessageDialog(this, "Thêm thành công !");
                load();
                loadCbbDanhMuc();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        } catch (ParseException ex) {
            Logger.getLogger(QLDanhMuc.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnAddMousePressed

    private void btnDeleteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMousePressed
        try {
            // TODO add your handling code here:
            int index = tbDanhMuc.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn bản ghi nào !");
                return;
            }
            int i = tbDanhMuc.getSelectedRowCount();
            if (i > 1) {
                JOptionPane.showMessageDialog(this, "Bạn chỉ được chọn 1 bản ghi !");
                return;
            }
            int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa ?");
            if (check != JOptionPane.YES_OPTION) {
                return;
            }
//            if (txtTen.getText().length() >= 30) {
//                JOptionPane.showMessageDialog(this, "Độ dài tên vượt quá độ dài cho phép!", "Thông báo", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
            DanhMuc n = layTT();
            if (services.delete(n) == true) {
                JOptionPane.showMessageDialog(this, "Xóa thành công !");
                load();
                loadCbbDanhMuc();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại !");
            }
        } catch (ParseException ex) {
            Logger.getLogger(QLDanhMuc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteMousePressed

    private void btnSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMousePressed
        // TODO add your handling code here:
        String ma = txtMa.getText();

        if (ma.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập mã trước khi tìm kiếm !");
            load();
            return;
        }
        DanhMuc n = services.seachbyMa(ma);
        if (n == null) {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu !");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tbDanhMuc.getModel();
        model.setRowCount(0);
        if (n.getTrangThai() == 0) {
            JOptionPane.showMessageDialog(this, "Tìm thành công !");
            Object[] data = new Object[]{
                n.getMaDanhMuc(),
                n.getTenDanhMuc()
            };
            model.addRow(data);
        } else {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu !");

        }
    }//GEN-LAST:event_btnSearchMousePressed

    private void btnClearMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMousePressed
        // TODO add your handling code here:
        load();
        txtTen.setText("");
    }//GEN-LAST:event_btnClearMousePressed

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
            java.util.logging.Logger.getLogger(QLDanhMuc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLDanhMuc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLDanhMuc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLDanhMuc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLDanhMuc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAdd;
    private javax.swing.JLabel btnClear;
    private javax.swing.JLabel btnDelete;
    private javax.swing.JLabel btnSearch;
    private javax.swing.JLabel btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private keeptoo.KGradientPanel kGradientPanel3;
    private keeptoo.KGradientPanel kGradientPanel4;
    private keeptoo.KGradientPanel kGradientPanel5;
    private javax.swing.JTable tbDanhMuc;
    private javax.swing.JLabel txtMa;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
