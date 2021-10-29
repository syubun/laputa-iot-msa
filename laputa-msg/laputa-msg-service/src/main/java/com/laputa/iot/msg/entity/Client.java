package com.laputa.iot.msg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.websocket.Session;
import java.io.Serializable;

/**
 * @Author sommer.jiang
 * @Email sommer_jiang@163.com
 * @Date 2019/3/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {

    private static final long serialVersionUID = 8957107006902627635L;

    private String userName;

    private Session session;



}
