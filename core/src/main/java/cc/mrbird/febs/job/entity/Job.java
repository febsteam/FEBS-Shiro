package cc.mrbird.febs.job.entity;

import cc.mrbird.febs.common.annotation.IsCron;
import cc.mrbird.febs.common.converter.*;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author MrBird
 */
@Data
@TableName("t_job")
@Excel("定时任务信息表")
public class Job implements Serializable {

    private static final long serialVersionUID = 400066840871805700L;

    /**
     * 任务调度参数 key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL("0"),
        /**
         * 暂停
         */
        PAUSE("1");

        private String value;

        ScheduleStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @TableId(value = "JOB_ID", type = IdType.AUTO)
    private Long jobId;

    @TableField("bean_name")
    @NotBlank(message = "{required}")
    @Size(max = 50, message = "{noMoreThan}")
    @ExcelField(value = "Bean名称")
    private String beanName;

    @TableField("method_name")
    @NotBlank(message = "{required}")
    @Size(max = 50, message = "{noMoreThan}")
    @ExcelField(value = "方法名称")
    private String methodName;

    @TableField("params")
    @Size(max = 50, message = "{noMoreThan}")
    @ExcelField(value = "方法参数")
    private String params;

    @TableField("cron_expression")
    @NotBlank(message = "{required}")
    @IsCron(message = "{invalid}")
    @ExcelField(value = "Cron表达式")
    private String cronExpression;

    @TableField("status")
    @ExcelField(value = "状态", writeConverterExp = "0=正常,1=暂停")
    private String status;

    @TableField("remark")
    @Size(max = 100, message = "{noMoreThan}")
    @ExcelField(value = "备注")
    private String remark;

    @TableField("create_time")
    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;

    private transient String createTimeFrom;
    private transient String createTimeTo;

}
