package cc.mrbird.febs.common.converter;

import cc.mrbird.febs.common.utils.DateUtil;
import com.wuwenze.poi.convert.WriteConverter;
import com.wuwenze.poi.exception.ExcelKitWriteConverterException;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;

/**
 * Execl导出时间类型字段格式化
 *
 * @author MrBird
 */
@Slf4j
public class TimeConverter implements WriteConverter {
    @Override
    public String convert(Object value) {
        if (value == null)
            return "";
        else {
            try {
                return DateUtil.formatCSTTime(value.toString(), DateUtil.FULL_TIME_SPLIT_PATTERN);
            } catch (ParseException e) {
                String message = "时间转换异常";
                log.error(message, e);
                throw new ExcelKitWriteConverterException(message);
            }
        }
    }
}
