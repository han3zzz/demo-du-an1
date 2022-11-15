/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodels.HoaDonChiTiet;
import java.util.List;

/**
 *
 * @author HANGOCHAN
 */
public interface IHoaDonChiTietServies {
    public List<HoaDonChiTiet> getALL(String ma);
    public boolean add(HoaDonChiTiet m);
    public boolean update(HoaDonChiTiet hoaDon);
    public void delete(String imei ,String mahd);
}
