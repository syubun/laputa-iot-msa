package com.laputa.iot.common.oss.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author sommer.jiang
 * @date 2021/2/11
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@Setter
@Getter
@ConfigurationProperties(prefix = FileServerProperties.PREFIX)
public class FileServerProperties {
    public static final String PREFIX = "laputa.file-server";
    public static final String TYPE_FDFS = "fastdfs";
    public static final String TYPE_S3 = "s3";
    public static final String TYPE_MIOIO = "minio";

    /**
     * 为以下2个值，指定不同的自动化配置
     * s3：aws s3协议的存储（七牛oss、阿里云oss、minio等）
     * fastdfs：本地部署的fastDFS
     */
    private String type;

    /**
     * aws s3配置
     */
    OssProperties oss = new OssProperties();

    /**
     * fastDFS配置
     */
    FdfsProperties fdfs = new FdfsProperties();

    private String uploadFolder;
}
