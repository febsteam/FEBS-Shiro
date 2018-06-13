package cc.mrbird.job.util;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

import cc.mrbird.job.domain.Job;

import java.util.Objects;

/**
 * 定时任务工具类
 * @author Administrator
 *
 */
public class ScheduleUtils {
	private final static String JOB_NAME = "TASK_";

	/**
	 * 获取触发器key
	 */
	private static TriggerKey getTriggerKey(Long jobId) {
		return TriggerKey.triggerKey(JOB_NAME + jobId);
	}

	/**
	 * 获取jobKey
	 */
	private static JobKey getJobKey(Long jobId) {
		return JobKey.jobKey(JOB_NAME + jobId);
	}

	/**
	 * 获取表达式触发器
	 */
	public static CronTrigger getCronTrigger(Scheduler scheduler, Long jobId) {
		try {
			return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 创建定时任务
	 */
	public static void createScheduleJob(Scheduler scheduler, Job scheduleJob) {
		try {
			// 构建job信息
			JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(getJobKey(scheduleJob.getJobId()))
					.build();

			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
					.withMisfireHandlingInstructionDoNothing();

			// 按新的cronExpression表达式构建一个新的trigger
			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(scheduleJob.getJobId()))
					.withSchedule(scheduleBuilder).build();

			// 放入参数，运行时的方法可以获取
			jobDetail.getJobDataMap().put(Job.JOB_PARAM_KEY, scheduleJob);

			scheduler.scheduleJob(jobDetail, trigger);

			// 暂停任务
			if (scheduleJob.getStatus().equals(Job.ScheduleStatus.PAUSE.getValue())) {
				pauseJob(scheduler, scheduleJob.getJobId());
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新定时任务
	 */
	public static void updateScheduleJob(Scheduler scheduler, Job scheduleJob) {
		try {
			TriggerKey triggerKey = getTriggerKey(scheduleJob.getJobId());

			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
					.withMisfireHandlingInstructionDoNothing();

			CronTrigger trigger = getCronTrigger(scheduler, scheduleJob.getJobId());

			// 按新的cronExpression表达式重新构建trigger
			trigger = Objects.requireNonNull(trigger).getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			// 参数
			trigger.getJobDataMap().put(Job.JOB_PARAM_KEY, scheduleJob);

			scheduler.rescheduleJob(triggerKey, trigger);

			// 暂停任务
			if (scheduleJob.getStatus().equals(Job.ScheduleStatus.PAUSE.getValue())) {
				pauseJob(scheduler, scheduleJob.getJobId());
			}

		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 立即执行任务
	 */
	public static void run(Scheduler scheduler, Job scheduleJob) {
		try {
			// 参数
			JobDataMap dataMap = new JobDataMap();
			dataMap.put(Job.JOB_PARAM_KEY, scheduleJob);

			scheduler.triggerJob(getJobKey(scheduleJob.getJobId()), dataMap);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 暂停任务
	 */
	public static void pauseJob(Scheduler scheduler, Long jobId) {
		try {
			scheduler.pauseJob(getJobKey(jobId));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 恢复任务
	 */
	public static void resumeJob(Scheduler scheduler, Long jobId) {
		try {
			scheduler.resumeJob(getJobKey(jobId));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除定时任务
	 */
	public static void deleteScheduleJob(Scheduler scheduler, Long jobId) {
		try {
			scheduler.deleteJob(getJobKey(jobId));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
