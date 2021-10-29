/*
 *    Copyright (c) 2018-2025, Laputa IOT All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the www.laputa-iot.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: SommerJiang (sommer_jiang@163.com)
 */

package com.laputa.iot.common.security.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.laputa.iot.common.core.constant.CommonConstants;
import com.laputa.iot.common.security.exception.LaputaAuth2Exception;
import lombok.SneakyThrows;

/**
 * @author sommer.jiang
 * @date 2018/11/16
 * <p>
 * OAuth2 异常格式化
 */
public class LaputaAuth2ExceptionSerializer extends StdSerializer<LaputaAuth2Exception> {

	public LaputaAuth2ExceptionSerializer() {
		super(LaputaAuth2Exception.class);
	}

	@Override
	@SneakyThrows
	public void serialize(LaputaAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
		gen.writeStartObject();
		gen.writeObjectField("code", CommonConstants.FAIL);
		gen.writeStringField("msg", value.getMessage());
		gen.writeStringField("data", value.getErrorCode());
		gen.writeEndObject();
	}

}
