package com.laputa.iot.common.sequence.builder;

import com.laputa.iot.common.sequence.sequence.impl.SnowflakeSequence;
import com.laputa.iot.common.sequence.sequence.Sequence;

/**
 * 基于雪花算法，序列号生成器构建者
 *
 * @author xuan on 2018/5/30.
 */
public class SnowflakeSeqBuilder implements SeqBuilder {

	/**
	 * 数据中心ID，值的范围在[0,31]之间，一般可以设置机房的IDC[必选]
	 */
	private long datacenterId;

	/**
	 * 工作机器ID，值的范围在[0,31]之间，一般可以设置机器编号[必选]
	 */
	private long workerId;

	public static SnowflakeSeqBuilder create() {
		SnowflakeSeqBuilder builder = new SnowflakeSeqBuilder();
		return builder;
	}

	@Override
	public Sequence build() {
		SnowflakeSequence sequence = new SnowflakeSequence();
		sequence.setDatacenterId(this.datacenterId);
		sequence.setWorkerId(this.workerId);
		return sequence;
	}

	public SnowflakeSeqBuilder datacenterId(long datacenterId) {
		this.datacenterId = datacenterId;
		return this;
	}

	public SnowflakeSeqBuilder workerId(long workerId) {
		this.workerId = workerId;
		return this;
	}

}
