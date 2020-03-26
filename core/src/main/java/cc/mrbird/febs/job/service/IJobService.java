package cc.mrbird.febs.job.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.job.entity.Job;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author MrBird
 */
public interface IJobService extends IService<Job> {

    /**
     * 获取定时任务
     *
     * @param jobId 任务id
     * @return 定时任务
     */
    Job findJob(Long jobId);

    /**
     * 获取定时任务分页数据
     *
     * @param request request
     * @param job     job
     * @return 定时任务分页数据
     */
    IPage<Job> findJobs(QueryRequest request, Job job);

    /**
     * 创建定时任务
     *
     * @param job job
     */
    void createJob(Job job);

    /**
     * 更新定时任务
     *
     * @param job 定时任务
     */
    void updateJob(Job job);

    /**
     * 删除定时任务
     *
     * @param jobIds 定时任务id数组
     */
    void deleteJobs(String[] jobIds);

    /**
     * 批量更新定时任务状态
     *
     * @param jobIds 定时任务id
     * @param status 待更新状态
     */
    void updateBatch(String jobIds, String status);

    /**
     * 运行定时任务
     *
     * @param jobIds 定时任务id
     */
    void run(String jobIds);

    /**
     * 暂停定时任务
     *
     * @param jobIds 定时任务id
     */
    void pause(String jobIds);

    /**
     * 恢复定时任务
     *
     * @param jobIds 定时任务id
     */
    void resume(String jobIds);

}
