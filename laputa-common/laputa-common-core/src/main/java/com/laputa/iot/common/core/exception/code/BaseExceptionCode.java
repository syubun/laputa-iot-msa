package com.laputa.iot.common.core.exception.code;

/**
 *
 */
public interface BaseExceptionCode {
    /**
     * 异常编码
     *
     * @return
     */
    int getCode();

    /**
     * 异常消息
     * @return
     */
    String getMsg();
}
