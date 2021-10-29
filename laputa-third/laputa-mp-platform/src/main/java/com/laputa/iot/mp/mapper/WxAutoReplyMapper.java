package com.laputa.iot.mp.mapper;

import com.laputa.iot.common.data.datascope.LaputaBaseMapper;
import com.laputa.iot.mp.entity.WxAutoReply;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消息自动回复
 *
 * @author JL
 * @date 2019-04-18 15:40:39
 */
@Mapper
public interface WxAutoReplyMapper extends LaputaBaseMapper<WxAutoReply> {

}
