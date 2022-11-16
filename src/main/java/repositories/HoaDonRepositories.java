/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainmodels.HoaDon;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.HibernateConfig;

/**
 *
 * @author HANGOCHAN
 */
public class HoaDonRepositories {
    public List<HoaDon> getALL(){
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery("From HoaDon");
        List<HoaDon> list = q.getResultList();
        return list;
    }
    public boolean add(HoaDon m){
        
        Transaction transaction = null ;
        try (Session s = HibernateConfig.getFACTORY().openSession()){
            transaction = s.beginTransaction();
            s.save(m);
            transaction.commit();
            return true;
            
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }
    public void themHD(String maHD , Date ngayMua , Date ngayTao , String maNV , String maKH ,Integer trangThai){
        Session session = HibernateConfig.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createQuery("Insert into ChiTietSP(MaHD,NgayMua,NgayTao,MaNV,MaKH,TrangThai) values (:mahd,:ngaymua,:ngaytao,:manv,:makh,:trangthai)");
        q.setParameter("mahd", maHD);
        q.setParameter("ngaymua", ngayMua);
        q.setParameter("ngaytao", ngayTao);
        q.setParameter("manv", maNV);
        q.setParameter("makh", maKH);
        q.setParameter("trangthai", trangThai);
        int index = q.executeUpdate();
        transaction.commit();
        session.close();
        
    }
    public void suaHD(String maHD , Date ngaySua ,String ghiChu , BigDecimal tongTien , BigDecimal giamGia ,Integer trangThai){
        Session session = HibernateConfig.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createQuery("UPDATE HoaDon SET GhiChu = :ghichu , TongTien = :tongtien , GiamGia = :giamgia , TrangThai = :trangThai where MaHD = :mahd");
        q.setParameter("mahd", maHD);
        q.setParameter("ghichu", ghiChu);
        q.setParameter("tongtien", tongTien);
        q.setParameter("giamgia", giamGia);
        q.setParameter("trangThai", trangThai);
        int index = q.executeUpdate();
        transaction.commit();
        session.close();
        
    }
    public boolean update(HoaDon hoaDon){
        Transaction transaction = null;
        try(Session s = HibernateConfig.getFACTORY().openSession()){
            transaction= s.beginTransaction();
            s.update(hoaDon);
            transaction.commit();
            return true;
        }catch(Exception e){
            transaction.rollback();
            return false;
        }
    }
    public boolean delete(String ma){
        Transaction transaction = null;
        try(Session s = HibernateConfig.getFACTORY().openSession()){
            HoaDon nv = s.get(HoaDon.class, ma);
            nv.setTrangThai(1);
            transaction= s.beginTransaction();
            s.update(nv);
            transaction.commit();
            return true;
        }catch(Exception e){
            transaction.rollback();
            return false;
        }
        
    }
    public HoaDon seachbyMa(String ma){
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery("From HoaDon where MaHD = :ma");
        q.setParameter("ma", ma);
        List<HoaDon> list = q.getResultList();
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
    public HoaDon fill(String ma){
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery("From HoaDon where MaHD = :masp");
        q.setParameter("masp", ma);
        List<HoaDon> list = q.getResultList();
        return list.get(0);
    }
}