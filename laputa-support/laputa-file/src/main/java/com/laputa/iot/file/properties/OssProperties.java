package com.laputa.iot.file.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author sommer.jiang
 */
@Setter
@Getter
public class OssProperties {
    /**
     * 密钥key
     */
    private String accessKey;
    /**
     * 密钥密码
     */
    private String accessKeySecret;
    /**
     * 端点
     */
    private String endpoint;
    /**
     * bucket名称
     */
    private String bucketName;
    /**
     * 说明
     */
    private String domain;
}
