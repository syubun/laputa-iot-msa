package com.laputa.iot.org.api.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.laputa.iot.common.core.base.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Comment:部门
 * @author sommer.jiang
 * @Create Date 2021年3月11日
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "org_department")
@NoArgsConstructor
public class Department extends BaseEntity<Long> {

	/**
	 * 公司id
	 */
	private Long companyId;
	/*  名称  20 */
	private String name;

	private String code;
	/* 备注 80 */
	private String note;
	/* 父id 32 */
	private Long pid;
	/**排序号*/
	private Integer orderNo;

/*领导人Code*/
	private String leaderCode;
	//父亲code
	@TableField(exist = false)
	private String pcode;
	//公司的code
	@TableField(exist = false)
	private String companyCode;
	//部门领导
	@TableField(exist = false)
	private String leaderName;
	/**
	 * 查询条件 - 【临时属性】
	 */
	@TableField(exist = false)
	private List<String> companyIds;
	@TableField(exist = false)
	private String companyName;


	@Builder
	public Department(Long id,
					  LocalDateTime createTime, Long createUser, LocalDateTime updateTime,
					  Long updateUser, Long companyId,
					  String name, String code, String note, Long pid, Integer orderNo,
					   String pcode, String companyCode) {
		super(id, createTime, createUser, updateTime, updateUser);
		this.companyId = companyId;
		this.name = name;
		this.code = code;
		this.note = note;
		this.pid = pid;
		this.orderNo = orderNo;
		this.pcode = pcode;
		this.companyCode = companyCode;
	}
}
