/*
 *    Copyright (c) 2018-2025, Sommer 20200212 All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the laputapdm.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: Sommer (sommer_jiang@163.com)
 */

package com.laputa.iot.common.oss.vo;


import io.minio.StatObjectResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 存储对象的元数据
 *
 * @author sommer
 */
@Data
@AllArgsConstructor
public class MinioObject {
	private String bucketName;
	private String name;
	private ZonedDateTime createdTime;
	private Long length;
	private String etag;
	private String contentType;
	private Map<String, List<String>> httpHeaders;

	public MinioObject(StatObjectResponse os) {
		this.bucketName = os.bucket();
		this.name = os.object();
		this.createdTime = os.lastModified();
		this.length = os.size();
		this.etag = os.etag();
		this.contentType = os.contentType();
		this.httpHeaders = os.headers().toMultimap();
	}

}
