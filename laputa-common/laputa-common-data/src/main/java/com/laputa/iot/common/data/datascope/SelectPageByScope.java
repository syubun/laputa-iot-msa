package com.laputa.iot.common.data.datascope;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @author sommer.jiang
 * @date 2020/4/26
 */
public class SelectPageByScope extends AbstractMethod {

	@Override
	public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
		SqlMethod sqlMethod = SqlMethod.SELECT_PAGE;
		String sql = String.format(sqlMethod.getSql(), this.sqlFirst(), this.sqlSelectColumns(tableInfo, true),
				tableInfo.getTableName(), this.sqlWhereEntityWrapper(true, tableInfo), this.sqlOrderBy(tableInfo),
				this.sqlComment());
		SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, modelClass);
		return this.addSelectMappedStatementForTable(mapperClass, "selectPageByScope", sqlSource, tableInfo);
	}

}
