/*
 *
 *      Copyright (c) 2018-2025, Laputa IOT All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the www.laputa-iot.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: SommerJiang (sommer_jiang@163.com)
 *
 */

package com.laputa.iot.common.core.base.dto;
//import com.google.common.collect.Maps;
import com.laputa.iot.common.core.base.enums.LaputaHttpStatus;
import com.laputa.iot.common.core.base.result.IResultCode;
import com.laputa.iot.common.core.base.result.ResultCode;
import com.laputa.iot.common.core.constant.CommonConstants;
import com.laputa.iot.common.core.exception.BizException;
import com.laputa.iot.common.core.exception.code.BaseExceptionCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author sommer.jiang
 */
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "响应信息主体")
public class R<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String DEF_ERROR_MESSAGE = "系统繁忙，请稍候再试";
	public static final String HYSTRIX_ERROR_MESSAGE = "请求超时，请稍候再试";
	public static final int SUCCESS_CODE = 0;
	public static final int FAIL_CODE = -1;
	public static final int TIMEOUT_CODE = -2;
	/**
	 * 统一参数验证异常
	 */
	public static final int VALID_EX_CODE = -9;
	public static final int OPERATION_EX_CODE = -10;

	@Getter
	@Setter
	@ApiModelProperty(value = "返回标记：成功标记=0，失败标记=1")
	private int code;

	@Getter
	@Setter
	@ApiModelProperty(value = "提示消息")
	private String msg;

	@Getter
	@Setter
	@ApiModelProperty(value = "响应数据")
	private T data;

	@Getter
	@Setter
	@ApiModelProperty(value = "请求路径")
	private String path;
	/**
	 * 附加数据
	 */
	@Getter
	@Setter
	@ApiModelProperty(value = "附加数据")
	private Map<String, Object> extra;

	/**
	 * 响应时间
	 */
	@Getter
	@Setter
	@ApiModelProperty(value = "响应时间戳")
	private long timestamp = System.currentTimeMillis();

	public static <T> R<T> ok() {
		return restResult(null, CommonConstants.SUCCESS, null);
	}

	public static <T> R<T> ok(T data) {
		return restResult(data, CommonConstants.SUCCESS, null);
	}

	public static <T> R<T> ok(T data, String msg) {
		return restResult(data, CommonConstants.SUCCESS, msg);
	}

	public static <T> R<T> failed() {
		return restResult(null, CommonConstants.FAIL, null);
	}

	public static <T> R<T> failed(String msg) {
		return restResult(null, CommonConstants.FAIL, msg);
	}

	public static <T> R<T> failed(T data) {
		return restResult(data, CommonConstants.FAIL, null);
	}

	public static <T> R<T> failed(T data, String msg) {
		return restResult(data, CommonConstants.FAIL, msg);
	}

	public static <E> R<E> result(int code, E data, String msg) {
		return new R<>(code, data, msg);
	}

	public R(int code, T data, String msg) {
		this.code = code;
		this.data = data;
		this.msg = msg;
	}

	/**
	 * 请求成功消息
	 *
	 * @param data 结果
	 * @return RPC调用结果
	 */
	public static <E> R<E> success(E data) {
		return new R<>(SUCCESS_CODE, data, "ok");
	}

	public static R<Boolean> success() {
		return new R<>(SUCCESS_CODE, true, "ok");
	}

	/**
	 * 请求成功方法 ，data返回值，msg提示信息
	 *
	 * @param data 结果
	 * @param msg  消息
	 * @return RPC调用结果
	 */
	public static <E> R<E> success(E data, String msg) {
		return new R<>(SUCCESS_CODE, data, msg);
	}

	/**
	 * 请求失败消息
	 *
	 * @param resultCode IResultCode
	 * @return
	 */
	public static <E> R<E> fail(IResultCode resultCode) {
		return new R<>(resultCode.getCode(), null, (resultCode.getMsg() == null || resultCode.getMsg().isEmpty()) ? DEF_ERROR_MESSAGE : resultCode.getMsg());
	}

	public static <E> R<E> fail(int code, String msg) {
		return new R<>(code, null, (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg);
	}
	public static <E> R<E> fail(String msg) {
		return fail(OPERATION_EX_CODE, msg);
	}

	public static <E> R<E> fail(String msg, Object... args) {
		String message = (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg;
		return new R<>(OPERATION_EX_CODE, null, String.format(message, args));
	}

	public static <E> R<E> fail(BaseExceptionCode exceptionCode) {
		return validFail(exceptionCode);
	}

	public static <E> R<E> fail(BizException exception) {
		if (exception == null) {
			return fail(DEF_ERROR_MESSAGE);
		}
		return new R<>(exception.getCode(), null, exception.getMessage());
	}

	/**
	 * 请求失败消息，根据异常类型，获取不同的提供消息
	 *
	 * @param throwable 异常
	 * @return RPC调用结果
	 */
	public static <E> R<E> fail(Throwable throwable) {
		return fail(FAIL_CODE, throwable != null ? throwable.getMessage() : DEF_ERROR_MESSAGE);
	}

	public static <E> R<E> validFail(String msg) {
		return new R<>(VALID_EX_CODE, null, (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg);
	}

	public static <E> R<E> validFail(String msg, Object... args) {
		String message = (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg;
		return new R<>(VALID_EX_CODE, null, String.format(message, args));
	}

	public static <E> R<E> validFail(BaseExceptionCode exceptionCode) {
		return new R<>(exceptionCode.getCode(), null,
				(exceptionCode.getMsg() == null || exceptionCode.getMsg().isEmpty()) ? DEF_ERROR_MESSAGE : exceptionCode.getMsg());
	}

	public static <E> R<E> timeout() {
		return fail(TIMEOUT_CODE, HYSTRIX_ERROR_MESSAGE);
	}

	public static R<String> fail(ResultCode resultCode, String message) {
		return new R<>(resultCode.getCode(), null, message);
	}
	public static <T> R<T> fail(IResultCode resultCode, String msg) {
		return new R<>(resultCode.getCode(), null, msg);
	}
	/**
	 * 逻辑处理是否成功
	 *
	 * @return 是否成功
	 */
	public Boolean getIsSuccess() {
		return this.code == SUCCESS_CODE || this.code == 200;
	}
	/**
	 * 逻辑处理是否失败
	 *
	 * @return
	 */
	public Boolean getIsError() {
		return !getIsSuccess();
	}

	private static <T> R<T> restResult(T data, int code, String msg) {
		R<T> apiResult = new R<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMsg(msg);
		return apiResult;
	}

	/**
	 * 调用服务的错误
	 * @param serviceName 服务名
	 * @param methodName 方法名
	 * @return 结果视图
	 */
	public static R hystrixError(String serviceName,String methodName) {
		String msg = LaputaHttpStatus.HYSTRIX_ERROR.getMessage().replace("xxx", serviceName).replace("{}", methodName);
		return new R(LaputaHttpStatus.HYSTRIX_ERROR.getCode(),null, msg);
	}

}
