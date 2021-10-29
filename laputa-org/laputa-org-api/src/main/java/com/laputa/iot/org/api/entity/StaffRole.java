package com.laputa.iot.org.api.entity;


import com.baomidou.mybatisplus.annotation.TableName;

import com.laputa.iot.common.core.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @program: laputa
 * @description: 人员角色
 * @author: Sommer.jiang
 * @create: 2021-04-10 22:37
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "org_staff_role")
public class StaffRole extends BaseEntity<Long> {


    private static final long serialVersionUID = 5081724006967591845L;

    //人员id
    private Long staffId;
    //人员工号
    private String staffCode;
    //角色id
    private Long roleId;
    private Date endDate;
    private int validMonth;

}
