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

package com.laputa.iot.msg.api.entity;



import com.baomidou.mybatisplus.annotation.TableName;

import com.laputa.iot.common.core.base.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 邮件
 *
 * @author Sommer.Jiang
 * @date 2021-10-21 09:10:00
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Data
@TableName("msg_mail")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "邮件")
public class Mail extends SuperEntity<Long> {


    private static final long serialVersionUID = 7251024524801932699L;
    /**
     * 主题
     */
    
    @Size(max = 64, message = "主题长度不能超过64") 
    @ApiModelProperty(value="主题")
    private String title;
        
    /**
     * 内容
     */
    @NotEmpty(message = "内容不能为空")
    @ApiModelProperty(value="内容")
    private String content;
        
    /**
     * 简单描述
     */
    @NotEmpty(message = "简单描述不能为空") 
    @Size(max = 255, message = "简单描述长度不能超过255") 
    @ApiModelProperty(value="简单描述")
    private String remark;
        
    /**
     * 星标邮件 0普通 1星级
     */
    @NotEmpty(message = "星标邮件 0普通 1星级不能为空")
    @ApiModelProperty(value="星标邮件 0普通 1星级")
    private Boolean isStar;
        
    /**
     * 发送者
     */
    @NotEmpty(message = "发送者不能为空") 
    @Size(max = 64, message = "发送者长度不能超过64") 
    @ApiModelProperty(value="发送者")
    private String senderName;
        
    /**
     * 收件人id
     */
    @NotEmpty(message = "收件人id不能为空") 
    
    @ApiModelProperty(value="收件人id")
    private Long receiveUser;
        
    /**
     * 收件人
     */
    @NotEmpty(message = "收件人不能为空") 
    @Size(max = 64, message = "收件人长度不能超过64") 
    @ApiModelProperty(value="收件人")
    private String receiveName;
        
    /**
     * 接收时间
     */
    @NotEmpty(message = "接收时间不能为空")
    @ApiModelProperty(value="接收时间")
    private LocalDateTime receiveDate;
        
    /**
     * 内部邮箱，默认是0 1是外部
     */
    @NotEmpty(message = "内部邮箱，默认是0 1是外部不能为空")
    @ApiModelProperty(value="内部邮箱，默认是0 1是外部")
    private Boolean isInner;
        
    /**
     * 发送者邮箱
     */
    @NotEmpty(message = "发送者邮箱不能为空") 
    @Size(max = 255, message = "发送者邮箱长度不能超过255") 
    @ApiModelProperty(value="发送者邮箱")
    private String senderMail;
        
    /**
     * 接收者邮箱
     */
    @NotEmpty(message = "接收者邮箱不能为空") 
    @Size(max = 255, message = "接收者邮箱长度不能超过255") 
    @ApiModelProperty(value="接收者邮箱")
    private String receiveMail;
        
    /**
     * 阅读时间
     */
    @NotEmpty(message = "阅读时间不能为空")
    @ApiModelProperty(value="阅读时间")
    private LocalDateTime readDate;
        
    /**
     * 状态码  0: 未读,1: 已读,2: 已回复,3: 已转发
     */
    @NotEmpty(message = "状态码  0: 未读,1: 已读,2: 已回复,3: 已转发不能为空") 
    
    @ApiModelProperty(value="状态码  0: 未读,1: 已读,2: 已回复,3: 已转发")
    private Integer status;
        
    /**
     * 附件数
     */
    @NotEmpty(message = "附件数不能为空")
    @ApiModelProperty(value="附件数")
    private Integer fileCount;
        
    /**
     * 内容大学
     */
    @NotEmpty(message = "内容大学不能为空")
    @ApiModelProperty(value="内容大学")
    private Integer contentSize;
        
    /**
     * 是否语音邮箱 0正常 1语音
     */
    @NotEmpty(message = "是否语音邮箱 0正常 1语音不能为空")
    @ApiModelProperty(value="是否语音邮箱 0正常 1语音")
    private Boolean isAudio;
        
    /**
     * 是否草稿 0草稿 1正式
     */
    @NotEmpty(message = "是否草稿 0草稿 1正式不能为空")
    @ApiModelProperty(value="是否草稿 0草稿 1正式")
    private Boolean isDraft;
                    


}
