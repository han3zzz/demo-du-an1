/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodels.ChiTietSP;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import repositories.ChiTietSPRepositories;
import viewmodels.ChiTietSPViewModels;

/**
 *
 * @author HANGOCHAN
 */
public class ChiTietSPServices implements IChiTietSPServices{
    ChiTietSPRepositories ctspr;
    
    public ChiTietSPServices(){
        ctspr = new ChiTietSPRepositories();
    }

    @Override
    public List<ChiTietSPViewModels> getALL() {
        List<ChiTietSP> list = ctspr.getAll();
        List<ChiTietSPViewModels> ctspvms = new ArrayList();
        for (ChiTietSP ctsp : list) {
            List<ChiTietSP> count = ctspr.count(ctsp.getSanPham().getMaSP());
            String tenSP = ctsp.getSanPham().getTenSP();
            String nsx = ctsp.getSanPham().getNsx().getTenNSX();
            String mauSac = ctsp.getSanPham().getMausac().getTenMauSac();
            Integer boNho = ctsp.getSanPham().getBonhotrong().getDungLuong();
            Integer tonKho = count.size();
            BigDecimal giaBan = ctsp.getGiaBan();
            BigDecimal giaNhap = ctsp.getGiaNhap();
            String anh = ctsp.getAnh();
            Integer trangThai = ctsp.getTrangThai();
            ChiTietSPViewModels ctspvm = new ChiTietSPViewModels(tenSP, nsx, mauSac, boNho, tonKho,giaNhap, giaBan,anh,trangThai);
            ctspvms.add(ctspvm);
        }
        
        return ctspvms;
    }

    @Override
    public boolean add(ChiTietSP m) {
        return ctspr.add(m);
    }

    @Override
    public boolean update(ChiTietSP m) {
        return ctspr.update(m);
    }

    @Override
    public boolean delete(ChiTietSP m) {
        return ctspr.delete(m);
    }

    @Override
    public ChiTietSP seachbyMa(String ma) {
        return ctspr.seachbyMa(ma);
    }

    @Override
    public ChiTietSP fill(String maSP) {
        return ctspr.fill(maSP);
    }

    @Override
    public List<ChiTietSP> getImeibyMaSP(String maSP) {
        return ctspr.getImeibyMaSP(maSP);
    }

    @Override
    public ChiTietSPViewModels load(String maSP) {
        ChiTietSP ctsp = ctspr.load(maSP);
        if (ctsp == null) {
            return null;
        }
        List<ChiTietSP> count = ctspr.count(ctsp.getSanPham().getMaSP());
        
        String tenSP = ctsp.getSanPham().getTenSP();
            String nsx = ctsp.getSanPham().getNsx().getTenNSX();
            String mauSac = ctsp.getSanPham().getMausac().getTenMauSac();
            Integer boNho = ctsp.getSanPham().getBonhotrong().getDungLuong();
            Integer tonKho = count.size();
            BigDecimal giaBan = ctsp.getGiaBan();
             BigDecimal giaNhap = ctsp.getGiaNhap();
            String anh = ctsp.getAnh();
            Integer trangThai = ctsp.getTrangThai();
            ChiTietSPViewModels ctspvm = new ChiTietSPViewModels(tenSP, nsx, mauSac, boNho, tonKho,giaNhap, giaBan,anh,trangThai);
        return ctspvm;
    }

    @Override
    public void updateImei(String ma) {
        ctspr.updateImei(ma);
    }

    @Override
    public void xoaImei(String ma) {
        ctspr.xoaImei(ma);
    }

    @Override
    public List<ChiTietSP> getImei() {
        return ctspr.getAll();
    }

}
