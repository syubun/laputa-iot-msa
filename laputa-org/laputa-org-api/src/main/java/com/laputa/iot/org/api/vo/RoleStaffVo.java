package com.laputa.iot.org.api.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * basedata类
 * @author admin
 * @date 2018-10-22 17:40:27
 */
@Data

public class RoleStaffVo {
    /**
     *
     */
    private Long id;
    /**
     * 员工姓名
     */
    private String name;
    /**
     * 员工工号
     */
    private String code;
    /**
     * 员工ID
     */
    private Long staffId;
    /**
     * 组标识
     */
    private Long roleId;



    // 临时变量 用于查询
    /**
     * 查询条件
     */
    private String keyWord;


    /*=====非表结构字段====*/
    /**
     * 组名
     */
    private String roleName;

    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 公司ID
     */
    private Long companyId;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 部门ID集合，以“,”隔开
     */
    private String deptIds;
    /**
     * 部门名称，以“,”隔开
     */
    private String deptNames;

}
