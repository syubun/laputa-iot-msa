package com.laputa.iot.org.api.entity;


import com.baomidou.mybatisplus.annotation.TableField;

import com.baomidou.mybatisplus.annotation.TableName;

import com.laputa.iot.common.core.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: laputa
 * @description: 岗位人员
 * @author: Sommer.jiang
 * @create: 2021-04-28 22:40
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "org_role_position_personal")
public class RolePositionPersonal extends BaseEntity<Long> {

    //公司id
    private String companyId;
    //角色id
    private String roleId;
    //岗位code
    private String positionCode;
    //人员Id
    private String personalId;
    @TableField(exist = false)
    private String roleNem;
    @TableField(exist = false)
    private String positionName;
    @TableField(exist = false)
    private String personalName;
}
