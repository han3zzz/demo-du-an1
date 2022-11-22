/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import repositories.HoaDonRepositories;
import viewmodels.ThongKeTheoNgay;

/**
 *
 * @author HANGOCHAN
 */
public class HoaDonServices implements IHoaDonServices{
    HoaDonRepositories hdr ;
    
    public HoaDonServices(){
        hdr = new HoaDonRepositories();
    }

    @Override
    public List<HoaDon> getALL() {
        return  hdr.getALL();
    }

    @Override
    public boolean add(HoaDon hoaDon) {
        return hdr.add(hoaDon);
    }

    @Override
    public boolean update(HoaDon hoaDon) {
        return  hdr.update(hoaDon);
    }

    @Override
    public boolean delete(String ma) {
        return hdr.delete(ma);
    }

    @Override
    public HoaDon seachbyMa(String ma) {
        return hdr.seachbyMa(ma);
    }

    @Override
    public HoaDon fill(String maHD) {
        return hdr.fill(maHD);
    }

//    @Override
//    public void themHD(String maHD, Date ngayMua, Date ngayTao, String maNV, String maKH, Integer trangThai) {
//         hdr.themHD(maHD, ngayMua, ngayTao, maNV, maKH, trangThai);
//    }

    @Override
    public void suaHD(String maHD, String ghiChu, BigDecimal tongTien, BigDecimal giamGia, Integer trangThai ,String makm) {
        hdr.suaHD(maHD, ghiChu, tongTien, giamGia, trangThai , makm);
    }

    @Override
    public void updateNgaySua(Date ngaySua, String maHD) {
        hdr.updateNgaySua(ngaySua, maHD);
    }

    @Override
    public void updateNgayTT(Date ngayTT, String maHD) {
        hdr.updateNgayTT(ngayTT, maHD);
    }

    @Override
    public HoaDon layMa() {
        return hdr.layMa();
    }

    @Override
    public List<HoaDon> countHoaDontheoNgay(Integer trangThai, Date ngay) {
        return hdr.countHoaDontheoNgay(trangThai, ngay);
    }

    @Override
    public List<HoaDon> countHoaDon(Integer trangThai) {
        return  hdr.countHoaDon(trangThai);
    }

    
    
    

    
}
