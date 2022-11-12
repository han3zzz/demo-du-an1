/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainmodels;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author HANGOCHAN
 */
@Entity
@Table (name = "SanPham")
public class SanPham implements Serializable{
    @Id
    @Column (name = "MaSP" , nullable = false)
    private String maSP ;
    @Column (name = "TenSP")
    private String tenSP ;
    @Column (name = "NgayTao")
    private Date ngayTao ;
    @Column (name = "NgaySua")
    private Date ngaySua ;
    @Column (name = "TrangThai")
    private Integer trangThai ;
    @ManyToOne
    @JoinColumn (name = "MaNSX" , nullable = false)
    private NSX nsx ;
    @ManyToOne
    @JoinColumn (name = "MaDM" , nullable = false)
    private DanhMuc danhmuc ;
    @OneToMany(mappedBy = "sanPham" , fetch = FetchType.LAZY)
    List<ChiTietSP> chiTietSPs;

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public NSX getNsx() {
        return nsx;
    }

    public void setNsx(NSX nsx) {
        this.nsx = nsx;
    }

    public DanhMuc getDanhMuc() {
        return danhmuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhmuc = danhMuc;
    }

    public List<ChiTietSP> getChiTietSPs() {
        return chiTietSPs;
    }

    public void setChiTietSPs(List<ChiTietSP> chiTietSPs) {
        this.chiTietSPs = chiTietSPs;
    }

   
    
    
    
    
    
}
