/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainmodels.KhachHang;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.HibernateConfig;

/**
 *
 * @author Tungt
 */
public class KhachHangRepositores {
    public List<KhachHang> getALL(){
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery("From KhachHang");
        List<KhachHang> list = q.getResultList();
        return list;
    }
    public boolean add(KhachHang khachHang){
        Transaction transaction = null;
        try(Session s = HibernateConfig.getFACTORY().openSession()){
            transaction= s.beginTransaction();
            s.save(khachHang);
            transaction.commit();
            return true;
        }catch(Exception e){
            transaction.rollback();
            return false;
        }
    }
    public boolean update(KhachHang khachHang){
        Transaction transaction = null;
        try(Session s = HibernateConfig.getFACTORY().openSession()){
            transaction= s.beginTransaction();
            s.update(khachHang);
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
           KhachHang kh = s.get(KhachHang.class, ma);
            kh.setTrangThai(1);
            transaction= s.beginTransaction();
            s.update(kh);
            transaction.commit();
            return true;
        }catch(Exception e){
            transaction.rollback();
            return false;
        }
        
    }
    public KhachHang seachbyMa(String ma){
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery("From KhachHang where MaKH = :ma");
        q.setParameter("ma", ma);
        List<KhachHang> list = q.getResultList();
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
    public KhachHang fill(String maKH){
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery("From KhachHang where MaKH = :masp");
        q.setParameter("masp", maKH);
        List<KhachHang> list = q.getResultList();
        return list.get(0);
    }
}
