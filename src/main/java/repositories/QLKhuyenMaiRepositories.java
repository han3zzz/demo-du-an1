/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainmodels.KhuyenMai;
import domainmodels.SanPham;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.HibernateConfig;

/**
 *
 * @author Admin
 */
public class QLKhuyenMaiRepositories {
    public  List<KhuyenMai> getAll(){
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery("From KhuyenMai");
        List<KhuyenMai> list = q.getResultList();
        return list;
        
    }
    public boolean add(KhuyenMai khuyenMai){
        Transaction transaction = null;
        try(Session s = HibernateConfig.getFACTORY().openSession()) {
            transaction = s.beginTransaction();
            s.save(khuyenMai);
            transaction.commit();
            return true;
            
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }
    public boolean update(KhuyenMai khuyenMai){
        Transaction transaction = null;
        try(Session s = HibernateConfig.getFACTORY().openSession()) {
            transaction = s.beginTransaction();
            s.update(khuyenMai);
            transaction.commit();
            return true;
            
        } catch (Exception e) {
            transaction.rollback();
            return  false;
        }
    }
    public boolean  delete(KhuyenMai khuyenMai){
        Transaction transaction = null;
        try (Session s = HibernateConfig.getFACTORY().openSession()){
            khuyenMai.setTrangThai(1);
            transaction = s.beginTransaction();
            s.update(khuyenMai);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }
    public KhuyenMai seachbyMa(String  ma){
        Session s = HibernateConfig.getFACTORY().openSession();
        Query q = s.createQuery("From KhuyenMai where MaKM =: ma");
        q .setParameter("ma", ma);
        List<KhuyenMai> list = q.getResultList();
        if(list.size()==0){
            return null;
            
        }
        return list.get(0);
    }
    
}
