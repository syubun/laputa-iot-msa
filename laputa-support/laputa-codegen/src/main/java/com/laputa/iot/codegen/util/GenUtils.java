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

package com.laputa.iot.codegen.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.base.CaseFormat;
import com.laputa.iot.codegen.mapper.GenDatasourceConfMapper;
import com.laputa.iot.codegen.entity.GenConfig;
import com.laputa.iot.codegen.entity.GenFormConf;
import com.laputa.iot.codegen.mapper.GeneratorMapper;
import com.laputa.iot.codegen.entity.ColumnEntity;
import com.laputa.iot.codegen.entity.GenDatasourceConf;
import com.laputa.iot.codegen.entity.TableEntity;
import com.laputa.iot.common.core.constant.CommonConstants;
import com.laputa.iot.common.core.exception.CheckedException;
import com.laputa.iot.common.core.util.SpringContextHolder;
import com.laputa.iot.common.datasource.util.DsJdbcUrlEnum;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.context.ApplicationContext;

/**
 * 代码生成器 工具类
 *
 * @author sommer.jiang
 * @date 2018-07-30
 */
@Slf4j
@UtilityClass
public class GenUtils {

	public final String CRUD_PREFIX = "export const tableOption =";

	private final String ENTITY_JAVA_VM = "Entity.java.vm";

	private final String MODEL_JAVA_VM = "Model.java.vm";


	private final String MAPPER_JAVA_VM = "Mapper.java.vm";

	private final String SERVICE_JAVA_VM = "Service.java.vm";

	private final String SERVICE_IMPL_JAVA_VM = "ServiceImpl.java.vm";

	private final String CONTROLLER_JAVA_VM = "Controller.java.vm";

	private final String MAPPER_XML_VM = "Mapper.xml.vm";

	private final String MENU_SQL_VM = "menu.sql.vm";

	private final String REMOTE_SERVICE_JAVA_VM = "Remote.java.vm";
	private final String DTO_JAVA_VM = "Dto.java.vm";
	private final String VO_JAVA_VM = "VO.java.vm";
//	private final String AVUE_CRUD_JS_VM = "avue/crud.js.vm";

	private final String TS_CRUD_TS_VM = "ts/api.ts.vm";
	private final String TS_INDEX_VUE_VM = "ts/index.vue.vm";
	private final String TS_DATA_TS_VM = "ts/data.ts.vm";
	private final String TS_DRAWER_VUE_VM = "ts/drawer.vue.vm";
	private final String TS_MODEL_TS_VM = "ts/model.ts.vm";
	private final String TS_MODAL_VUE_VM = "ts/modal.vue.vm";


	private String  getTSType(String type){
		if (type.equalsIgnoreCase("int")
				||type.equalsIgnoreCase("Integer")
				||type.equalsIgnoreCase("double")
				||type.equalsIgnoreCase("long")
				||type.equalsIgnoreCase("boolean")
				||type.equalsIgnoreCase("bool")
		)
			return "number";
		if (type.equalsIgnoreCase("String"))
			return "string";
		return "any";
	}

	/**
	 * 配置
	 * @param config
	 * @return
	 */
	private List<String> getTemplates(GenConfig config) {
		List<String> templates = new ArrayList<>();
		templates.add("template/Mapper.java.vm");
		templates.add("template/Mapper.xml.vm");
		templates.add("template/Service.java.vm");
		templates.add("template/ServiceImpl.java.vm");
		templates.add("template/Controller.java.vm");
		templates.add("template/menu.sql.vm");
		templates.add("template/ts/api.ts.vm");
		templates.add("template/ts/index.vue.vm");
		templates.add("template/ts/data.ts.vm");
		templates.add("template/ts/drawer.vue.vm");
		templates.add("template/ts/model.ts.vm");
		templates.add("template/ts/modal.vue.vm");
		templates.add("template/Remote.java.vm");
		templates.add("template/Dto.java.vm");
		templates.add("template/VO.java.vm");
		if (config.getEntityType().equals("1")) {
			templates.add("template/Model.java.vm");

		}
		else {
			templates.add("template/Entity.java.vm");
		}


		return templates;
	}

