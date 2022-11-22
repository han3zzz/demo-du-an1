/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import domainmodels.SanPham;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.HibernateConfig;
import viewmodels.ThongKeTheoNgay;

/**
 *
 * @author Admin
 */
public class QLSanPhamRepositories {

    public List<SanPham> getAll() {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery("From SanPham");
        List<SanPham> list = q.getResultList();
        return list;
    }

    public boolean add(SanPham sanPham) {
        Transaction transaction = null;
        try ( Session s = HibernateConfig.getFACTORY().openSession()) {
            transaction = s.beginTransaction();
            s.save(sanPham);
            transaction.commit();
            return true;

        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public boolean update(SanPham sanPham) {
        Transaction transaction = null;
        try ( Session s = HibernateConfig.getFACTORY().openSession()) {
            transaction = s.beginTransaction();
            s.update(sanPham);
            transaction.commit();
            return true;

        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public boolean delete(SanPham sanPham) {
        Transaction transaction = null;
        try ( Session s = HibernateConfig.getFACTORY().openSession()) {
            sanPham.setTrangThai(1);
            transaction = s.beginTransaction();
            s.update(sanPham);
            transaction.commit();
            return true;

        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public SanPham seachbyMa(String ma) {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery("From SanPham where MaSP = :ma");
        q.setParameter("ma", ma);
        List<SanPham> list = q.getResultList();
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public SanPham layMa() {
        Session session = HibernateConfig.getFACTORY().openSession();
        Query q = session.createQuery("From SanPham where MaSP in (Select 'SP'+ Cast(Max(Cast(SUBSTRING(MaSP,3,Len(MaSP) - 2) as int)) as string) from SanPham)");
        List<SanPham> list = q.getResultList();
        return list.get(0);
    }

//    public List<ThongKeTheoNgay> layTop5TheoNgay(Date ngay) {
//        Session session = HibernateConfig.getFACTORY().openSession();
//        Query q = session.createQuery("SELECT SanPham.MaSP , SanPham.TenSP ,count(HoaDonChiTiet.MaImei) as 'soluongban' from HoaDonChiTiet "
//                + "				join HoaDon on HoaDon.MaHD = HoaDonChiTiet.MaHD "
//                + "				join ChiTietSP on ChiTietSP.MaImei =  HoaDonChiTiet.MaImei "
//                + "				join SanPham on SanPham.MaSP = ChiTietSP.MaSP "
//                + "				where HoaDon.TrangThai = 2 and HoaDon.NgayMua = :ngaymua "
//                + "				Group by SanPham.MaSP , SanPham.TenSP "
//                + "				Order by soluongban desc   ");
//        q.setParameter("ngaymua", ngay);
//        List<ThongKeTheoNgay> list = q.getResultList();
//        return list;
//    }

}
