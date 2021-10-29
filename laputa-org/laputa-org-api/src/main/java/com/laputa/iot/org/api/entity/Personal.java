package com.laputa.iot.org.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.laputa.iot.common.core.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @program: laputa
 * @description: 人员信息
 * @author: Sommer.jiang
 * @create: 2021-04-10 13:53
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "org_personal")
public class Personal extends BaseEntity<Long> {

    /**
     * 工号
     */
    private String code;
    /**
     * 姓名
     */
    private String name;
    /**
     * 上级领导
     */
    private String leaderCode;
    /**
     * 上级领导姓名
     */
    @TableField(exist = false)
    private String leaderName;
    /**
     * 头像
     */
    private String headImg;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 部门ID
     */
    private String deptId;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 公司ID
     */
    private String companyId;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 状态（1：在职，0：离职）
     */
    private Integer status = 1;
    /**
     * 离职日期
     */
    private Date leaveDate;
    /**
     * 钉钉的用户id
     */
    private String ddUserid;
    /**
     * 性别
     * 1：男；
     * 2：女；
     */
    private int sex;
    //地址
    private String address;
    //传真
    private String fax;
    //岗位编码
    private String positionCode;
    @TableField(exist = false)
    private String positionName;
    //职级编码
    private String jobGradeCode;
    @TableField(exist = false)
    private String jobGradeName;
    @TableField(exist = false)
    private String deptCode;
    @TableField(exist = false)
    private String companyCode;
    //人员拥有的角色列表
    @TableField(exist = false)
    private List<Role> roles;
    @TableField(exist = false)
    private List<String> companyIds;
    @TableField(exist = false)
    private List<String> deptIds;
}
