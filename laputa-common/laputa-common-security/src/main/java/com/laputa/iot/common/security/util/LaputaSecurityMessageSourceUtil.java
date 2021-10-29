package com.laputa.iot.common.security.util;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

/**
 * @author sommer.jiang
 * @date 2020/9/4
 * <p>
 * @see org.springframework.security.core.SpringSecurityMessageSource Laputa 框架自身异常处理，
 * 建议所有异常都使用此工具类型 避免无法复写 SpringSecurityMessageSource
 */
public class LaputaSecurityMessageSourceUtil extends ReloadableResourceBundleMessageSource {

	// ~ Constructors
	// ===================================================================================================

	public LaputaSecurityMessageSourceUtil() {
		setBasename("classpath:messages/messages");
		setDefaultLocale(Locale.CHINA);
	}

	// ~ Methods
	// ========================================================================================================

	public static MessageSourceAccessor getAccessor() {
		return new MessageSourceAccessor(new LaputaSecurityMessageSourceUtil());
	}

}