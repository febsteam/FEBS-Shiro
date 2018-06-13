package cc.mrbird.common.xss;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Xss攻击拦截器
 *
 */
public class XssFilter implements Filter {

	private static Logger logger = LoggerFactory.getLogger(XssFilter.class);
	// 是否过滤富文本内容
	private static boolean IS_INCLUDE_RICH_TEXT = false;

	public List<String> excludes = new ArrayList<String>();

	@Override
	public void init(FilterConfig filterConfig) {
		logger.info("------------ xss filter init ------------");
		String isIncludeRichText = filterConfig.getInitParameter("isIncludeRichText");
		if (StringUtils.isNotBlank(isIncludeRichText)) {
			IS_INCLUDE_RICH_TEXT = BooleanUtils.toBoolean(isIncludeRichText);
		}
		String temp = filterConfig.getInitParameter("excludes");
		if (temp != null) {
			String[] url = temp.split(",");
			excludes.addAll(Arrays.asList(url));
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if (handleExcludeURL(req)) {
			chain.doFilter(request, response);
			return;
		}
		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request,
				IS_INCLUDE_RICH_TEXT);
		chain.doFilter(xssRequest, response);
	}

	@Override
	public void destroy() {

	}

	private boolean handleExcludeURL(HttpServletRequest request) {
		if (excludes == null || excludes.isEmpty()) {
			return false;
		}
		String url = request.getServletPath();
		for (String pattern : excludes) {
			Pattern p = Pattern.compile("^" + pattern);
			Matcher m = p.matcher(url);
			if (m.find())
				return true;
		}
		return false;
	}

}
