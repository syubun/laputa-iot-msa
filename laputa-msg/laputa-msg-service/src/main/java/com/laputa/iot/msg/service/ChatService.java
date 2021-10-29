package com.laputa.iot.msg.service;


import com.laputa.iot.common.core.exception.ServiceInternalException;
import com.laputa.iot.msg.api.dto.WatcherDTO;
import com.laputa.iot.msg.api.vo.MessageVO;

/**
 * @ClassName ChatService
 * @Description
 * @Author Sommer Jiang
 * @Date 2021/6/16 10:24
 * @Version 1.0.0
 **/

public interface ChatService {
    /**
     * 更新观察者
     * @param watcher
     * @return
     */
    Boolean upInsertWatcher(WatcherDTO watcher);


    Boolean isWatchingMe(Long myId,Long peerId,String peerType);

    /**
     * 发送个人信息
     * @param peerId
     * @param senderId
     * @param message
     * @return
     * @throws ServiceInternalException
     */
    Boolean sendPersonalMessage(Long peerId,Long senderId, MessageVO message) throws ServiceInternalException;

    Integer changeUnRead(Long senderId,Integer senderType,Long receiverId,String action);
}
