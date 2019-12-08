package cc.mrbird.febs.job.mapper;


import cc.mrbird.febs.job.entity.Job;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author MrBird
 */
public interface JobMapper extends BaseMapper<Job> {
	
	List<Job> queryList();
}