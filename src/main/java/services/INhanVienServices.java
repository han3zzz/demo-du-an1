/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodels.NhanVien;
import java.util.List;

/**
 *
 * @author HANGOCHAN
 */
public interface INhanVienServices {
    public List<NhanVien> getALL();
    public boolean add(NhanVien nhanVien);
    public boolean update(NhanVien nhanVien);
    public boolean delete(NhanVien nhanVien);
    public NhanVien seachbyMa(String ma);
}