	/**
	 * 生成代码
	 * @return
	 */
	@SneakyThrows
	public Map<String, String> generatorCode(GenConfig genConfig, Map<String, String> table,
			List<Map<String, String>> columns, ZipOutputStream zip, GenFormConf formConf) {
		// 配置信息
		Configuration config = getConfig();
		boolean hasBigDecimal = false;
		// 表信息
		TableEntity tableEntity = new TableEntity();
		tableEntity.setTableName(table.get("tableName"));
		tableEntity.setEntityType(table.get("entityType"));
		if (StrUtil.isNotBlank(genConfig.getComments())) {
			tableEntity.setComments(genConfig.getComments());
		}
		else {
			tableEntity.setComments(table.get("tableComment"));
		}

		tableEntity.setDbType(table.get("dbType"));

		String tablePrefix;
		if (StrUtil.isNotBlank(genConfig.getTablePrefix())) {
			tablePrefix = genConfig.getTablePrefix();
		}
		else {
			tablePrefix = config.getString("tablePrefix");
		}

		// 表名转换成Java类名
		String className = tableToJava(tableEntity.getTableName(), tablePrefix);
		tableEntity.setClassName(className);
		tableEntity.setCaseClassName(className);
		tableEntity.setLowerClassName(StringUtils.uncapitalize(className));
		tableEntity.setUpperClassName(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE,className));

		// 获取需要在swagger文档中隐藏的属性字段
		List<Object> hiddenColumns = config.getList("hiddenColumn");
		// 列信息
		List<ColumnEntity> columnList = new ArrayList<>();
		for (Map<String, String> column : columns) {
			ColumnEntity columnEntity = new ColumnEntity();
			columnEntity.setColumnName(column.get("columnName"));
			columnEntity.setDataType(column.get("dataType"));
			columnEntity.setExtra(column.get("extra"));
			columnEntity.setNullable("NO".equals(column.get("isNullable")));
			columnEntity.setColumnType(column.get("columnType"));
			// 隐藏不需要的在接口文档中展示的字段
			if (hiddenColumns.contains(column.get("columnName"))) {
				columnEntity.setHidden(Boolean.TRUE);
			}
			else {
				columnEntity.setHidden(Boolean.FALSE);
			}
			// 列名转换成Java属性名
			String attrName = columnToJava(columnEntity.getColumnName());
			columnEntity.setCaseAttrName(attrName);
			columnEntity.setLowerAttrName(StringUtils.uncapitalize(attrName));

			// 判断注释是否为空
			if (StrUtil.isNotBlank(column.get("comments"))) {
				// 注意去除换行符号
				columnEntity.setComments(StrUtil.removeAllLineBreaks(column.get("comments")));
			}
			else {
				columnEntity.setComments(columnEntity.getLowerAttrName());
			}
			columnEntity.setIsBase(false);
			if("id".equals(columnEntity.getColumnName())
					||"deleted".equals(columnEntity.getColumnName())
					||"version".equals(columnEntity.getColumnName())
					||"create_user".equals(columnEntity.getColumnName())
					||"create_time".equals(columnEntity.getColumnName())
					||"update_user".equals(columnEntity.getColumnName())
					||"update_time".equals(columnEntity.getColumnName())
					||"tenant_id".equals(columnEntity.getColumnName())
			){
				columnEntity.setIsBase(true);
			}

			// 列的数据类型，转换成Java类型
			String dataType = StrUtil.subBefore(columnEntity.getDataType(), "(", false);
			String attrType = config.getString(dataType, "unknowType");
			columnEntity.setAttrType(attrType);
			if (!hasBigDecimal && "BigDecimal".equals(attrType)) {
				hasBigDecimal = true;
			}
			// 是否主键
			if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableEntity.getPk() == null) {
				tableEntity.setPk(columnEntity);
			}

			String tsType = getTSType(dataType);
			columnEntity.setTsType(tsType);
			if(dataType.equalsIgnoreCase("varchar")){
				String value = column.get("columnType");
				String size=value.substring(value.indexOf("(")+1,value.indexOf(")"));
				columnEntity.setSize(size);
			}else{
				columnEntity.setSize("0");
			}

			columnList.add(columnEntity);
		}
		tableEntity.setColumns(columnList);

		// 没主键，则第一个字段为主键
		if (tableEntity.getPk() == null) {
			tableEntity.setPk(tableEntity.getColumns().get(0));
		}

		// 封装模板数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("dbType", tableEntity.getDbType());
		map.put("tableName", tableEntity.getTableName());
		map.put("pk", tableEntity.getPk());
		map.put("caseClassName", tableEntity.getCaseClassName());
		map.put("classname", tableEntity.getLowerClassName());
		map.put("className", tableEntity.getClassName());
		map.put("pathName", tableEntity.getLowerClassName().toLowerCase());
		map.put("columns", tableEntity.getColumns());
		map.put("upperClassName", tableEntity.getUpperClassName());
		map.put("hasBigDecimal", hasBigDecimal);
		map.put("datetime", DateUtil.now());
