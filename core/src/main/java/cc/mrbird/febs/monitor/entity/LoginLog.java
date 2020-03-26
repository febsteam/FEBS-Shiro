package cc.mrbird.febs.monitor.entity;

import cc.mrbird.febs.common.converter.TimeConverter;
import cc.mrbird.febs.common.utils.HttpContextUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

/**
 * @author MrBird
 */
@Slf4j
@Data
@TableName("t_login_log")
@Excel("登录日志")
public class LoginLog implements Serializable {

    private static final long serialVersionUID = 921991157363932095L;
    /**
     * id
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 登录用户
     */
    @TableField("USERNAME")
    @ExcelField("登录用户")
    private String username;

    /**
     * 登录时间
     */
    @TableField("LOGIN_TIME")
    @ExcelField(value = "登录时间", writeConverter = TimeConverter.class)
    private Date loginTime;

    /**
     * 登录地点
     */
    @TableField("LOCATION")
    @ExcelField(value = "登录地点")
    private String location;
    /**
     * 登录 IP
     */
    @TableField("IP")
    @ExcelField("登录IP")
    private String ip;
    /**
     * 操作系统
     */
    @TableField("`SYSTEM`")
    @ExcelField("操作系统")
    private String system;
    /**
     * 登录浏览器
     */
    @TableField("BROWSER")
    @ExcelField("登录浏览器")
    private String browser;

    private transient String loginTimeFrom;
    private transient String loginTimeTo;

    public void setSystemBrowserInfo() {
        try {
            HttpServletRequest request = HttpContextUtil.getHttpServletRequest();

            StringBuilder userAgent = new StringBuilder("[");
            userAgent.append(request.getHeader("User-Agent"));
            userAgent.append("]");
            int indexOfMac = userAgent.indexOf("Mac OS X");
            int indexOfWindows = userAgent.indexOf("Windows NT");
            int indexOfIe = userAgent.indexOf("MSIE");
            int indexOfIe11 = userAgent.indexOf("rv:");
            int indexOfFirefox = userAgent.indexOf("Firefox");
            int indexOfSogou = userAgent.indexOf("MetaSr");
            int indexOfChrome = userAgent.indexOf("Chrome");
            int indexOfSafari = userAgent.indexOf("Safari");
            boolean isMac = indexOfMac > 0;
            boolean isWindows = indexOfWindows > 0;
            boolean isLinux = userAgent.indexOf("Linux") > 0;
            boolean containIe = indexOfIe > 0 || (isWindows && (indexOfIe11 > 0));
            boolean containFirefox = indexOfFirefox > 0;
            boolean containSogou = indexOfSogou > 0;
            boolean containChrome = indexOfChrome > 0;
            boolean containSafari = indexOfSafari > 0;
            String browser = "";
            if (containSogou) {
                if (containIe) {
                    browser = "搜狗" + userAgent.substring(indexOfIe, indexOfIe + "IE x.x".length());
                } else if (containChrome) {
                    browser = "搜狗" + userAgent.substring(indexOfChrome, indexOfChrome + "Chrome/xx".length());
                }
            } else if (containChrome) {
                browser = userAgent.substring(indexOfChrome, indexOfChrome + "Chrome/xx".length());
            } else if (containSafari) {
                int indexOfSafariVersion = userAgent.indexOf("Version");
                browser = "Safari "
                        + userAgent.substring(indexOfSafariVersion, indexOfSafariVersion + "Version/x.x.x.x".length());
            } else if (containFirefox) {
                browser = userAgent.substring(indexOfFirefox, indexOfFirefox + "Firefox/xx".length());
            } else if (containIe) {
                if (indexOfIe11 > 0) {
                    browser = "IE 11";
                } else {
                    browser = userAgent.substring(indexOfIe, indexOfIe + "IE x.x".length());
                }
            }
            String os = "";
            if (isMac) {
                os = userAgent.substring(indexOfMac, indexOfMac + "MacOS X xxxxxxxx".length());
            } else if (isLinux) {
                os = "Linux";
            } else if (isWindows) {
                os = "Windows ";
                String version = userAgent.substring(indexOfWindows + "Windows NT".length(), indexOfWindows
                        + "Windows NTx.x".length());
                switch (version.trim()) {
                    case "5.0":
                        os += "2000";
                        break;
                    case "5.1":
                        os += "XP";
                        break;
                    case "5.2":
                        os += "2003";
                        break;
                    case "6.0":
                        os += "Vista";
                        break;
                    case "6.1":
                        os += "7";
                        break;
                    case "6.2":
                        os += "8";
                        break;
                    case "6.3":
                        os += "8.1";
                        break;
                    case "10":
                        os += "10";
                    default:
                        break;
                }
            }
            this.system = os;
            this.browser = StringUtils.replace(browser, "/", " ");
        } catch (Exception e) {
            log.error("获取登录信息失败：{}", e.getMessage());
            this.system = "";
            this.browser = "";
        }

    }
}
