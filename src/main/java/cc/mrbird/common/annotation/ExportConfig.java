package cc.mrbird.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.poi.hssf.util.HSSFColor;

/**
 * Excel导出项配置
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ExportConfig {

	/**
	 * @return 表头显示名(如：id字段显示为"编号") 默认为字段名
	 */
	String value() default "field";

	/**
	 * @return 单元格宽度 默认-1(自动计算列宽)
	 */
	short width() default -1;

	/**
	 * 将单元格值进行转换后再导出：<br/>
	 * 目前支持以下几种场景：<br/>
	 * 1. 固定的数值转换为字符串值（如：1代表男，2代表女）<br/>
	 * <b>表达式:</b> "s:1=男,2=女"<br/>
	 * 
	 * 2. 数值对应的值需要查询数据库才能进行映射(实现cc.mrbird.util.poi.convert.ExportConvert接口)<br/>
	 * 
	 * @return 默认不启用
	 */
	String convert() default "";

	/**
	 * @return 当前单元格的字体颜色 (默认 HSSFColor.BLACK.index)
	 */
	HSSFColor.HSSFColorPredefined color() default HSSFColor.HSSFColorPredefined.BLACK;

	/**
	 * 将单元格的值替换为当前配置的值：<br/>
	 * 应用场景: <br/>
	 * 密码字段导出为："******"
	 * 
	 * @return 默认true
	 */
	String replace() default "";
}
