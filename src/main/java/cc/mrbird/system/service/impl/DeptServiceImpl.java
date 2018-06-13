package cc.mrbird.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.mrbird.common.domain.Tree;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.common.util.TreeUtils;
import cc.mrbird.system.dao.DeptMapper;
import cc.mrbird.system.domain.Dept;
import cc.mrbird.system.service.DeptService;
import tk.mybatis.mapper.entity.Example;

@Service("deptService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeptServiceImpl extends BaseService<Dept> implements DeptService {

	@Autowired
	private DeptMapper deptMapper;

	@Override
	public Tree<Dept> getDeptTree() {
		List<Tree<Dept>> trees = new ArrayList<>();
		List<Dept> depts = this.findAllDepts(new Dept());
		for (Dept dept : depts) {
			Tree<Dept> tree = new Tree<>();
			tree.setId(dept.getDeptId().toString());
			tree.setParentId(dept.getParentId().toString());
			tree.setText(dept.getDeptName());
			trees.add(tree);
		}
		return TreeUtils.build(trees);
	}

	@Override
	public List<Dept> findAllDepts(Dept dept) {
		try {
			Example example = new Example(Dept.class);
			if(StringUtils.isNotBlank(dept.getDeptName())){
				example.createCriteria().andCondition("dept_name=", dept.getDeptName());
			}
			example.setOrderByClause("dept_id");
			return this.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public Dept findByName(String deptName) {
		Example example = new Example(Dept.class);
		example.createCriteria().andCondition("lower(dept_name) =", deptName.toLowerCase());
		List<Dept> list = this.selectByExample(example);
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	@Transactional
	public void addDept(Dept dept) {
		Long parentId = dept.getParentId();
		if (parentId == null)
			dept.setParentId(0L);
		dept.setDeptId(this.getSequence(Dept.SEQ));
		dept.setCreateTime(new Date());
		this.save(dept);
	}

	@Override
	@Transactional
	public void deleteDepts(String deptIds) {
		List<String> list = Arrays.asList(deptIds.split(","));
		this.batchDelete(list, "deptId", Dept.class);
		this.deptMapper.changeToTop(list);
	}

	@Override
	public Dept findById(Long deptId) {
		return this.selectByKey(deptId);
	}

	@Override
	@Transactional
	public void updateDept(Dept dept) {
		this.updateNotNull(dept);
	}

}
