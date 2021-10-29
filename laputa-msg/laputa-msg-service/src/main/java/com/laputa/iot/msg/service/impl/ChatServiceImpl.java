package com.laputa.iot.msg.service.impl;


import com.laputa.iot.common.core.base.result.ResultCode;
import com.laputa.iot.common.core.constant.CommonConstants;
import com.laputa.iot.common.core.exception.ServiceInternalException;
import com.laputa.iot.common.data.utils.RedisUtils;
import com.laputa.iot.msg.api.dto.EventDTO;
import com.laputa.iot.msg.api.dto.WatcherDTO;
import com.laputa.iot.msg.api.enums.ContainerEnum;
import com.laputa.iot.msg.api.enums.RelationTypeEnum;
import com.laputa.iot.msg.api.vo.MessageVO;
import com.laputa.iot.msg.api.vo.Shouting;
import com.laputa.iot.msg.service.ChatService;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName ChatService
 * @Description
 * @Author Sommer Jiang
 * @Date 2021/6/16 10:24
 * @Version 1.0.0
 **/
@Service
@Slf4j
public class ChatServiceImpl implements ChatService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ChatServiceImpl(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private MessageServiceImpl messageService;

    @Autowired
    private AsyncJobService asyncJobService;
    
    // watcher只存在30s，过期说明客户端没在监听，离线了
    private int REDIS_WATCHER_EXPIRED = CommonConstants.REDIS_WATCHER_EXPIRED;
    private String REDIS_WATCHER = "chat_watcher_";

    private String REDIS_UNREAD_COUNT = "chat_unread_count_";

    private String USER_RELATION_FILTER = "user_relation_filter_";

    @Override
    public Boolean upInsertWatcher(WatcherDTO watcher){
        if(watcher!=null&&watcher.getWatcherId()!=null&&watcher.getPeerId()!=null){
            if(watcher.getAction()!=null&&"entry".equals(watcher.getAction())) {
                redisUtils.set(REDIS_WATCHER + watcher.getWatcherId(), watcher, REDIS_WATCHER_EXPIRED);
                return true;
            }else{
                if (redisUtils.hasKey(REDIS_WATCHER +  watcher.getWatcherId())){
                    WatcherDTO existedWatcher = (WatcherDTO) redisUtils.get(REDIS_WATCHER + watcher.getWatcherId());
                    if(existedWatcher!=null&& watcher.getPeerId().equals(existedWatcher.getPeerId())){
                        log.info("remove watcher:{}",watcher.toString());
                        return redisUtils.delete(REDIS_WATCHER + watcher.getWatcherId());
                    }else{
                        return true;
                    }
                }else{
                    return true;
                }
            }
        }else{
            return false;
        }
    }

    @Override
    public Boolean isWatchingMe(Long myId,Long peerId,String peerType){
        if(myId!=null&&peerId!=null&&peerId!=null){
            if (redisUtils.hasKey(REDIS_WATCHER + peerId)){
                WatcherDTO watcher = (WatcherDTO) redisUtils.get(REDIS_WATCHER + peerId);
                if(watcher!=null&& myId.equals(watcher.getPeerId())){
                    log.info("isWatchingMe myId:{},peerId:{}",myId,peerId);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean sendPersonalMessage(Long peerId,Long senderId, MessageVO message) throws ServiceInternalException {
        log.info("sendPersonalMessage peerId:{},senderId:{}, message:{}",peerId,senderId,message.toString());
        if (peerId!=null&&message!=null) {
            Integer filterRelTypeCode = getRelationFilterRelTypeCode(peerId,senderId);
            if(filterRelTypeCode!=null&& RelationTypeEnum.getNegativeCodeList().contains(filterRelTypeCode)){
                if(RelationTypeEnum.BLACK.getCode()==filterRelTypeCode){
                    throw new ServiceInternalException(ResultCode.USER_SOCIAL_BE_BLACKED);
                }else if(RelationTypeEnum.IGNORE.getCode()==filterRelTypeCode){
                    throw new ServiceInternalException(ResultCode.USER_SOCIAL_BE_IGNORED);
                }
            }
            boolean result = messageService.insertTodayMessage(message);
            if(result){
                if(isWatchingMe(senderId,message.getTo(), ContainerEnum.PERSON.getDesc())){
                    sendPersonalShouting(message.getTo(),message);
                    if(ContainerEnum.PERSON.getDesc().equals(message.getPeerType())){
                        sendPersonalShouting(message.getFrom(),message);
                    }
                    asyncJobService.sendMsgSubProcess(message.getFrom(),ContainerEnum.PERSON.getCode(),message.getTo(), ContainerEnum.getCodeByDesc(message.getPeerType()),message.getCreateTime(),false);
                    return true;
                }else{
                    if(ContainerEnum.PERSON.getDesc().equals(message.getPeerType())){
                        sendPersonalShouting(message.getFrom(),message);
                    }
                    asyncJobService.sendMsgSubProcess(message.getFrom(),ContainerEnum.PERSON.getCode(),message.getTo(),ContainerEnum.getCodeByDesc(message.getPeerType()),message.getCreateTime(),true);
                    throw new ServiceInternalException("对方不在线");
                }
            }else{
                throw new ServiceInternalException("保存消息失败");
            }

        }else{
            throw new ServiceInternalException(ResultCode.WEBSOCKET_REQUEST_ERROR);
        }
    }

    @Override
    public Integer changeUnRead(Long senderId,Integer senderType,Long receiverId,String action){
        if(senderId!=null&&senderType!=null&&receiverId!=null){
            Boolean result = null;
            if("update".equals(action)){
                result = upInsertUnRead(senderId,senderType,receiverId);
            }else if("clear".equals(action)){
                result = clearUnRead(senderId,senderType,receiverId);
            }
            if(result!=null&&result){
                Integer count = 0;
                if (redisUtils.hHasKey(REDIS_UNREAD_COUNT + receiverId,String.valueOf(senderId))){
                    count = (Integer)redisUtils.hGet(REDIS_UNREAD_COUNT + receiverId,String.valueOf(senderId));
                }
                return count;
            }else{
                return -1;
            }
        }else{
            return -1;
        }
    }

    private Boolean upInsertUnRead(Long senderId,Integer senderType,Long receiverId){
        if(senderId!=null&&senderType!=null&&receiverId!=null){
            if (redisUtils.hHasKey(REDIS_UNREAD_COUNT + receiverId,String.valueOf(senderId))){
                redisUtils.hashIncr(REDIS_UNREAD_COUNT + receiverId,String.valueOf(senderId),1);
            }else{
                redisUtils.hSet(REDIS_UNREAD_COUNT + receiverId,String.valueOf(senderId),0);
            }
            return true;
        }else{
            return false;
        }
    }

    private Boolean clearUnRead(Long senderId,Integer senderType,Long receiverId){
        if(senderId!=null&&senderType!=null&&receiverId!=null){
            if (redisUtils.hHasKey(REDIS_UNREAD_COUNT + receiverId,String.valueOf(senderId))){
                redisUtils.hSet(REDIS_UNREAD_COUNT + receiverId,String.valueOf(senderId),0);
            }else{
                redisUtils.hSet(REDIS_UNREAD_COUNT + receiverId,String.valueOf(senderId),0);
            }
            return true;
        }else{
            return false;
        }
    }

    public Integer getUnRead(String senderId,Integer senderType,String receiverId){
        if(senderId!=null&&senderType!=null&&receiverId!=null){
            if (redisUtils.hHasKey(REDIS_UNREAD_COUNT + receiverId,senderId)){
                return (Integer)redisUtils.hGet(REDIS_UNREAD_COUNT + receiverId,senderId);
            }else{
                redisUtils.hSet(REDIS_UNREAD_COUNT + receiverId,senderId,0);
                return 0;
            }
        }else{
            return 0;
        }
    }

    public Integer getAllUnRead(String receiverId){
        if(receiverId!=null){
            if(redisUtils.hasKey(REDIS_UNREAD_COUNT + receiverId)){
                Integer sum = new Integer(0);
                Map<Object,Object> hm = redisUtils.hmGet(REDIS_UNREAD_COUNT + receiverId);
                if(ObjectUtils.isNotEmpty(hm)){
                    for (Map.Entry<Object,Object> entry : hm.entrySet()) {
                        Integer count = (Integer) entry.getValue();
                        if(count>0){
                            if(count>=99){
                                sum +=99;
                            }else{
                                sum +=count;
                            }
                        }
                    }
                }
                return sum;
            }else{
                return 0;
            }
        }else{
            return 0;
        }
    }

    public Boolean sendPersonalEvent(Long userId,Long hostId, EventDTO event)  throws ServiceInternalException{
        log.info("sendPersonalEvent userId:{},hostId:{}, event:{}",userId,hostId,event.toString());
        event.setType("event");
        if (userId!=null&&event!=null) {
            sendPersonalShouting(event.getTo(),event);
            return true;
        }else{
            throw new ServiceInternalException(ResultCode.WEBSOCKET_REQUEST_ERROR);
        }
    }

    public void sendPersonalShouting(Long userId, Shouting shouting)  throws ServiceInternalException{
        log.info("sendPersonalShouting userId:{} shouting:{}",userId,shouting.toString());
        shouting.setType("message");
        simpMessagingTemplate.convertAndSendToUser(
                String.valueOf(userId),
                "/topic/personal/"+userId,
                shouting
        );
    }

    public Integer getRelationFilterRelTypeCode(Long userId,Long peerId){
        if(userId!=null && userId> 0 &&peerId!=null && peerId >0) {
            Integer relTypeCode = null;
            if(redisUtils.hHasKey(USER_RELATION_FILTER + userId,String.valueOf(peerId))){
                relTypeCode = (Integer) redisUtils.hGet(USER_RELATION_FILTER + userId,String.valueOf(peerId));
            }
            return relTypeCode;
        }else{
            return null;
        }
    }

    public Boolean upInsertRelationFilterRelTypeCode(Long userId,Long peerId,Integer relTypeCode){
        if(userId!=null && userId> 0 &&peerId!=null && peerId >0&&relTypeCode!=null) {
            redisUtils.hSet(USER_RELATION_FILTER + userId,String.valueOf(peerId),String.valueOf(relTypeCode));
            return true;
        }else{
            return false;
        }
    }

    public Boolean removeRelationFilterRelTypeCode(Long userId,Long peerId,Integer relTypeCode){
        log.info("removeRelationFilterRelTypeCode userId:{},peerId:{},relTypeCode:{}",userId,peerId,relTypeCode);
        if(userId!=null && userId> 0 &&peerId!=null && peerId >0&&relTypeCode!=null) {
            Integer existedCode = getRelationFilterRelTypeCode(userId,peerId);
            if(relTypeCode.equals(existedCode)){
                redisUtils.hDel(USER_RELATION_FILTER + userId,peerId);
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }





}
