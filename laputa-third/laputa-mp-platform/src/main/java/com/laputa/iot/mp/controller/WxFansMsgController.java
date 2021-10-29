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
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.mp.entity.WxMsg;
import com.laputa.iot.mp.service.WxMsgService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 微信粉丝消息管理
 *
 * @author sommer.jiang
 * @date 2019-03-27 20:45:27
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/wxfansmsg")
public class WxFansMsgController {

	private final WxMsgService wxMsgService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param msg 查询条件
	 */
	@GetMapping("/page")
	public R getWxMsgPage(Page page, WxMsg msg) {
		return R.ok(wxMsgService.page(page, Wrappers.query(msg)));
	}

	/**
	 * 通过id查询微信消息
	 * @param id id
	 * @return R
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable("id") Integer id) {
		return R.ok(wxMsgService.getById(id));
	}

	/**
	 * 新增微信消息
	 * @param wxMsg 微信消息
	 * @return R
	 */
	@PostMapping
	@PreAuthorize("@pms.hasPermission('mp_wxmsg_add')")
	public R save(@RequestBody WxMsg wxMsg) {
		return wxMsgService.saveAndPushMsg(wxMsg);
	}

	/**
	 * 修改微信消息
	 * @param wxMsg 微信消息
	 * @return R
	 */
	@PutMapping
	@PreAuthorize("@pms.hasPermission('mp_wxmsg_edit')")
	public R updateById(@RequestBody WxMsg wxMsg) {
		return R.ok(wxMsgService.updateById(wxMsg));
	}

	/**
	 * 通过id删除微信消息
	 * @param id id
	 * @return R
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('mp_wxmsg_del')")
	public R removeById(@PathVariable String id) {
		return R.ok(wxMsgService.removeById(id));
	}

}
