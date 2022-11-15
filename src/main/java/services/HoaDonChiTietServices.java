/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodels.HoaDonChiTiet;
import java.util.List;
import repositories.HoaDonChiTietRepositories;

/**
 *
 * @author HANGOCHAN
 */
public class HoaDonChiTietServices implements IHoaDonChiTietServies{
    HoaDonChiTietRepositories hdctr ;
    
    public HoaDonChiTietServices(){
        hdctr = new HoaDonChiTietRepositories();
    }

    @Override
    public List<HoaDonChiTiet> getALL(String ma) {
        return hdctr.getALL(ma);
    }

    @Override
    public boolean add(HoaDonChiTiet m) {
        return hdctr.add(m);
    }

    @Override
    public boolean update(HoaDonChiTiet hoaDon) {
        return hdctr.update(hoaDon);
    }

    @Override
    public void delete(String imei, String mahd) {
        hdctr.delete(imei, mahd);
    }
}
