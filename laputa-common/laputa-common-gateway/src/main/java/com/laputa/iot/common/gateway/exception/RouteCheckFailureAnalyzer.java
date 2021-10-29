package com.laputa.iot.common.gateway.exception;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

/**
 * @author sommer.jiang
 * @date 2020/11/19
 * <p>
 * 格式化异常信息，方便启动时观察。
 */
public class RouteCheckFailureAnalyzer extends AbstractFailureAnalyzer<RouteCheckException> {

	@Override
	protected FailureAnalysis analyze(Throwable rootFailure, RouteCheckException cause) {

		return new FailureAnalysis(cause.getMessage(), "解决方案参考: http://t.cn/A6Gl3sDJ", cause);
	}

}
