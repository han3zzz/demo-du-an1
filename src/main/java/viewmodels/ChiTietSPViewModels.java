/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodels;

import domainmodels.SanPham;
import java.math.BigDecimal;

/**
 *
 * @author HANGOCHAN
 */
public class ChiTietSPViewModels {
    private String tenSP ;
    private String nsx ;
    private String mauSac ;
    private Integer boNho ;
    private Integer ram ;
    private String cpu ;
    private Integer tonKho ;
    private BigDecimal giaBan ;
    private Integer trangThai ;

    public ChiTietSPViewModels(String tenSP, String nsx, String mauSac, Integer boNho, Integer ram, String cpu, Integer tonKho, BigDecimal giaBan, Integer trangThai) {
        this.tenSP = tenSP;
        this.nsx = nsx;
        this.mauSac = mauSac;
        this.boNho = boNho;
        this.ram = ram;
        this.cpu = cpu;
        this.tonKho = tonKho;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getNsx() {
        return nsx;
    }

    public void setNsx(String nsx) {
        this.nsx = nsx;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public Integer getBoNho() {
        return boNho;
    }

    public void setBoNho(Integer boNho) {
        this.boNho = boNho;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public Integer getTonKho() {
        return tonKho;
    }

    public void setTonKho(Integer tonKho) {
        this.tonKho = tonKho;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    

    
    
    
    
}
