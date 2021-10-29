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

package com.laputa.iot.upms.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.conditions.update.Update;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 租户
 *
 * @author sommer.jiang
 * @date 2019-05-15 15:55:41
 */
@Data
@ApiModel(value = "租户信息")

public class SysTenant extends Model<SysTenant>{

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.INPUT)
	@ApiModelProperty(value = "主键")
	@NotNull(message = "id不能为空", groups = Update.class)
	private Long id;

	@ApiModelProperty(value = "创建时间")
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	@ApiModelProperty(value = "创建人")
	@TableField(value = "create_user", fill = FieldFill.INSERT)
	private String createUser;



	@ApiModelProperty(value = "逻辑删除 //0:正常 1:删除")
	@TableLogic
	@TableField(value = "deleted")
	private Integer deleted;

	public static final String UPDATE_TIME = "updateTime";
	public static final String UPDATE_USER = "updateUser";

	@ApiModelProperty(value = "最后修改时间")
	@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	@ApiModelProperty(value = "最后修改人")
	@TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
	private String updateUser;



	/**
	 * 租户名称
	 */
	@ApiModelProperty(value = "租户名称")
	private String name;

	/**
	 * 租户编号
	 */
	@ApiModelProperty(value = "租户编号")
	private String code;

	/**
	 * 开始时间
	 */
	@ApiModelProperty(value = "开始时间")
	private LocalDateTime startTime;

	/**
	 * 结束时间
	 */
	@ApiModelProperty(value = "结束时间")
	private LocalDateTime endTime;

	/**
	 * 0正常 9-冻结
	 */
	@ApiModelProperty(value = "租户冻结标记,9:冻结,0:正常")
	private String status;



}
