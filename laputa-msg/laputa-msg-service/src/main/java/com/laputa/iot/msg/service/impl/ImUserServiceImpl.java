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
package com.laputa.iot.msg.service.impl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.core.base.result.ResultCode;
import com.laputa.iot.common.core.constant.CacheConstants;
import com.laputa.iot.common.core.exception.ServiceInternalException;
import com.laputa.iot.common.core.util.BeanCopyUtil;
import com.laputa.iot.common.core.util.NumberHelper;
import com.laputa.iot.common.data.utils.RedisUtils;
import com.laputa.iot.msg.api.dto.EventDTO;
import com.laputa.iot.msg.api.entity.ChatUser;
import com.laputa.iot.msg.api.entity.ImUser;
import com.laputa.iot.msg.api.dto.ImUserDTO;
import com.laputa.iot.msg.api.enums.ContainerEnum;
import com.laputa.iot.msg.api.enums.RelationTypeEnum;
import com.laputa.iot.msg.api.enums.UserActiveStatusEnum;
import com.laputa.iot.msg.api.enums.UserStatusEnum;
import com.laputa.iot.msg.api.vo.RelationVO;
import com.laputa.iot.msg.api.vo.Shouting;
import com.laputa.iot.msg.mapper.ImUserMapper;
import com.laputa.iot.msg.service.ImUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 聊天用户
 *
 * @author Sommer
 * @date 2021-10-23 14:18:38
 */
@Service
@Slf4j
public class ImUserServiceImpl extends ServiceImpl<ImUserMapper, ImUser> implements ImUserService {

    private final SimpMessagingTemplate simpMessagingTemplate;



    /**
      * 分页查询ImUser
      * @param page
      * @param imUser 参数列表
      * @return
      */
    @Override
    public IPage queryPage(Page page, ImUser imUser) {
        IPage<ImUser> selectPage = baseMapper.selectPage(page, Wrappers.query(imUser));

        return selectPage;
    }
    /**
	 * 保存用户信息
	 * @param imUser DTO 对象
	 * @return success/fail
	 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstants.IM_USER_KEY, key = "#imUser.id")
    public Boolean saveImUser(ImUser imUser) {

        return this.save(imUser);
    }


    /**
     * 通过ID查询ImUser
     * @param id imUserID
     * @return ImUserVO
     */
    @Override
    @Cacheable(value = CacheConstants.IM_USER_KEY, key = "#id")
    public   ImUser selectImUserById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 删除ImUser
     * @param id imUserID
     * @return Boolean
     */
    @Override
    @CacheEvict(value = CacheConstants.IM_USER_KEY, key = "#id")
    public Boolean deleteImUserById(Long id) {

        return this.removeById(id);
    }

    @Override
    @CacheEvict(value = CacheConstants.USER_DETAILS, key = "#imUser.id")
    public Boolean updateImUser(ImUser imUser) {
        return this.updateById(imUser);
    }



    @Resource
    private RedisUtils redisUtils;

    private String REDIS_PREFIX_ROOM_INFO = "meeting_room_info_";
    private String REDIS_PREFIX_ACK = "meeting_room_ack_";

    private String USER_ACTIVE_STATUS = "user_active_status";

    private long USER_ACTIVE_STATUS_TIME = 30;


    @Autowired
    public ImUserServiceImpl(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public Boolean sendPersonalEvent(Long userId, EventDTO event) {
        log.info("sendPersonalEvent userId:{},event:{}", userId, event.toString());
        event.setType("event");
        sendPersonalShouting(event.getTo(), event);
        return true;
    }

    public void sendPersonalShouting(Long userId, Shouting shouting) {
        log.info("sendPersonalShouting userId:{} shouting:{}", userId, shouting.toString());
        simpMessagingTemplate.convertAndSendToUser(
                String.valueOf(userId),
                "/topic/notify/" + userId,
                shouting
        );
    }


    public R<List<RelationVO>> getUserRecentRelation(@PathVariable Long userId) {
        log.info("getUserRecentRelation userId:" + userId);
//        SysUser user = userService.getById(userId);
//        if (user ==null){
//            return R.fail(ResultCode.API_DB_FAIL);
//        }
//        List<RelationVO> relationVOList = relationService.getUserRecentContactRelationVO(userId);
//        if(relationVOList!=null){
//            return R.ok(relationVOList);
//        }else{
//            return R.fail(ResultCode.API_VALIDATION_ERROR);
//        }
        return R.fail("meiy");
    }

    public Integer getUserActiveStatus(Long userId) {
        if (NumberHelper.isIDValid(userId)) {
            if (redisUtils.hHasKey(USER_ACTIVE_STATUS, String.valueOf(userId))) {
                Integer status = (Integer) redisUtils.hGet(USER_ACTIVE_STATUS, String.valueOf(userId));
                log.info("getUserActiveStatus userId:{},status:{}", userId, status);
                return status;
            } else {
                return UserActiveStatusEnum.OFFLINE.getCode();
            }
        } else {
            return UserActiveStatusEnum.OFFLINE.getCode();
        }
    }

    public R<List<RelationVO>> getUserRelation(Long peerId, Long hostId, Integer code, Object o) {
        return null;
    }

    public List<RelationVO> getUserRelation(Long peerId, Long hostId, String code, Object o) throws ServiceInternalException {
        return null;
    }


    public Boolean isInPeerBookMark(Long userId, Long peerId) {
        if (NumberHelper.isIDValid(userId) && NumberHelper.isIDValid(peerId)) {
            List<Integer> relTypeList = new ArrayList<>();
            relTypeList.add(RelationTypeEnum.FRIEND.getCode());
            R<List<RelationVO>> result = getUserRelation(peerId, null, ContainerEnum.PERSON.getCode(), relTypeList);
            log.info("isInPeerBookMark  getUserRelation result:{}", result.toString());
            if (result.getCode() == ResultCode.SUCCESS.getCode()) {
                List<RelationVO> relationVOList = result.getData();
                if (CollectionUtils.isNotEmpty(relationVOList)) {
                    if (relationVOList.contains(userId)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public R getUserRelation( Long userId,Long peerId, String category){
        log.info("getUserRelation userId:"+userId);
        ChatUser user = this.getById(userId);
        if (user ==null){
            return R.fail(ResultCode.API_DB_FAIL);
        }
        if (!UserStatusEnum.getVisibleUserStatusCodeList().contains(user.getStatus())){
            return R.fail(ResultCode.AUTH_ACCOUNT_INVALID);
        }
        String hostId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        log.info("Authentication:"+SecurityContextHolder.getContext().getAuthentication().toString());
        if(!hostId.equals(user.getUserId())){
            return R.fail(ResultCode.AUTH_UNAUTHORIZED);
        }
        try {

            List<RelationVO> relationVOList = getUserRelation(userId, peerId, category, null);
            return R.ok(relationVOList);
        }catch (ServiceInternalException e){
            return R.fail(e.getResultCode(),e.getErrorMsg());
        }
    }

    private ChatUser getById(Long userId) {
        return new ChatUser();
    }

    public Boolean updateUserActiveStatus(String username, Integer status) {
        if (!StringUtils.isBlank(username)) {
            if(!UserActiveStatusEnum.getAllCodeList().contains(status)){
                status = UserActiveStatusEnum.ACTIVE_VISIBLE.getCode();
            }
            redisUtils.hSet(USER_ACTIVE_STATUS,username,status,USER_ACTIVE_STATUS_TIME);
            return true;
        }else{
            return false;
        }
    }



}
