package cc.mrbird.febs.common.utils;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.exception.RedisConnectException;
import cc.mrbird.febs.monitor.service.IRedisService;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;

/**
 * 验证码工具类，重写 {@link com.wf.captcha.utils.CaptchaUtil}
 * 因为它没有提供修改验证码类型方法
 *
 * @author MrBird
 */
@Slf4j
public class CaptchaUtil {

    private static IRedisService redisService = SpringContextUtil.getBean(IRedisService.class);

    // gif 类型验证码
    private static final int GIF_TYPE = 1;
    // png 类型验证码
    private static final int PNG_TYPE = 0;

    // 验证码图片默认高度
    private static final int DEFAULT_HEIGHT = 48;
    // 验证码图片默认宽度
    private static final int DEFAULT_WIDTH = 130;
    // 验证码默认位数
    private static final int DEFAULT_LEN = 5;

    public static void out(HttpServletRequest request, HttpServletResponse response) throws IOException {
        out(DEFAULT_LEN, request, response);
    }

    public static void out(int len, HttpServletRequest request, HttpServletResponse response) throws IOException {
        out(DEFAULT_WIDTH, DEFAULT_HEIGHT, len, null, request, response);
    }

    public static void out(int len, Font font, HttpServletRequest request, HttpServletResponse response) throws IOException {
        out(DEFAULT_WIDTH, DEFAULT_HEIGHT, len, null, font, request, response);
    }

    public static void out(int width, int height, int len, Integer vType, HttpServletRequest request, HttpServletResponse response) throws IOException {
        out(width, height, len, vType, null, request, response);
    }

    public static void out(int width, int height, int len, Integer vType, Font font, HttpServletRequest request, HttpServletResponse response) throws IOException {
        outCaptcha(width, height, len, font, GIF_TYPE, vType, request, response);
    }

    public static void outPng(HttpServletRequest request, HttpServletResponse response) throws IOException {
        outPng(DEFAULT_LEN, request, response);
    }

    public static void outPng(int len, HttpServletRequest request, HttpServletResponse response) throws IOException {
        outPng(DEFAULT_WIDTH, DEFAULT_HEIGHT, len, null, request, response);
    }

    public static void outPng(int len, Font font, HttpServletRequest request, HttpServletResponse response) throws IOException {
        outPng(DEFAULT_WIDTH, DEFAULT_HEIGHT, len, null, font, request, response);
    }

    public static void outPng(int width, int height, int len, Integer vType, HttpServletRequest request, HttpServletResponse response) throws IOException {
        outPng(width, height, len, vType, null, request, response);
    }

    public static void outPng(int width, int height, int len, Integer vType, Font font, HttpServletRequest request, HttpServletResponse response) throws IOException {
        outCaptcha(width, height, len, font, PNG_TYPE, vType, request, response);
    }

    public static boolean verify(String code, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String key = FebsConstant.CODE_PREFIX + session.getId();
        String sessionCode = "";
        try {
            sessionCode = redisService.get(key);
        } catch (RedisConnectException e) {
            log.error("获取验证码异常", e);
        }
        return StringUtils.equalsIgnoreCase(code, sessionCode);
    }

    private static void outCaptcha(int width, int height, int len, Font font, int cType, Integer vType, HttpServletRequest request, HttpServletResponse response) throws IOException {
        setHeader(response, cType);
        Captcha captcha = null;
        if (cType == GIF_TYPE) {
            captcha = new GifCaptcha(width, height, len);
        } else {
            captcha = new SpecCaptcha(width, height, len);
        }
        if (font != null) {
            captcha.setFont(font);
        }
        if (vType != null) {
            captcha.setCharType(vType);
        }
        HttpSession session = request.getSession();
        String code = captcha.text().toLowerCase();
        String key = FebsConstant.CODE_PREFIX + session.getId();

        try {
            redisService.set(key, code, 120000L);
        } catch (RedisConnectException e) {
            log.error("保存验证码异常", e);
        }

        captcha.out(response.getOutputStream());
    }

    public static void setHeader(HttpServletResponse response, int cType) {
        if (cType == GIF_TYPE) {
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        } else {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    }
}
