package com.laputa.iot.msg.controller;

import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.core.base.result.ResultCode;
import com.laputa.iot.common.core.exception.ServiceInternalException;
import com.laputa.iot.common.core.util.DateUtils;
import com.laputa.iot.common.security.util.SecurityUtils;
import com.laputa.iot.msg.api.dto.EventDTO;
import com.laputa.iot.msg.api.dto.WatcherDTO;
import com.laputa.iot.msg.api.enums.ContainerEnum;
import com.laputa.iot.msg.api.enums.EventEnum;
import com.laputa.iot.msg.api.vo.MessageVO;
import com.laputa.iot.msg.service.ChatService;
import com.laputa.iot.msg.service.ImUserService;
import com.laputa.iot.msg.service.impl.MessageServiceImpl;
import com.laputa.iot.msg.service.impl.AsyncJobService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ChatWebSocketController
 * @Description
 * @Author Sommer Jiang
 * @Date 2021/6/16 10:16
 * @Version 1.0.0
 **/
@RestController
@Slf4j
public class IMController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private MessageServiceImpl messageService;


    @Autowired
    private ImUserService userService;

    @Autowired
    private AsyncJobService asyncJobService;

    @MessageMapping({"/watch/{userId}"})
    public R watchChatSession(@DestinationVariable Long userId, WatcherDTO watcher, OAuth2Authentication auth2Authentication) {

        Long hostId = SecurityUtils.getUser(auth2Authentication).getId();
        if (hostId!=null &&hostId.equals(watcher.getWatcherId())){
            watcher.setPeerId(userId);
            watcher.setCreateTime(DateUtils.getCurrentDateTime());
            boolean result =  chatService.upInsertWatcher(watcher);
            if (result){
                return R.success();
            }else{
                return R.fail(ResultCode.API_DB_FAIL);
            }
        }else{
            return R.fail(ResultCode.AUTH_NEED_LOGIN);
        }

    }

    @MessageMapping({"/personal/sendmsg/{peerId}"})
    public R<String> sendPersonalMessage(@DestinationVariable Long peerId, MessageVO message) {
        log.info("sendPersonalMessage peerId:{}, message:{}",peerId,message.toString());

        if (SecurityUtils.getUser()!=null) {
            Long hostId = SecurityUtils.getUser().getId();
//            String hostId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            if (hostId > 0 &&peerId!=null && peerId >0){
                log.info("sendPersonalMessage hostId:{}",hostId);
                Date messageDate = DateUtils.getCurrentDateTime();
                message.setFrom(hostId);
                message.setTo(peerId);
                message.setPeerType(ContainerEnum.PERSON.getDesc());
                message.setCreateTime(messageDate);
                message.setType("message"); //用户只能发送message，不能发送event
                try {
                    boolean result = chatService.sendPersonalMessage(peerId,hostId,message);
                    if(result){
                        return R.ok();
                    }else{
                        return R.fail(ResultCode.WEBSOCKET_MESSAGE_FAIL);
                    }
                }catch (ServiceInternalException e){
                    if(e.getResultCode().getCode()==ResultCode.USER_SOCIAL_BE_IGNORED.getCode()){
                        EventDTO<Long> event = EventDTO.buildPersonal(EventEnum.EVENT_CONSPICUOUS_NOTIFY,"已被对方屏蔽",hostId,peerId);
                        userService.sendPersonalEvent(hostId,event);
                    }else if(e.getResultCode().getCode()==ResultCode.USER_SOCIAL_BE_BLACKED.getCode()){
                        EventDTO<Long> event = EventDTO.buildPersonal(EventEnum.EVENT_CONSPICUOUS_NOTIFY,"已被对方拉黑",hostId,peerId);
                        userService.sendPersonalEvent(hostId,event);
                    }
                    return R.fail(ResultCode.WEBSOCKET_MESSAGE_FAIL,e.getMessage());
                }

            }else{
                return R.fail(ResultCode.API_VALIDATION_ERROR);
            }
        }else{
            return R.fail(ResultCode.AUTH_NEED_LOGIN);
        }

    }

    @SubscribeMapping({"/historyMessage/{peerId}/{count}"})
    public R<List<MessageVO>> getHistoryMessage(@DestinationVariable Long peerId, @DestinationVariable String count, OAuth2Authentication auth2Authentication) {
        log.info("getHistoryMessage:{},count:{}",peerId,count);

        if (auth2Authentication!=null) {
         Long hostId =   SecurityUtils.getUser(auth2Authentication).getId();
//            String hostId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            if (hostId> 0 &&peerId!=null&&StringUtils.isNumeric(count)){
                Integer dayCount = Integer.valueOf(count);
                chatService.changeUnRead(peerId, ContainerEnum.PERSON.getCode(),hostId,"clear");
                List<MessageVO> messageVOList = messageService.getMessageVOByIdAndDayCount(hostId,peerId,dayCount,ContainerEnum.PERSON.getDesc());
                if(messageVOList!=null){
                    asyncJobService.sendRecentListNotify(hostId,DateUtils.getCurrentDateTime());
                    return R.success(messageVOList);
                }else{
                    return R.fail(ResultCode.WEBSOCKET_MESSAGE_FAIL);
                }
            }else{
                return R.fail(ResultCode.API_VALIDATION_ERROR);
            }
        }else{
            return R.fail(ResultCode.AUTH_NEED_LOGIN);
        }
    }

}
