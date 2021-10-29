package com.laputa.iot.common.sequence.range.impl.name;

import cn.hutool.core.date.DateUtil;
import com.laputa.iot.common.sequence.range.BizName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author sommer.jiang
 * @date 2019-05-26
 * <p>
 * 根据时间重置bizname
 */
@NoArgsConstructor
@AllArgsConstructor
public class DateBizName implements BizName {

	private String bizName;

	/**
	 * 生成空间名称
	 */
	@Override
	public String create() {
		return bizName + DateUtil.today();
	}

}