package com.laputa.iot.common.oss.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author sommer.jiang
 * @date 2021/2/11
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@Setter
@Getter
public class ObjectInfo {
    /**
     * 对象查看路径
     */
    private String objectUrl;
    /**
     * 对象保存路径
     */
    private String objectPath;
}
