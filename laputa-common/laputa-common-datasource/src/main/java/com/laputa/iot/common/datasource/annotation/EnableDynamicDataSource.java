package com.laputa.iot.common.datasource.annotation;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.laputa.iot.common.datasource.DynamicDataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author sommer.jiang
 * @date 2019-05-18
 * <p>
 * 开启动态数据源
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableAutoConfiguration(exclude = { DruidDataSourceAutoConfigure.class })
@Import(DynamicDataSourceAutoConfiguration.class)
public @interface EnableDynamicDataSource {

}
