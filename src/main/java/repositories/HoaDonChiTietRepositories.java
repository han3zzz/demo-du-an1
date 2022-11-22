/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainmodels.HoaDonChiTiet;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.HibernateConfig;

/**
 *
 * @author HANGOCHAN
 */
public class HoaDonChiTietRepositories {
    public List<HoaDonChiTiet> getALL(String maHD){
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery("From HoaDonChiTiet where MaHD = :ma");
        q.setParameter("ma", maHD);
        List<HoaDonChiTiet> list = q.getResultList();
        return list;
    }
    public boolean add(HoaDonChiTiet m){
        
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
    public boolean update(HoaDonChiTiet hoaDon){
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
    public void delete(String imei ,String mahd){
        Session session = HibernateConfig.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createQuery("DELETE FROM HoaDonChiTiet where MaHD = :ma and MaImei = :imei");
        q.setParameter("ma", mahd);
        q.setParameter("imei", imei);
        int index = q.executeUpdate();
        transaction.commit();
        session.close();
        
    }
    public HoaDonChiTiet seachbyMa(String ma){
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery("From HoaDonChiTiet where MaHD = :ma");
        q.setParameter("ma", ma);
        List<HoaDonChiTiet> list = q.getResultList();
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
    public HoaDonChiTiet seachbyMaImei(String ma){
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery("From HoaDonChiTiet where MaImei = :ma");
        q.setParameter("ma", ma);
        List<HoaDonChiTiet> list = q.getResultList();
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
    public void updateImei(String ma , String maImei , String maImeiMoi){
        Session session = HibernateConfig.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createQuery("UPDATE HoaDonChiTiet SET MaImei = :maImeiMoi where MaImei = :maImei and MaHD = : maHD");
        q.setParameter("maImeiMoi", maImeiMoi);
        q.setParameter("maImei", maImei);
        q.setParameter("maHD", ma);
        int index = q.executeUpdate();
        transaction.commit();
        session.close();
    }
}
