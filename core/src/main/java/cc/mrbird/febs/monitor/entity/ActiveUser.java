package cc.mrbird.febs.monitor.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 在线用户
 *
 * @author MrBird
 */
@Data
public class ActiveUser implements Serializable {

    private static final long serialVersionUID = -1277171780468841527L;

    /**
     * session id
     */
    private String id;
    /**
     * 用户 id
     */
    private String userId;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 用户主机地址
     */
    private String host;
    /**
     * 用户登录时系统 IP
     */
    private String systemHost;
    /**
     * 状态
     */
    private String status;
    /**
     * session 创建时间
     */
    private String startTimestamp;
    /**
     * session 最后访问时间
     */
    private String lastAccessTime;
    /**
     * 超时时间
     */
    private Long timeout;
    /**
     * 所在地
     */
    private String location;
    /**
     * 是否为当前登录用户
     */
    private boolean current;
}
