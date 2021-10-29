package com.laputa.iot.file.service.impl;

import com.laputa.iot.codegen.LaputaCodeGenApplication;
import com.laputa.iot.codegen.entity.GenConfig;
import com.laputa.iot.codegen.entity.GenDatasourceConf;
import com.laputa.iot.codegen.service.GenDatasourceConfService;
import com.laputa.iot.codegen.service.impl.GeneratorServiceImpl;
import com.laputa.iot.common.core.util.FileUtil;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = LaputaCodeGenApplication.class)
class GeneratorServiceImplTest {

    @Autowired
    GeneratorServiceImpl generatorService;
//
    @Autowired
     GenDatasourceConfService datasourceConfService;
    @Autowired
    StringEncryptor stringEncryptor;
//    @Test
//    void getPage() {
//    }

    @Test
    void previewCode() {

        List<GenDatasourceConf> list = datasourceConfService.list();
//
        list.stream().forEach(System.out::println);
//        Map<String, String> stringStringMap = generatorService.previewCode(null);
//        System.out.println(stringStringMap.toString());
    }

    @Test
    void generatorCode() {
        GenConfig genConfig = new GenConfig();
        genConfig.setDsName("l_2");
        genConfig.setAuthor("sommer.jiang");
        genConfig.setEntityType(0);
        genConfig.setPackageName("com.laputa.iot");
        genConfig.setComments("文件");
        genConfig.setTableName("sys_file");
        genConfig.setTablePrefix("sys");
        genConfig.setModuleName("manage");
        byte[] data =  generatorService.generatorCode(genConfig);
        FileUtil.saveFile("E:\\LAPUTA-IOT-PLATFORM","sys_file.zip",data);
    }
}