//		map.put("UpperClassName", tableEntity.get());

		if (StrUtil.isNotBlank(genConfig.getComments())) {
			map.put("comments", genConfig.getComments());
		}
		else {
			map.put("comments", tableEntity.getComments());
		}

		if (StrUtil.isNotBlank(genConfig.getAuthor())) {
			map.put("author", genConfig.getAuthor());
		}
		else {
			map.put("author", config.getString("author"));
		}

		if (StrUtil.isNotBlank(genConfig.getModuleName())) {
			map.put("moduleName", genConfig.getModuleName());
		}
		else {
			map.put("moduleName", config.getString("moduleName"));
		}

		if (StrUtil.isNotBlank(genConfig.getPackageName())) {
			map.put("package", genConfig.getPackageName());
			map.put("mainPath", genConfig.getPackageName());
		}
		else {
			map.put("package", config.getString("package"));
			map.put("mainPath", config.getString("mainPath"));
		}

		// 渲染数据
		return renderData(genConfig, zip, formConf, tableEntity, map);
	}

	public static void main(String[] args) {
		System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "UserName"));//user_name
	}

	/**
	 * 渲染数据
	 * @param genConfig 配置信息
	 * @param zip 流 （为空，直接返回Map）
	 * @param formConf 表单信息
	 * @param tableEntity 表基本信息
	 * @param map 模板参数
	 * @return map key-filename value-contents
	 * @throws IOException
	 */
	private Map<String, String> renderData(GenConfig genConfig, ZipOutputStream zip, GenFormConf formConf,
			TableEntity tableEntity, Map<String, Object> map) throws IOException {
		// 设置velocity资源加载器
		Properties prop = new Properties();
		prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		Velocity.init(prop);
		VelocityContext context = new VelocityContext(map);

		// 获取模板列表
		List<String> templates = getTemplates(genConfig);
		Map<String, String> resultMap = new HashMap<>(8);

		for (String template : templates) {
			// 如果是crud
//			if (template.contains(AVUE_CRUD_JS_VM) && formConf != null) {
//
//				String fileName = getFileName(template, tableEntity.getCaseClassName(), map.get("package").toString(),
//						map.get("moduleName").toString());
//				String contents = CRUD_PREFIX + formConf.getFormInfo();
//
//				if (zip != null) {
//					zip.putNextEntry(new ZipEntry(Objects.requireNonNull(fileName)));
//					IoUtil.write(zip, StandardCharsets.UTF_8, false, contents);
//					zip.closeEntry();
//				}
//
//				resultMap.put(template, contents);
//				continue;
//			}

			// 渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, CharsetUtil.UTF_8);
			tpl.merge(context, sw);

			// 添加到zip
			String fileName = getFileName(template, tableEntity.getCaseClassName(), map.get("package").toString(),
					map.get("moduleName").toString());

			if (zip != null) {
				zip.putNextEntry(new ZipEntry(Objects.requireNonNull(fileName)));
				IoUtil.write(zip, StandardCharsets.UTF_8, false, sw.toString());
				IoUtil.close(sw);
				zip.closeEntry();
			}
			resultMap.put(template, sw.toString());
		}

		return resultMap;
	}

	/**
	 * 列名转换成Java属性名
	 */
	public String columnToJava(String columnName) {
		return WordUtils.capitalizeFully(columnName, new char[] { '_' }).replace("_", "");
	}

	/**
	 * 表名转换成Java类名
	 */
	private String tableToJava(String tableName, String tablePrefix) {
		if (StringUtils.isNotBlank(tablePrefix)) {
			tableName = tableName.replaceFirst(tablePrefix, "");
		}
		return columnToJava(tableName);
	}

	/**
	 * 获取配置信息
	 */
	public Configuration getConfig() {
		try {
			return new PropertiesConfiguration("generator.properties");
		}
		catch (ConfigurationException e) {
			throw new CheckedException("获取配置文件失败，", e);
		}
	}

	/**
	 * 获取文件名
	 */
	private String getFileName(String template, String className, String packageName, String moduleName) {
		String packagePath = CommonConstants.BACK_SERVICE_PROJECT + moduleName + CommonConstants.BACK_SERVICE+ File.separator + "src" + File.separator + "main"
				+ File.separator + "java" + File.separator;

		String apipackagePath = CommonConstants.BACK_SERVICE_PROJECT  + moduleName + CommonConstants.BACK_API_PROJECT + File.separator + "src" + File.separator + "main"
				+ File.separator + "java" + File.separator;

		if (StringUtils.isNotBlank(packageName)) {
			packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
		}

		if (StringUtils.isNotBlank(packageName)) {
			apipackagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
		}

		if (template.contains(ENTITY_JAVA_VM)) {
			return apipackagePath +"api" + File.separator + "entity" + File.separator + className + ".java";
		}

		if (template.contains(MODEL_JAVA_VM)) {
			return apipackagePath +"api" + File.separator + "entity" + File.separator + className + ".java";
		}

		if (template.contains(MAPPER_JAVA_VM)) {
			return packagePath + "mapper" + File.separator + className + "Mapper.java";
		}

		if (template.contains(SERVICE_JAVA_VM)) {
			return packagePath + "service" + File.separator + className + "Service.java";
		}

		if (template.contains(SERVICE_IMPL_JAVA_VM)) {
			return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
		}

		if (template.contains(CONTROLLER_JAVA_VM)) {
			return packagePath + "controller" + File.separator + className + "Controller.java";
		}

		if (template.contains(REMOTE_SERVICE_JAVA_VM) ) {
			return apipackagePath +"api" + File.separator + "remote" + File.separator + className + "RemoteService.java";
		}

		if (template.contains(DTO_JAVA_VM) ) {
			return apipackagePath +"api" + File.separator + "dto" + File.separator + className + "DTO.java";
		}

		if (template.contains(VO_JAVA_VM) ) {
			return apipackagePath +"api" + File.separator + "vo" + File.separator + className + "VO.java";
		}

		if (template.contains(MAPPER_XML_VM)) {
			return CommonConstants.BACK_SERVICE_PROJECT + moduleName + CommonConstants.BACK_SERVICE + File.separator + "src" + File.separator + "main" + File.separator
					+ "resources" + File.separator + "mapper" + File.separator + className + "Mapper.xml";
		}

		if (template.contains(MENU_SQL_VM)) {
			return className.toLowerCase() + "_menu.sql";
		}



		if (template.contains(TS_INDEX_VUE_VM) ) {
			return CommonConstants.FRONT_END_PROJECT + File.separator + "src" + File.separator + "views"
					+ File.separator + moduleName + File.separator + className.toLowerCase() + File.separator
					+ "index.vue";
		}

		if (template.contains(TS_DATA_TS_VM) ) {
			return CommonConstants.FRONT_END_PROJECT + File.separator + "src" + File.separator + "views"
					+ File.separator + moduleName + File.separator + className.toLowerCase() + File.separator
					+ className.toLowerCase() +".data.ts";
		}

		if (template.contains(TS_MODEL_TS_VM) ) {
			return CommonConstants.FRONT_END_PROJECT + File.separator + "src" + File.separator + "api" + File.separator
					+ moduleName  + File.separator + "model"  + File.separator
					+ className + "Model.ts";
		}

		if (template.contains(TS_CRUD_TS_VM)) {
			return CommonConstants.FRONT_END_PROJECT + File.separator + "src" + File.separator + "api" + File.separator
					+ moduleName  + File.separator
					+ className.toLowerCase() + ".ts";
		}

		if (template.contains(TS_DRAWER_VUE_VM) ) {
			return CommonConstants.FRONT_END_PROJECT + File.separator + "src" + File.separator + "views"
					+ File.separator + moduleName + File.separator + className.toLowerCase() + File.separator
					+ className +"Drawer.vue";
		}

		if (template.contains(TS_MODAL_VUE_VM) ) {
			return CommonConstants.FRONT_END_PROJECT + File.separator + "src" + File.separator + "views"
					+ File.separator + moduleName + File.separator + className.toLowerCase() + File.separator
					+ className +"Modal.vue";
		}




		return null;
	}

	/**
	 * 根据目标数据源名称动态匹配Mapper
	 * @param dsName
	 * @return
	 */
	public GeneratorMapper getMapper(String dsName) {
		// 获取目标数据源数据库类型
		GenDatasourceConfMapper datasourceConfMapper = SpringContextHolder.getBean(GenDatasourceConfMapper.class);
		GenDatasourceConf datasourceConf = datasourceConfMapper
				.selectOne(Wrappers.<GenDatasourceConf>lambdaQuery().eq(GenDatasourceConf::getName, dsName));

		String dbConfType;
		// 默认MYSQL 数据源
		if (datasourceConf == null) {
			dbConfType = DsJdbcUrlEnum.MYSQL.getDbName();
		}
		else {
			dbConfType = datasourceConf.getDsType();
		}

		// 获取全部数据实现
		ApplicationContext context = SpringContextHolder.getApplicationContext();
		Map<String, GeneratorMapper> beansOfType = context.getBeansOfType(GeneratorMapper.class);

		// 根据数据类型选择mapper
		for (String key : beansOfType.keySet()) {
			if (StrUtil.containsIgnoreCase(key, dbConfType)) {
				return beansOfType.get(key);
			}
		}

		throw new IllegalArgumentException("dsName 不合法: " + dsName);
	}

}
