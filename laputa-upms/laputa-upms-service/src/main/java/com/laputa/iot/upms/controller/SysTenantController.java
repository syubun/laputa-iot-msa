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

package com.laputa.iot.upms.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.upms.api.entity.SysTenant;
import com.laputa.iot.upms.service.SysTenantService;
import com.laputa.iot.common.core.constant.CacheConstants;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.log.annotation.SysLog;
import com.laputa.iot.common.security.annotation.Inner;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 租户管理
 *
 * @author sommer.jiang
 * @date 2019-05-15 15:55:41
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/tenant")
@Api(value = "tenant", tags = "租户管理")
public class SysTenantController {

	private final SysTenantService sysTenantService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param sysTenant 租户
	 * @return
	 */
	@GetMapping("/page")
	public R getSysTenantPage(Page page, SysTenant sysTenant) {
		return R.ok(sysTenantService.page(page, Wrappers.query(sysTenant)));
	}

	/**
	 * 通过id查询租户
	 * @param id id
	 * @return R
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable("id") Integer id) {
		return R.ok(sysTenantService.getById(id));
	}

	/**
	 * 新增租户
	 * @param sysTenant 租户
	 * @return R
	 */
	@SysLog("新增租户")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('admin_systenant_add')")
	@CacheEvict(value = CacheConstants.TENANT_DETAILS, allEntries = true)
	public R save(@RequestBody SysTenant sysTenant) {
		return R.ok(sysTenantService.saveTenant(sysTenant));
	}

	/**
	 * 修改租户
	 * @param sysTenant 租户
	 * @return R
	 */
	@SysLog("修改租户")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('admin_systenant_edit')")
	@CacheEvict(value = CacheConstants.TENANT_DETAILS, allEntries = true)
	public R updateById(@RequestBody SysTenant sysTenant) {
		return R.ok(sysTenantService.updateById(sysTenant));
	}

	/**
	 * 通过id删除租户
	 * @param id id
	 * @return R
	 */
	@SysLog("删除租户")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('admin_systenant_del')")
	@CacheEvict(value = CacheConstants.TENANT_DETAILS, allEntries = true)
	public R removeById(@PathVariable Integer id) {
		return R.ok(sysTenantService.removeById(id));
	}

	/**
	 * 查询全部有效的租户
	 * @return
	 */
	@Inner(value = false)
	@GetMapping("/list")
	public R list() {
//		log.info("tenent/list");
		List<SysTenant> tenants = sysTenantService.getNormalTenant().stream()
				.filter(tenant -> tenant.getStartTime().isBefore(LocalDateTime.now()))
				.filter(tenant -> tenant.getEndTime().isAfter(LocalDateTime.now())).collect(Collectors.toList());
		return R.ok(tenants);
	}

}
