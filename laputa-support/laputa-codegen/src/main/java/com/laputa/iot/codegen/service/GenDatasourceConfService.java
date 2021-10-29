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
package com.laputa.iot.codegen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laputa.iot.codegen.entity.GenDatasourceConf;
import com.laputa.iot.common.core.base.dto.R;

/**
 * 数据源表
 *
 * @author sommer.jiang
 * @date 2019-03-31 16:00:20
 */
public interface GenDatasourceConfService extends IService<GenDatasourceConf> {

	/**
	 * 保存数据源并且加密
	 * @param genDatasourceConf
	 * @return
	 */
	R saveDsByEnc(GenDatasourceConf genDatasourceConf);

	/**
	 * 更新数据源
	 * @param genDatasourceConf
	 * @return
	 */
	R updateDsByEnc(GenDatasourceConf genDatasourceConf);

	/**
	 * 更新动态数据的数据源列表
	 * @param datasourceConf
	 * @return
	 */
	void addDynamicDataSource(GenDatasourceConf datasourceConf);

	/**
	 * 校验数据源配置是否有效
	 * @param datasourceConf 数据源信息
	 * @return 有效/无效
	 */
	R checkDataSource(GenDatasourceConf datasourceConf);

	/**
	 * 通过数据源名称删除
	 * @param dsId 数据源ID
	 * @return
	 */
	Boolean removeByDsId(Integer dsId);

}
