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
public class SuperEntity<T> implements Serializable, Cloneable {
    public static final String FIELD_ID = "id";
    public static final String CREATE_TIME = "createTime";
    public static final String CREATE_USER = "createUser";
    public static final String VERSION = "version";
    public static final String TENANT_ID = "tenantId";

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



    @ApiModelProperty(value = "租户隔离")
    @TableField(value = "tenant_id")
    protected Long tenantId;

    /**
     * 查询关键字
     * 临时变量 用于查询
     */
    @TableField(exist = false)
    protected String keyword;

    public <T> SuperEntity(T id, LocalDateTime createTime, T createUser) {
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
