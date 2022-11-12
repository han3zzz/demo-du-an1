/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodels.NSX;
import java.util.List;
import repositories.NSXRepositories;
import repositories.QLNSXRepositories;

/**
 *
 * @author hodangquan
 */
public class QLNSXServices implements IQLNSXServices{
    QLNSXRepositories nsxr ;
    
    public QLNSXServices(){
        nsxr = new QLNSXRepositories();
    }
 
    
    @Override
    public List<NSX> getALL() {
        return nsxr.getAll();
    }

    @Override
    public boolean add(NSX nsx) {
        return nsxr.add(nsx);
    }

    @Override
    public boolean update(NSX nsx) {
        return nsxr.update(nsx);
    }

    @Override
    public boolean delete(NSX nsx) {
        return nsxr.delete(nsx);
    }

    @Override
    public NSX seachbyMa(String ma) {
        return nsxr.seachbyMa(ma);
    }
}
