package com.laputa.iot.msg.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Shouting
 * @Description
 * @Author Sommer Jiang
 * @Date 2021/6/8 18:09
 * @Version 1.0.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shouting implements Serializable {

    private static final long serialVersionUID = 6102411440108234989L;
    private String type;
    private String peerType;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String method;
    private Long to;

    public Shouting(Long to, String type, Date gmtCreate, String method) {
        this.to = to;
        this.type = type;
        this.createTime = gmtCreate;
        this.method = method;
    }

}
