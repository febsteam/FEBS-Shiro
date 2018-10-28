package cc.mrbird.pet.dao;
/**
 * @author:shenshen
 * date:2018/10/17
 */
import cc.mrbird.common.config.MyMapper;
import cc.mrbird.pet.domain.Dona;

import java.util.List;

public interface DonaMapper extends MyMapper<Dona>{
    // 删除父节点，子节点变成顶级节点（根据实际业务调整）
    void changeToTop(List<String> donaIds);
}
