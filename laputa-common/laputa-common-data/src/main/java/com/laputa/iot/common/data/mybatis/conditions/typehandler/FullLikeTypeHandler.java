package com.laputa.iot.common.data.mybatis.conditions.typehandler;

import org.apache.ibatis.type.Alias;

/**
 * 仅仅用于like查询
 *
 */
@Alias("fullLike")
public class FullLikeTypeHandler extends BaseLikeTypeHandler {
    public FullLikeTypeHandler() {
        super(true, true);
    }
}
