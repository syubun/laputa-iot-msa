package com.laputa.iot.org.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.laputa.iot.common.core.base.entity.BaseEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @program: laputa
 * @description: 岗位信息
 * @author: Sommer.jiang
 * @create: 2021-04-27 14:05
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "org_position_info")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PositionInfo extends BaseEntity<Long> {

    //编码
    private String code;
    //岗位Id
    private Long positionSeqId;
    //岗位code
    private String positionSeqCode;
    //名称
    private String name;
    //排序号
    private int orderNo;
    //状态 1启用  0未启用
    private int status;
    //直接上级编码
    private String superiorCode;
    //启动时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date startDate;
    @TableField(exist = false)
    private String superiorName;
    @TableField(exist = false)
    private String positionSeqName;
    @TableField(exist = false)
    private String companyName;
    @TableField(exist = false)
    private String deptName;

    @Builder
    public PositionInfo(Long id, LocalDateTime createTime, Long createUser, LocalDateTime updateTime, Long updateUser,
                        String code, Long positionSeqId, String positionSeqCode, String name, int orderNo, int status, String superiorCode) {
        super(id, createTime, createUser, updateTime, updateUser);
        this.code = code;
        this.positionSeqId = positionSeqId;
        this.positionSeqCode = positionSeqCode;
        this.name = name;
        this.orderNo = orderNo;
        this.status = status;
        this.superiorCode = superiorCode;

    }


}
