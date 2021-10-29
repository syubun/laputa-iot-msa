/*
 *
 *      Copyright (c) 2018-2025, Laputa IOT All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the www.laputa-iot.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: SommerJiang (sommer_jiang@163.com)
 *
 */

package com.laputa.iot.upms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.laputa.iot.upms.api.dto.SysOauthClientDetailsDTO;
import com.laputa.iot.upms.api.entity.SysOauthClientDetails;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author sommer.jiang
 * @since 2018-05-15
 */
public interface SysOauthClientDetailsService extends IService<SysOauthClientDetails> {

	/**
	 * 通过ID删除客户端
	 * @param clientId
	 * @return
	 */
	Boolean removeByClientId(String clientId);

	/**
	 * 根据客户端信息
	 * @param clientDetailsDTO
	 * @return
	 */
	Boolean updateClientById(SysOauthClientDetailsDTO clientDetailsDTO);

	/**
	 * 添加客户端
	 * @param clientDetailsDTO
	 * @return
	 */
	Boolean saveClient(SysOauthClientDetailsDTO clientDetailsDTO);

	/**
	 * 分页查询客户端信息
	 * @param page
	 * @param query
	 * @return
	 */
	Page queryPage(Page page, SysOauthClientDetails query);

}
