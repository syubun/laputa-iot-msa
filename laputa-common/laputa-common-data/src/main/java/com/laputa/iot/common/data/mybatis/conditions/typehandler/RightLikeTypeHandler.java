package com.laputa.iot.common.data.mybatis.conditions.typehandler;


import org.apache.ibatis.type.Alias;

/**
 * 仅仅用于like查询
 *
 */
@Alias("rightLike")
public class RightLikeTypeHandler extends BaseLikeTypeHandler {
    public RightLikeTypeHandler() {
        super(false, true);
    }
}

