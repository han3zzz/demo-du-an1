/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author HANGOCHAN
 */
@Entity
@Table(name = "HoaDonChiTiet")
public class HoaDonChiTiet implements Serializable{
    @Id
    @ManyToOne
    @JoinColumn(name = "MaHD", nullable = false)
    private HoaDon maHD ;
    @Id
    @ManyToOne
    @JoinColumn(name = "MaImei", nullable = false)
    private ChiTietSP maImei ;
    @JoinColumn(name = "SoLuong")
    private Integer soLuong ;
    @JoinColumn (name = "DonGia")
    private BigDecimal donGia ;

    public HoaDon getMaHD() {
        return maHD;
    }

    public void setMaHD(HoaDon maHD) {
        this.maHD = maHD;
    }

    public ChiTietSP getMaImei() {
        return maImei;
    }

    public void setMaImei(ChiTietSP maImei) {
        this.maImei = maImei;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }
    
    
    
    
    
}
