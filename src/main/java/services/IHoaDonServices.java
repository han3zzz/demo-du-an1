/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodels.HoaDon;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HANGOCHAN
 */
public interface IHoaDonServices {
    public List<HoaDon> getALL();
    public boolean add(HoaDon hoaDon);
    public boolean update(HoaDon hoaDon);
    public boolean delete(String ma);
    public HoaDon seachbyMa(String ma);
    public HoaDon fill(String maHD);
    public void themHD(String maHD , Date ngayMua , Date ngayTao , String maNV , String maKH ,Integer trangThai);
    public void suaHD(String maHD , Date ngaySua ,String ghiChu , BigDecimal tongTien , BigDecimal giamGia ,Integer trangThai);
}