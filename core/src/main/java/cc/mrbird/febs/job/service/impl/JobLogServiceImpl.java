package cc.mrbird.febs.job.service.impl;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.SortUtil;
import cc.mrbird.febs.job.entity.JobLog;
import cc.mrbird.febs.job.mapper.JobLogMapper;
import cc.mrbird.febs.job.service.IJobLogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author MrBird
 */
@Slf4j
@Service("JobLogService")
public class JobLogServiceImpl extends ServiceImpl<JobLogMapper, JobLog> implements IJobLogService {

    @Override
    public IPage<JobLog> findJobLogs(QueryRequest request, JobLog jobLog) {
        LambdaQueryWrapper<JobLog> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(jobLog.getBeanName())) {
            queryWrapper.eq(JobLog::getBeanName, jobLog.getBeanName());
        }
        if (StringUtils.isNotBlank(jobLog.getMethodName())) {
            queryWrapper.eq(JobLog::getMethodName, jobLog.getMethodName());
        }
        if (StringUtils.isNotBlank(jobLog.getStatus())) {
            queryWrapper.eq(JobLog::getStatus, jobLog.getStatus());
        }
        Page<JobLog> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "createTime", FebsConstant.ORDER_DESC, true);
        return this.page(page, queryWrapper);
    }

    @Override
    public void saveJobLog(JobLog log) {
        this.save(log);
    }

    @Override
    public void deleteJobLogs(String[] jobLogIds) {
        List<String> list = Arrays.asList(jobLogIds);
        this.baseMapper.deleteBatchIds(list);
    }

}
