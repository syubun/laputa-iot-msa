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

package com.laputa.iot.manager.manager.service.impl;

import com.laputa.iot.manager.manager.service.LoadBalanceService;
import com.laputa.iot.manager.config.ConfigReader;
import com.laputa.iot.manager.model.LoadBalanceInfo;
import com.laputa.iot.manager.redis.RedisServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LCN on 2017/12/5
 */
@Service
public class LoadBalanceServiceImpl implements LoadBalanceService {

	@Autowired
	private RedisServerService redisServerService;

	@Autowired
	private ConfigReader configReader;

	@Override
	public boolean put(LoadBalanceInfo loadBalanceInfo) {
		String groupName = getLoadBalanceGroupName(loadBalanceInfo.getGroupId());
		redisServerService.saveLoadBalance(groupName, loadBalanceInfo.getKey(), loadBalanceInfo.getData());
		return true;
	}

	@Override
	public LoadBalanceInfo get(String groupId, String key) {
		String groupName = getLoadBalanceGroupName(groupId);
		String bytes = redisServerService.getLoadBalance(groupName, key);
		if (bytes == null) {
			return null;
		}

		LoadBalanceInfo loadBalanceInfo = new LoadBalanceInfo();
		loadBalanceInfo.setGroupId(groupId);
		loadBalanceInfo.setKey(key);
		loadBalanceInfo.setData(bytes);
		return loadBalanceInfo;
	}

	@Override
	public boolean remove(String groupId) {
		redisServerService.deleteKey(getLoadBalanceGroupName(groupId));
		return true;
	}

	@Override
	public String getLoadBalanceGroupName(String groupId) {
		return configReader.getKeyPrefixLoadbalance() + groupId;
	}

}
