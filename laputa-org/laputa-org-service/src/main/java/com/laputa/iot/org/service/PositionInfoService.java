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

package com.laputa.iot.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.org.api.entity.PositionInfo;
import com.laputa.iot.org.api.dto.PositionInfoDTO;
import com.laputa.iot.org.api.vo.OrgTreeVo;

import java.util.List;

/**
 * 岗位
 *
 * @author Sommer
 * @date 2021-10-03 12:04:34
 */
public interface PositionInfoService extends IService<PositionInfo> {



    /**
     * 分页查询PositionInfo
     * @param page 分页对象
     * @param positionInfoDTO 参数列表
     * @return
     */
    IPage queryPage(Page page, PositionInfoDTO positionInfoDTO);

    /**
     * 删除PositionInfo
     * @param id
     * @return boolean
     */
    Boolean deletePositionInfoById(Long id);

    /**
     * 更新指定PositionInfo
     * @param positionInfo PositionInfo信息
     * @return
     */
    Boolean updatePositionInfo(PositionInfo positionInfo);

    /**
     * 通过ID查询PositionInfo
     * @param id positionInfoID
     * @return PositionInfoVO
     */
    PositionInfo selectPositionInfoById(Long id);



    /**
     * 保存PositionInfo
     * @param positionInfo DTO 对象
     * @return success/fail
     */
    Boolean savePositionInfo(PositionInfo positionInfo);

    /**
     * 得到岗位树
     * @return List<OrgTreeVo>
     */
    List<OrgTreeVo> getPositionTree();
}
