/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import domainmodels.KhachHang;
import java.util.List;

/**
 *
 * @author Tungt
 */
public interface IKhachHangService {
     public List<KhachHang> getALL();
    public boolean add(KhachHang khachHang);
    public boolean update(KhachHang khachHang);
    public boolean delete(String ma);
    public KhachHang seachbyMa(String ma);
    public KhachHang fill(String maKH);
}
