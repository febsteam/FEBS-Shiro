package cc.mrbird.pet.service;
import cc.mrbird.common.domain.Tree;
import cc.mrbird.common.service.IService;
import cc.mrbird.pet.domain.Dona;
/**
 * @author:shenshen
 * date:2018/10/17
 */

import java.util.List;


public interface DonaService extends IService<Dona>{

    Tree<Dona> getDonaTree();

    List<Dona> findAllDonas(Dona dona);

    Dona findByUser(String donaUser);

    Dona findById(Long donaId);

    void addDona(Dona dona);

    void updateDona(Dona dona);

    void deleteDonas(String donaIds);

}
