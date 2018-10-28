package cc.mrbird.pet.service.impl;

import cc.mrbird.common.domain.Tree;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.common.util.TreeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cc.mrbird.pet.domain.Area;
import cc.mrbird.pet.service.AreaService;
import cc.mrbird.pet.dao.AreaMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author:shenshen
 * date:2018/10/19
 */
@Service("areaService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AreaServiceImpl extends BaseService<Area> implements AreaService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AreaMapper areaMapper;
    @Override
    public Tree<Area> getAreaTree() {
        List<Tree<Area>> trees = new ArrayList<>();
        List<Area> areas = this.findAllAreas(new Area());
        areas.forEach(area -> {
            Tree<Area> tree = new Tree<>();
            tree.setId(area.getAreaId().toString());
            tree.setParentId(area.getParentId().toString());
            tree.setText(area.getAreaName());
            trees.add(tree);
        });
        return TreeUtils.build(trees);
    }

    @Override
    public List<Area> findAllAreas(Area area) {
        try {
            Example example = new Example(Area.class);
            if (StringUtils.isNotBlank(area.getAreaName())) {
                example.createCriteria().andCondition("area_name=",area.getAreaName());
            }
            example.setOrderByClause("area_id");
            return this.selectByExample(example);
        } catch (Exception e) {
            log.error("获取区域列表失败", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Area findByName(String areaName) {
        Example example = new Example(Area.class);
        example.createCriteria().andCondition("lower(area_name) =", areaName.toLowerCase());
        List<Area> list = this.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Area findById(Long areaId) {
        return this.selectByKey(areaId);
    }

    @Override
    @Transactional
    public void addArea(Area area) {
        Long parentId = area.getParentId();
        if (parentId == null)
            area.setParentId(0L);
        area.setCreateTime(new Date());
        this.save(area);
    }

    @Override
    @Transactional
    public void updateArea(Area area) {
        this.updateNotNull(area);
    }

    @Override
    @Transactional
    public void deleteAreas(String areaIds) {
        List<String> list = Arrays.asList(areaIds.split(","));
        this.batchDelete(list, "areaId", Area.class);
        this.areaMapper.changeToTop(list);
    }
}