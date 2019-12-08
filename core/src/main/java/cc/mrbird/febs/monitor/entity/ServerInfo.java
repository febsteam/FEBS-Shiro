package cc.mrbird.febs.monitor.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author MrBird
 */
@Data
public class ServerInfo implements Serializable {

    private static final long serialVersionUID = 5915203206170057447L;
    /**
     * 应用已运行时长
     */
    private Double processUptime;
    /**
     * 应用 CPU占用率
     */
    private Double processCpuUsage;
    /**
     * 应用启动时间点
     */
    private String processStartTime;
    /**
     * 系统 CPU核心数
     */
    private Double systemCpuCount;
    /**
     * 系统 CPU 使用率
     */
    private Double systemCpuUsage;
    /**
     * 当前活跃 JDBC连接数
     */
    private Double jdbcConnectionsActive;
    /**
     * JDBC最小连接数
     */
    private Double jdbcConnectionsMin;
    /**
     * JDBC最大连接数
     */
    private Double jdbcConnectionsMax;
}
