package com.laputa.iot.msg.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @Author Sommer Jiang
 * @since 2021-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class ChatUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("user_id")
    private String userId;

    @TableField("user_name")
    private String userName;


    @TableField("gender")
    private Integer gender;

    @TableField("phone_number")
    private String phoneNumber;

    @TableField("email")
    private String email;

    @TableField("avatar")
    private String avatar;


    @TableField("status")
    private Integer status;

    @TableField("gmt_birthday")
    private Date gmtBirthday;

    @TableField("nick_name")
    private String name;


    @TableField("balance")
    private BigDecimal balance;


}
