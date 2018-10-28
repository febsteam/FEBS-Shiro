package cc.mrbird.pet.service;

import cc.mrbird.common.domain.Tree;
import cc.mrbird.common.service.IService;
import cc.mrbird.pet.domain.Area;
import java.util.List;

/**
 * 区域-service接口
 * @author:shenshen
 * date:2018/10/17
 */
public interface AreaService extends IService<Area> {

    Tree<Area> getAreaTree();

    List<Area> findAllAreas(Area area);

    Area findByName(String areaName);

    Area findById(Long areaId);

    void addArea(Area area);

    void updateArea(Area area);

    void deleteAreas(String areaIds);
}