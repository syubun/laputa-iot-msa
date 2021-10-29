package com.laputa.iot.upms.service.impl;


import com.laputa.iot.upms.service.InitSqlService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Slf4j
@Service
public class InitSqlServiceImpl implements InitSqlService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void initSql() {
        try {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            ClassPathResource resource = new ClassPathResource("initsql/laputa_iot_upms.sql");
            Connection conn = sqlSession.getConnection();
            EncodedResource er = new EncodedResource(resource, "utf-8");
            ScriptUtils.executeSqlScript(conn, er);
        } catch (Exception e) {
            log.error("执行初始化sql失败", e);
        }
        log.info("成功初始化数据库");
    }
}
