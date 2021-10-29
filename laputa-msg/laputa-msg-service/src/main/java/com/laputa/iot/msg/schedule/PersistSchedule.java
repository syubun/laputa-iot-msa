package com.laputa.iot.msg.schedule;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

import com.laputa.iot.common.core.util.DateUtils;
import com.laputa.iot.common.data.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName PersistSchedule
 * @Description
 * @Author Sommer Jiang
 * @Date 2021/6/16 16:25
 * @Version 1.0.0
 **/
@Slf4j
@Component
@EnableScheduling//可以在启动类上注解也可以在当前文件
public class PersistSchedule {
    @Resource
    private RedisUtils redisUtils;


    // watcher只存在30s，过期说明客户端没在监听，离线了
    private int REDIS_WATCHER_EXPIRED = 30;
    private String REDIS_WATCHER = "chat_message_";

    /**
     * fixedDelay单位毫秒 1000=1秒
     */
//    @Scheduled(fixedDelay=600000)
//    private void saveLocal() throws UnsupportedEncodingException {
//        String msgCreate_start = DateUtils.getDayStartTime("yyyy-MM-dd", DateFormatUtils.format(DateUtils.getCurrentDateTime(),"yyyy-MM-dd HH:mm:ss"));
//        if(redisUtils.hasKey(REDIS_WATCHER+msgCreate_start)){
//            Map<String,List<MessageVO>> messageMap = (Map<String,List<MessageVO>>)redisUtils.get(REDIS_WATCHER+msgCreate_start);
//            Map<String, Message> labelMessageMap = new HashMap<>();
//            for (String peerId : messageMap.keySet()) {
//                List<MessageVO> messageVOList = messageMap.get(peerId);
//                for (MessageVO messageVO : messageVOList) {
//                    String label=null;
//                    if(Long.parseLong(messageVO.getFrom())>Long.parseLong(messageVO.getTo())){
//                        label= messageVO.getFrom()+"_"+messageVO.getTo();
//                    }else{
//                        label= messageVO.getTo()+"_"+messageVO.getFrom();
//                    }
//                    if(labelMessageMap.containsKey(label)){
//                        Message message = labelMessageMap.get(label);
//                        if(StringUtils.isNotEmpty(message.getMessage())){
//                            try {
//                                String messageBase64 = MyBase64Utils.springEncodeString(messageVO.getMessage());
//                                long sendTS = messageVO.getGmtCreate().getTime();
//                                String messageFrag = "";
//                                messageFrag="<"+messageVO.getFrom()+"_"+sendTS+":"+messageBase64+">";
//
//                            }catch (UnsupportedEncodingException e){
//
//                            }
//
//                        }
//                    }
//                }
//            }
//        }
//    }

    @Scheduled(fixedDelay=600000)
    private void saveMessageYesterdayLocal() {
     log.info("saveMessageYesterdayLocal" +DateUtils.getCurrentDateTime());
    }
}
