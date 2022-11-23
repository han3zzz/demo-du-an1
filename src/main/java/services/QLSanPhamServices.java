/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodels.SanPham;
import java.util.Date;
import java.util.List;
import repositories.QLSanPhamRepositories;


/**
 *
 * @author Admin
 */
public class QLSanPhamServices implements IQLSanPhamServices{
    QLSanPhamRepositories spr;
    
    public QLSanPhamServices(){
        spr = new QLSanPhamRepositories();
    }

    @Override
    public List<SanPham> getALL() {
        return spr.getAll();
    }

    @Override
    public boolean add(SanPham sanPham) {
        return spr.add(sanPham);
    }

    @Override
    public boolean update(SanPham sanPham) {
        return spr.update(sanPham);
    }

    @Override
    public boolean delete(SanPham sanPham) {
        return spr.delete(sanPham);
    }

    @Override
    public SanPham seachbyMa(String ma) {
        return spr.seachbyMa(ma);
    }

    @Override
    public SanPham layMa() {
        return spr.layMa();
    }

   
}
