package com.laputa.iot.msg.api.vo;

import com.laputa.iot.common.core.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName Message
 * @Description
 * @Author Sommer Jiang
 * @Date 2021/6/7 19:57
 * @Version 1.0.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageVO extends Shouting implements Serializable {
    private Long from;
    private String message;
    private String status;

    public MessageVO(Long from, Long to, String message, String method) {
        super(to,"message", DateUtils.getCurrentDateTime(),method);
        this.from = from;
        this.message = message;
    }

    public boolean equals(MessageVO other){
        if(other!=null){
            if(this.getFrom()!=null&&other.getFrom()!=null&&this.getFrom().equals(other.getFrom())){
                if(this.getTo()!=null&&other.getTo()!=null&&this.getTo().equals(other.getTo())){
                    if(this.getCreateTime()!=null&&other.getCreateTime()!=null&&this.getCreateTime().equals(other.getCreateTime())){
                        if(this.getMessage()!=null&&other.getMessage()!=null&&this.getMessage().trim().equals(other.getMessage().trim())){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
