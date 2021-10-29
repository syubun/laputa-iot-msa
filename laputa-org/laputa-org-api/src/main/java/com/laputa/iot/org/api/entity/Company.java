package com.laputa.iot.org.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.laputa.iot.common.core.base.entity.BaseEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 公司类
 * @author burce.liu
 * @date 2021-3-13 15:16:07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "org_company")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Company extends BaseEntity<Long> {

    private static final long serialVersionUID = -6685448923567560835L;
    /**
     * 上级公司id
     */
    private Long pid;
    /**
     * 公司中文名称
     */
    private String name;

    /**
     * 公司中文名称 - 简称
     */
    private String shortName;
    
    /**
     * 公司翻译名称
     */
    private String tName;
    
    /**
     * 公司code
     */
    private String code;
    /**
     * 描述
     */
    private String descr;
    //排序
    private int orderNo;
    /**
     * 状态 1启用 0禁用
     */
    private Integer status;

    @TableField(exist = false)
    private String pcode;

    @TableField(exist = false)
    private String userName;//登入名

    @TableField(exist = false)
    private List<String> roleSnList;//角色标识
    /**
     * 查询条件 - 【临时属性】
     */
    @TableField(exist = false)
    private List<String> companyIds;

@Builder
    public Company(Long id, LocalDateTime createTime, Long createUser, LocalDateTime updateTime, Long updateUser,
                   Long pid, String name, String shortName, String tName, String code, String descr, int orderNo, Integer status) {
        super(id, createTime, createUser, updateTime, updateUser);
        this.pid = pid;
        this.name = name;
        this.shortName = shortName;
        this.tName = tName;
        this.code = code;
        this.descr = descr;
        this.orderNo = orderNo;
        this.status = status;


    }


}
