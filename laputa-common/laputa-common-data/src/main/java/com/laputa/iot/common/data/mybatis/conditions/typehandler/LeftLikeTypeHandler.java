package com.laputa.iot.common.data.mybatis.conditions.typehandler;


import org.apache.ibatis.type.Alias;

/**
 * 仅仅用于like查询
 *
 */
@Alias("leftLike")
public class LeftLikeTypeHandler extends BaseLikeTypeHandler {
    public LeftLikeTypeHandler() {
        super(true, false);
    }
}
