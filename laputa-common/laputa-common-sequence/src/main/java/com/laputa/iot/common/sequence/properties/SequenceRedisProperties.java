package com.laputa.iot.common.sequence.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author sommer.jiang
 * @date 2020/5/26
 * <p>
 * 发号器Redis配置属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "laputa.xsequence.redis")
public class SequenceRedisProperties extends BaseSequenceProperties {

}