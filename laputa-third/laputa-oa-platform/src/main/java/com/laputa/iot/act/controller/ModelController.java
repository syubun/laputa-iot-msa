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

import com.laputa.iot.act.service.ModelService;
import com.laputa.iot.act.dto.ModelForm;
import com.laputa.iot.common.core.base.dto.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author sommer.jiang
 * @date 2018/9/25
 */
@RestController
@RequestMapping("/model")
@AllArgsConstructor
public class ModelController {

	private final ModelService modelService;

	@PostMapping(value = "/insert")
	public R<Boolean> insertForm(@RequestBody @Valid ModelForm form) {
		modelService.create(form.getName(), form.getKey(), form.getDesc(), form.getCategory());
		return R.ok(Boolean.TRUE);
	}

	@PostMapping
	public R createModel(@RequestParam String name, @RequestParam String key, @RequestParam String desc,
			@RequestParam String category) {
		return R.ok(modelService.create(name, key, desc, category));
	}

	@GetMapping
	public R getModelPage(@RequestParam Map<String, Object> params) {
		return R.ok(modelService.getModelPage(params));
	}

	@DeleteMapping("/{id}")
	public R removeModelById(@PathVariable("id") String id) {
		return R.ok(modelService.removeModelById(id));

	}

	@PostMapping("/deploy/{id}")
	public R deploy(@PathVariable("id") String id) {
		return modelService.deploy(id) ? R.ok() : R.failed();
	}

}
