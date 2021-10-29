package com.laputa.iot.common.datasource.config;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sommer.jiang
 * @date 2020/12/11
 * <p>
 * 清空上文的DS 设置避免污染当前线程
 */
public class ClearTtlDsInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		DynamicDataSourceContextHolder.clear();
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		DynamicDataSourceContextHolder.clear();
	}

}
