package com.laputa.iot.msg.api.dto;


import com.laputa.iot.common.core.util.DateUtils;
import com.laputa.iot.msg.api.enums.EventEnum;
import com.laputa.iot.msg.api.vo.Shouting;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Message
 * @Description
 * @Author Sommer Jiang
 * @Date 2021/6/7 19:57
 * @Version 1.0.0
 **/
@Data
public class EventDTO<T> extends Shouting implements Serializable {
    private String code;
    private T data;
    private String message;

    public EventDTO(Long to, String method, String code, String message, T data) {
        super(to,"event", DateUtils.getCurrentDateTime(),method);
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> EventDTO<T> build(EventEnum eventType, Long peerId, String method, T data) {
        EventDTO event = new EventDTO(peerId,method,eventType.getCode(),eventType.getMsg(),data);
        return event;
    }

    public static <T> EventDTO<T> buildBroadCast(EventEnum eventType, Long roomId, T data) {
        EventDTO event = new EventDTO(roomId,"broadcast",eventType.getCode(),eventType.getMsg(),data);
        return event;
    }

    public static <T> EventDTO<T> buildPersonal(EventEnum eventType, Long peerId, T data) {
        EventDTO event = new EventDTO(peerId,"personal",eventType.getCode(),eventType.getMsg(),data);
        return event;
    }

    public static <T> EventDTO<T> buildPersonal(EventEnum eventType, String errorMsg, Long peerId, T data) {
        EventDTO event = new EventDTO(peerId,"personal",eventType.getCode(),errorMsg,data);
        return event;
    }
}
