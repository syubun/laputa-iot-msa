package com.laputa.iot.common.core.base.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 超类基础实体
 *
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CommonEntity<T> implements Serializable, Cloneable {
    public static final String FIELD_ID = "id";
    public static final String CREATE_TIME = "createTime";
    public static final String CREATE_USER = "createUser";
    public static final String VERSION = "version";


    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = Update.class)
    protected T id;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    protected T createUser;



    @ApiModelProperty(value = "逻辑删除 //0:正常 1:删除")
    @TableLogic
    @TableField(value = "deleted")
    protected Integer deleted;

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

    /**
     * 查询关键字
     * 临时变量 用于查询
     */
    @TableField(exist = false)
    protected String keyword;

    public <T> CommonEntity(T id, LocalDateTime createTime, T createUser) {
    }



    @Override
    public Object clone() {
        //支持克隆  提高性能  仅仅是浅克隆
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return new Exception("克隆失败");
        }
    }

    /**
     * 保存和缺省验证组
     */
    public interface Save extends Default {

    }

    /**
     * 更新和缺省验证组
     */
    public interface Update extends Default {

    }
}
