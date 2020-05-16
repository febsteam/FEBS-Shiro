package cc.mrbird.febs.common.aspect;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.monitor.service.ILogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

/**
 * @author MrBird
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ControllerEndpointAspect extends BaseAspectSupport {

    private final ILogService logService;

    @Pointcut("@annotation(cc.mrbird.febs.common.annotation.ControllerEndpoint)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws FebsException {
        Object result;
        Method targetMethod = resolveMethod(point);
        ControllerEndpoint annotation = targetMethod.getAnnotation(ControllerEndpoint.class);
        String operation = annotation.operation();
        long start = System.currentTimeMillis();
        try {
            result = point.proceed();
            if (StringUtils.isNotBlank(operation)) {
                RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
                ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) attributes;
                String ip = StringUtils.EMPTY;
                if (servletRequestAttributes != null) {
                    ip = servletRequestAttributes.getRequest().getRemoteAddr();
                }
                logService.saveLog(point, targetMethod, ip, operation, start);
            }
            return result;
        } catch (Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
            String exceptionMessage = annotation.exceptionMessage();
            String message = throwable.getMessage();
            String error = FebsUtil.containChinese(message) ? exceptionMessage + "，" + message : exceptionMessage;
            throw new FebsException(error);
        }
    }
}



