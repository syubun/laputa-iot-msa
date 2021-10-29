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

package com.laputa.iot.act.controller;

import cn.hutool.core.io.IoUtil;
import com.laputa.iot.act.dto.LeaveBillDto;
import com.laputa.iot.act.service.ActTaskService;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.security.annotation.Inner;
import com.laputa.iot.common.security.util.SecurityUtils;
import com.laputa.iot.common.xss.core.XssCleanIgnore;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.Map;

/**
 * @author sommer.jiang
 * @date 2018/9/28
 */
@RestController
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {

	private final ActTaskService actTaskService;

	@GetMapping("/todo")
	public R todo(@RequestParam Map<String, Object> params) {
		return R.ok(actTaskService.getTaskByName(params, SecurityUtils.getUser().getUsername()));
	}

	@GetMapping("/{id}")
	public R getTaskById(@PathVariable String id) {
		return R.ok(actTaskService.getTaskById(id));
	}

	@PostMapping
	@XssCleanIgnore
	public R submitTask(@RequestBody LeaveBillDto leaveBillDto) {
		return R.ok(actTaskService.submitTask(leaveBillDto));
	}

	@Inner(value = false)
	@GetMapping("/view/{id}")
	public ResponseEntity viewCurrentImage(@PathVariable String id) {
		InputStream imageStream = actTaskService.viewByTaskId(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity(IoUtil.readBytes(imageStream), headers, HttpStatus.CREATED);
	}

	@GetMapping("/comment/{id}")
	public R commitList(@PathVariable String id) {
		return R.ok(actTaskService.getCommentByTaskId(id));
	}

}
