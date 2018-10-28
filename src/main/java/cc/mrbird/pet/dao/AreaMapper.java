package cc.mrbird.pet.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.pet.domain.Area;
import java.util.List;

/**
 * 地区-dao
 * @author:shenshen
 * date:2018/10/17
 */
public interface AreaMapper extends MyMapper<Area> {
    // 删除父节点，子节点变成顶级节点（根据实际业务调整）
    void changeToTop(List<String> areaIds);
}
