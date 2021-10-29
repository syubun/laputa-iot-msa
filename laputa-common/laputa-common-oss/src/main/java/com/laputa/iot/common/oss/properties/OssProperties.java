package com.laputa.iot.common.oss.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * aws s3协议配置
 *
 * @author sommer.jiang
 * pathStyleAccess 采用nginx反向代理或者AWS S3 配置成true，支持第三方云存储配置成false pathStyleAccess: false
 * access-key: laputa secret-key: laputa bucket-name: lengleng region: custom-domain:
 * https://oss.xxx.com/laputa
 * <p>
 * bucket 设置公共读权限
 */
@Setter
@Getter
public class OssProperties {
    /**
     * 用户名 Access key就像用户ID，可以唯一标识你的账户
     */
    private String accessKey;
    /**
     * Secret key是你账户的密码 密码
     */
    private String accessKeySecret;
    /**
     * 对象存储服务的URL 访问端点
     */
    private String endpoint;
    /**
     * bucket名称 默认的存储桶名称
     */
    private String bucketName = "laputa";
    /**
     * 区域
     */
    private String region;
    /**
     * path-style
     */
    private Boolean pathStyleAccessEnabled = true;



    /**
     * 自定义域名
     */
    private String customDomain;

    /**
     * true path-style nginx 反向代理和S3默认支持 pathStyle {http://endpoint/bucketname} false
     * supports virtual-hosted-style 阿里云等需要配置为 virtual-hosted-style
     * 模式{http://bucketname.endpoint}
     */
    private Boolean pathStyleAccess = true;

    /**
     * 应用ID
     */
    private String appId;



    /**
     * Secret key是你账户的密码
     */
    private String bootkey;


    /**
     * 最大线程数，默认： 100
     */
    private Integer maxConnections = 100;
}
