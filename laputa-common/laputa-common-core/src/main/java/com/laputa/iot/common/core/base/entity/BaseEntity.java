package com.laputa.iot.common.core.base.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 基础实体
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@ToString(callSuper = true)
public class BaseEntity<T> extends SuperEntity<T> {

    public static final String UPDATE_TIME = "updateTime";
    public static final String UPDATE_USER = "updateUser";

    @ApiModelProperty(value = "最后修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;

    @ApiModelProperty(value = "最后修改人")
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    protected T updateUser;

    @ApiModelProperty(value = "版本号")
    @Version
    @TableField(value = "version",fill = FieldFill.INSERT_UPDATE)
    protected Integer version;


    public BaseEntity(T id, LocalDateTime createTime, T createUser, LocalDateTime updateTime, T updateUser) {
        super(id, createTime, createUser ,0,1l,null);
        this.updateTime = updateTime;
        this.updateUser = updateUser;
    }



}
