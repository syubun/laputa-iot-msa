/*
 *    Copyright (c) 2018-2025, Laputa IOT All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the www.laputa-iot.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: SommerJiang (sommer_jiang@163.com)
 */

package com.laputa.iot.msg.api.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 邮件
 *
 * @author Sommer
 * @date 2021-10-20 10:13:09
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Data

@ApiModel(value = "邮件")
public class MailVO  implements  Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value="主键ID")
    private Long id;

    /**
     * 主题
     */
    @ApiModelProperty(value="主题")
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty(value="内容")
    private String content;

    /**
     * 简单描述
     */
    @ApiModelProperty(value="简单描述")
    private String describe;

    /**
     * 星标邮件 0普通 1星级
     */
    @ApiModelProperty(value="星标邮件 0普通 1星级")
    private String isstar;

    /**
     * 发送者
     */
    @ApiModelProperty(value="发送者")
    private String senderName;

    /**
     * 收件人id
     */
    @ApiModelProperty(value="收件人id")
    private Long receiveUser;

    /**
     * 收件人
     */
    @ApiModelProperty(value="收件人")
    private String receiveName;

    /**
     * 接收时间
     */
    @ApiModelProperty(value="接收时间")
    private LocalDateTime receiveDate;

    /**
     * 阅读时间
     */
    @ApiModelProperty(value="阅读时间")
    private LocalDateTime readDate;

    /**
     * 状态码  0: 未读,1: 已读,2: 已回复,3: 已转发
     */
    @ApiModelProperty(value="状态码  0: 未读,1: 已读,2: 已回复,3: 已转发")
    private Integer status;

    /**
     * 附件数
     */
    @ApiModelProperty(value="附件数")
    private Integer fileCount;

    /**
     * 内容大学
     */
    @ApiModelProperty(value="内容大学")
    private Integer size;

    /**
     * 是否语音邮箱 0正常 1语音
     */
    @ApiModelProperty(value="是否语音邮箱 0正常 1语音")
    private Integer isaudio;

    /**
     * 是否草稿 0草稿 1正式
     */
    @ApiModelProperty(value="是否草稿 0草稿 1正式")
    private Integer isdraft;

    /**
     * 是否删除 0正常 1删除
     */
    @ApiModelProperty(value="是否删除 0正常 1删除")
    private Integer deleted;

    /**
     * 创建人id
     */
    @ApiModelProperty(value="创建人id")
    private Long createUser;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private LocalDateTime createTime;

    /**
     * 所属租户
     */
    @ApiModelProperty(value="所属租户",hidden=true)
    private Long tenantId;




}
