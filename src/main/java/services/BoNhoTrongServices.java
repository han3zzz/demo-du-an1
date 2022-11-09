/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domainmodels.BoNhoTrong;
import java.util.List;
import repositories.BoNhoTrongRepositories;

/**
 *
 * @author HANGOCHAN
 */
public class BoNhoTrongServices implements IBoNhoTrongServices{
    BoNhoTrongRepositories bntr ;
    public BoNhoTrongServices(){
        bntr = new BoNhoTrongRepositories();
    }

    @Override
    public List<BoNhoTrong> getALL() {
        return bntr.getAll();
    }

    @Override
    public boolean add(BoNhoTrong m) {
        return bntr.add(m);
    }

    @Override
    public boolean update(BoNhoTrong m) {
        return bntr.update(m);
    }

    @Override
    public boolean delete(BoNhoTrong m) {
        return bntr.delete(m);
    }

    @Override
    public BoNhoTrong seachbyMa(String ma) {
        return bntr.seachbyMa(ma);
    }
}
