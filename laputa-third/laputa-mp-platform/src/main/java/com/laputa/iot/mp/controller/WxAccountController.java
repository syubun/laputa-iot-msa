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
package com.laputa.iot.mp.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.common.core.constant.CacheConstants;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.log.annotation.SysLog;
import com.laputa.iot.mp.entity.WxAccount;
import com.laputa.iot.mp.service.WxAccountService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 公众号账户
 *
 * @author sommer.jiang
 * @date 2019-03-26 22:07:53
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wxaccount")
public class WxAccountController {

	private final WxAccountService wxAccountService;

	private final RedisTemplate redisTemplate;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param wxAccount 公众号账户
	 * @return
	 */
	@GetMapping("/page")
	public R getWxAccountPage(Page page, WxAccount wxAccount) {
		return R.ok(wxAccountService.page(page, Wrappers.query(wxAccount)));
	}

	/**
	 * 通过id查询公众号账户
	 * @param id id
	 * @return R
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable("id") Integer id) {
		return R.ok(wxAccountService.getById(id));
	}

	/**
	 * 新增公众号账户
	 * @param wxAccount 公众号账户
	 * @return R
	 */
	@SysLog("新增公众号账户")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('mp_wxaccount_add')")
	public R save(@RequestBody WxAccount wxAccount) {
		wxAccountService.save(wxAccount);
		redisTemplate.convertAndSend(CacheConstants.MP_REDIS_RELOAD_TOPIC, "重新加载公众号配置");
		return R.ok();
	}

	/**
	 * 修改公众号账户
	 * @param wxAccount 公众号账户
	 * @return R
	 */
	@SysLog("修改公众号账户")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('mp_wxaccount_edit')")
	public R updateById(@RequestBody WxAccount wxAccount) {
		wxAccountService.updateById(wxAccount);
		redisTemplate.convertAndSend(CacheConstants.MP_REDIS_RELOAD_TOPIC, "重新加载公众号配置");
		return R.ok();
	}

	/**
	 * 通过id删除公众号账户
	 * @param id id
	 * @return R
	 */
	@SysLog("删除公众号账户")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('mp_wxaccount_del')")
	public R removeById(@PathVariable Integer id) {
		wxAccountService.removeById(id);
		redisTemplate.convertAndSend(CacheConstants.MP_REDIS_RELOAD_TOPIC, "重新加载公众号配置");
		return R.ok();
	}

	/**
	 * 生成公众号二维码
	 * @param appId
	 * @return
	 */
	@SysLog("生成公众号二维码")
	@PostMapping("/qr/{appId}")
	@PreAuthorize("@pms.hasPermission('mp_wxaccount_add')")
	public R qr(@PathVariable String appId) {
		return wxAccountService.generateQr(appId);
	}

	/**
	 * 获取公众号列表
	 * @return
	 */
	@GetMapping("/list")
	public R list() {
		return R.ok(wxAccountService.list());
	}

	/**
	 * 获取公众号接口数据
	 * @param appId 公众号
	 * @param interval 时间间隔
	 * @return
	 */
	@GetMapping("/statistics")
	public R statistics(String appId, String interval) {
		return wxAccountService.statistics(appId, interval);
	}

}
