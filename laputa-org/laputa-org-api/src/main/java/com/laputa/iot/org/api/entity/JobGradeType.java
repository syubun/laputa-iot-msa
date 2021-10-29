package com.laputa.iot.org.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.laputa.iot.common.core.base.entity.BaseEntity;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @program: laputa
 * @description: 职级分类
 * @author: Sommer.jiang
 * @create: 2021-04-27 13:57
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "org_job_grade_type")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JobGradeType extends BaseEntity<Long> {


    private static final long serialVersionUID = -1943532128168454178L;
    //编码
    private String code;
    // 名称
    private String name;
    //所属组织  如果为空是全局使用
    private Long companyId;
    //状态 1是启用  0是未启用
    private Integer status;
    //排序
    private Integer orderNo;
    //备注
    private String note;

    @Builder
    public JobGradeType(Long id, LocalDateTime createTime, Long createUser,
                        LocalDateTime updateTime, Long updateUser,
                        String code, String name, Long companyId, Integer status, Integer orderNo, String note) {
        super(id, createTime, createUser, updateTime, updateUser);
        this.code = code;
        this.name = name;
        this.companyId = companyId;
        this.status = status;
        this.orderNo = orderNo;
        this.note = note;
    }
}
