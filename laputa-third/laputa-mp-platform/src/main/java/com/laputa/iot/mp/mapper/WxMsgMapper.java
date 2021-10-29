package com.laputa.iot.mp.mapper;

import com.laputa.iot.common.data.datascope.LaputaBaseMapper;
import com.laputa.iot.mp.entity.WxMsg;
import org.apache.ibatis.annotations.Mapper;

/**
 * 微信消息
 *
 * @author JL
 * @date 2019-05-28 16:12:10
 */
@Mapper
public interface WxMsgMapper extends LaputaBaseMapper<WxMsg> {

}
