package com.laputa.iot.msg.service.impl;

import com.laputa.iot.common.core.constant.CommonConstants;
import com.laputa.iot.common.core.util.DateUtils;
import com.laputa.iot.common.data.utils.RedisUtils;
import com.laputa.iot.msg.api.enums.ContainerEnum;
import com.laputa.iot.msg.api.vo.MessageVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageServiceImpl {

    @Autowired
    private RedisUtils redisUtils;
    // watcher只存在30s，过期说明客户端没在监听，离线了
    private int REDIS_WATCHER_EXPIRED = 30;
    private String REDIS_WATCHER = "chat_watcher_";
    private String REDIS_CHAT_MESSAGE = "chat_message_";


    public boolean insertTodayMessage(MessageVO message) {
        return  false;
    }

    public List<MessageVO> getMessageVOByIdAndDayCount(Long userId, Long peerId, Integer count, String type){
        if(userId>0 &&peerId > 0 &&count!=null&&type!=null) {
            String label=null;
            if (ContainerEnum.GROUP.getDesc().equals(type)) {
                label = String.valueOf(peerId);
            }else{
                if (userId> peerId) {
                    label = userId + "_" + peerId;
                } else {
                    label = peerId + "_" + userId;
                }
            }
            Date startDate = DateUtils.getDayStartTime(DateUtils.getCurrentDateTime());
            List<MessageVO> messageVOList = new ArrayList<>();
            int allowDay = CommonConstants.CHAT_MESSAGE_QUERY_ALLOW;
            if(count>allowDay){
                count=allowDay;
            }
            while (allowDay>=count&&count>0){
                // 获取发送时间当天起始时间
                String messageStartDay = DateUtils.date2Str(startDate,DateUtils.DEFAULT_DATE_FORMAT);
                if (redisUtils.hasKey(REDIS_CHAT_MESSAGE + messageStartDay)) {
                    HashMap<String, ArrayList<MessageVO>> messageMap = (HashMap<String, ArrayList<MessageVO>>) redisUtils.get(REDIS_CHAT_MESSAGE + messageStartDay);
                    if (messageMap.containsKey(label)) {
                        messageVOList.addAll(messageMap.get(label));
                    }
                }else{
                    //Todo:redis没有，说明在数据库中，要从数据库中获取。
                }
                startDate = DateUtils.addMinutes(startDate,-1440l);
                count--;
            }
            // 降序排列，时间越近的，下标越小
            messageVOList.sort(new Comparator<MessageVO>() {
                @Override
                public int compare(MessageVO o1, MessageVO o2) {
                    if (o2.getCreateTime().before(o1.getCreateTime())){
                        return -1;
                    }else if (o2.getCreateTime().equals(o1.getCreateTime())){
                        return 0;
                    }else{
                        return 1;
                    }
                }
            });
            return messageVOList;
        }else {
            return null;
        }
    }
}
