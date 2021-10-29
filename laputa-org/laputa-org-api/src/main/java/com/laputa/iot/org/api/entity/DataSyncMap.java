package com.laputa.iot.org.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.laputa.iot.common.core.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: laputa
 * @description: 组织同步映射关系
 * @author: Sommer.jiang
 * @create: 2021-04-16 00:25
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "org_data_sync_map")
public class DataSyncMap extends BaseEntity<Long> {


    //同步对象的名称  如：公司 部门 人员  角色
    private String name;
    //同步的接口的url  rest接口
    private String url;
    //字段对应关系
    private String jsonMap;
}
