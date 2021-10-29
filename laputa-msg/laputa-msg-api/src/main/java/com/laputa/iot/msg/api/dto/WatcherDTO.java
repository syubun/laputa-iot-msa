package com.laputa.iot.msg.api.dto;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName Watcher
 * @Description
 * @Author Sommer Jiang
 * @Date 2021/6/16 10:21
 * @Version 1.0.0
 **/
@Data
public class WatcherDTO {
    private Long watcherId;
    private Long peerId;
    private String peerType;
    private String action;
    private Date createTime;
}
