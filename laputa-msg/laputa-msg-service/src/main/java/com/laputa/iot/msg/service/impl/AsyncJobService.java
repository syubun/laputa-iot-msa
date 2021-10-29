package com.laputa.iot.msg.service.impl;

import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.core.base.result.ResultCode;
import com.laputa.iot.msg.api.dto.EventDTO;
import com.laputa.iot.msg.api.enums.EventEnum;
import com.laputa.iot.msg.api.vo.RelationVO;
import com.laputa.iot.msg.service.ImUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AsyncJobService {

    public void sendMsgSubProcess(Long from, Integer code, Long to, Integer codeByDesc, Date createTime, boolean b) {
    }

    @Autowired
    ImUserService userService;



    @Async("taskExecutor")
    public Boolean sendRecentListNotify(Long userId, Date gmtMsgDate){
        R<List<RelationVO>> receiverResult =  userService.getUserRecentRelation(userId);
        if(receiverResult!=null&&receiverResult.getCode()== ResultCode.SUCCESS.getCode()){
            List<RelationVO> receiverRelationVOList = receiverResult.getData();
            EventDTO<List<RelationVO>> event = EventDTO.buildPersonal(EventEnum.EVENT_RECENT_CHANGED, userId, receiverRelationVOList);
            event.setCreateTime(gmtMsgDate);
            userService.sendPersonalEvent(userId, event);
            return true;
        }else{
            log.info("process done ,not notify receiver:{}",userId);
            return true;
        }
    }
}
