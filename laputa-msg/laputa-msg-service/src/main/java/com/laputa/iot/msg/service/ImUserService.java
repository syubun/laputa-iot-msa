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

package com.laputa.iot.msg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.msg.api.dto.EventDTO;
import com.laputa.iot.msg.api.entity.ImUser;
import com.laputa.iot.msg.api.dto.ImUserDTO;
import com.laputa.iot.msg.api.vo.RelationVO;

import java.util.List;

/**
 * 聊天用户
 *
 * @author Sommer
 * @date 2021-10-23 14:18:38
 */
public interface ImUserService extends IService<ImUser> {



    /**
     * 分页查询ImUser
     * @param page 分页对象
     * @param imUser 参数列表
     * @return
     */
    IPage queryPage(Page page, ImUser imUser);

    /**
     * 删除ImUser
     * @param id
     * @return boolean
     */
    Boolean deleteImUserById(Long id);

    /**
     * 更新指定ImUser
     * @param imUser ImUser信息
     * @return
     */
    Boolean updateImUser(ImUser imUser);

    /**
     * 通过ID查询ImUser
     * @param id imUserID
     * @return ImUserVO
     */
    ImUser selectImUserById(Long id);



    /**
     * 保存ImUser
     * @param imUser DTO 对象
     * @return success/fail
     */
    Boolean saveImUser(ImUser imUser);

    /**
     * 更新用户状态
     * @param username
     * @param status
     * @return
     */

    Boolean updateUserActiveStatus(String username, Integer status);


    Boolean sendPersonalEvent(Long hostId, EventDTO event);

    Integer getUserActiveStatus(Long hostId);

    R<List<RelationVO>> getUserRelation(Long peerId, Long hostId, Integer code, Object o);

    Boolean isInPeerBookMark(Long hostId, Long peerId);

    R<List<RelationVO>> getUserRecentRelation(Long userId);
}
