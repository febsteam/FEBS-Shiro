package cc.mrbird.system.service.impl;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.system.domain.Dict;
import cc.mrbird.system.service.DictService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("dictService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DictServiceImpl extends BaseService<Dict> implements DictService {

	@Override
	public List<Dict> findAllDicts(Dict dict) {
		try {
			Example example = new Example(Dict.class);
			Criteria criteria = example.createCriteria();
			if (StringUtils.isNotBlank(dict.getKey())) {
				criteria.andCondition("key=", Long.valueOf(dict.getKey()));
			}
			if (StringUtils.isNotBlank(dict.getValue())) {
				criteria.andCondition("value=", dict.getValue());
			}
			if (StringUtils.isNotBlank(dict.getTableName())) {
				criteria.andCondition("table_name=", dict.getTableName());
			}
			if (StringUtils.isNotBlank(dict.getFieldName())) {
				criteria.andCondition("field_name=", dict.getFieldName());
			}
			example.setOrderByClause("dict_id");
			return this.selectByExample(example);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	@Transactional
	public void addDict(Dict dict) {
		dict.setDictId(this.getSequence(Dict.SEQ));
		this.save(dict);
	}

	@Override
	@Transactional
	public void deleteDicts(String dictIds) {
		List<String> list = Arrays.asList(dictIds.split(","));
		this.batchDelete(list, "dictId", Dict.class);
	}

	@Override
	@Transactional
	public void updateDict(Dict dict) {
		this.updateNotNull(dict);
	}

	@Override
	public Dict findById(Long dictId) {
		return this.selectByKey(dictId);
	}

}
