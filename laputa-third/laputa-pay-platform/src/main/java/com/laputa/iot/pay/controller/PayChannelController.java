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

package com.laputa.iot.pay.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.log.annotation.SysLog;
import com.laputa.iot.pay.service.PayChannelService;
import com.laputa.iot.pay.entity.PayChannel;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 渠道
 *
 * @author sommer.jiang
 * @date 2019-05-28 23:57:58
 */
@RestController
@AllArgsConstructor
@RequestMapping("/paychannel")
@Api(value = "paychannel", tags = "paychannel管理")
public class PayChannelController {

	private final PayChannelService payChannelService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param payChannel 渠道
	 * @return
	 */
	@GetMapping("/page")
	public R getPayChannelPage(Page page, PayChannel payChannel) {
		return R.ok(payChannelService.page(page, Wrappers.query(payChannel)));
	}

	/**
	 * 通过id查询渠道
	 * @param id id
	 * @return R
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable("id") Integer id) {
		return R.ok(payChannelService.getById(id));
	}

	/**
	 * 新增渠道
	 * @param payChannel 渠道
	 * @return R
	 */
	@SysLog("新增渠道")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('pay_paychannel_add')")
	public R save(@RequestBody PayChannel payChannel) {
		payChannelService.saveChannel(payChannel);
		return R.ok();
	}

	/**
	 * 修改渠道
	 * @param payChannel 渠道
	 * @return R
	 */
	@SysLog("修改渠道")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('pay_paychannel_edit')")
	public R updateById(@RequestBody PayChannel payChannel) {
		payChannelService.updateById(payChannel);
		return R.ok();
	}

	/**
	 * 通过id删除渠道
	 * @param id id
	 * @return R
	 */
	@SysLog("删除渠道")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('pay_paychannel_del')")
	public R removeById(@PathVariable Integer id) {
		payChannelService.removeById(id);
		return R.ok();
	}

}
