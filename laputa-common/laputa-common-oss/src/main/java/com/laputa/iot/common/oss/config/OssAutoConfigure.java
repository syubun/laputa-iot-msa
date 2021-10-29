package com.laputa.iot.common.oss.config;

import com.laputa.iot.common.oss.properties.FileServerProperties;
import com.laputa.iot.common.oss.template.FdfsTemplate;
import com.laputa.iot.common.oss.template.MinioTemplate;
import com.laputa.iot.common.oss.template.OssTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * @author sommer.jiang
 * @date 2021/2/13
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@EnableConfigurationProperties(FileServerProperties.class)
@Import({FdfsTemplate.class, OssTemplate.class, MinioTemplate.class})
public class OssAutoConfigure {

}
