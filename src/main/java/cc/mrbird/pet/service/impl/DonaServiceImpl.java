package cc.mrbird.pet.service.impl;

import cc.mrbird.common.domain.Tree;
import cc.mrbird.pet.dao.DonaMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cc.mrbird.pet.domain.Dona;
import cc.mrbird.pet.service.DonaService;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.common.util.TreeUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author:shenshen
 * date:2018/10/19
 */
@Service("donaService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DonaServiceImpl extends BaseService<Dona> implements DonaService{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DonaMapper donaMapper;

    @Override
    public Tree<Dona> getDonaTree() {
        List<Tree<Dona>> trees = new ArrayList<>();
        List<Dona> donas = this.findAllDonas(new Dona());
        donas.forEach(dona -> {
            Tree<Dona> tree = new Tree<>();
            tree.setId(dona.getDonaId().toString());
            tree.setParentId(dona.getParentId().toString());
            tree.setText(dona.getDonaUser());
            trees.add(tree);
        });
        return TreeUtils.build(trees);
    }

    @Override
    public List<Dona> findAllDonas(Dona dona) {
        try {
            Example example = new Example(Dona.class);
            if (StringUtils.isNotBlank(dona.getDonaUser())) {
                example.createCriteria().andCondition("dona_user=",dona.getDonaUser());
            }
            example.setOrderByClause("dona_id");
            return this.selectByExample(example);
        } catch (Exception e) {
            log.error("获取捐赠物品列表失败", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Dona findByUser(String donaUser) {
        Example example = new Example(Dona.class);
        example.createCriteria().andCondition("lower(dona_user) =", donaUser.toLowerCase());
        List<Dona> list = this.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Dona findById(Long donaId) {
        return this.selectByKey(donaId);
    }

    @Override
    @Transactional
    public void addDona(Dona dona) {
        Long parentId = dona.getParentId();
        if (parentId == null)
            dona.setParentId(0L);
        dona.setCreateTime(new Date());
        this.save(dona);
    }

    @Override
    @Transactional
    public void updateDona(Dona dona) {
        this.updateNotNull(dona);
    }

    @Override
    public void deleteDonas(String donaIds) {
        List<String> list = Arrays.asList(donaIds.split(","));
        this.batchDelete(list, "donaId", Dona.class);
        this.donaMapper.changeToTop(list);
    }
}
