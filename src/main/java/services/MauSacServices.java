/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodels.MauSac;
import java.util.List;
import repositories.MauSacRepositories;

/**
 *
 * @author HANGOCHAN
 */
public class MauSacServices implements IMauSacServices{
    MauSacRepositories msr;
    
    public MauSacServices(){
        msr = new MauSacRepositories();
    }

    @Override
    public List<MauSac> getALL() {
        return msr.getAll();
    }

    @Override
    public boolean add(MauSac m) {
        return msr.add(m);
    }

    @Override
    public boolean update(MauSac m) {
        return msr.update(m);
    }

    @Override
    public boolean delete(MauSac m) {
        return msr.delete(m);
    }

    @Override
    public MauSac seachbyMa(String ma) {
        return msr.seachbyMa(ma);
    }
    
}
