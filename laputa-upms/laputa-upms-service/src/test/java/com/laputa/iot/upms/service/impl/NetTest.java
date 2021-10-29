package com.laputa.iot.upms.service.impl;

import com.laputa.iot.common.core.util.FileUtil;
import lombok.Data;
import lombok.ToString;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class NetTest {

    public static void main(String[] args) throws Exception {

//        byte[] content = FileUtil.getContent("E:\\data\\json\\ion.json");
//        System.out.println(new String(content, StandardCharsets.UTF_8));
//        Icon parse = JSON.parseObject(new String(content, StandardCharsets.UTF_8), Icon.class);
//        System.out.println(parse);
//        Map<String, Object> icons = parse.icons;
//        StringBuilder sb = new StringBuilder();
//        for (Map.Entry<String, Object> entry : icons.entrySet()) {
//
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//            sb.append("'"+entry.getKey()+ "',\n");
//        }
//        FileUtil.saveFile("E:\\data\\json\\","ion.txt",sb.toString());
//        Icon icon = Json
        //json 去除key里面的双引号
        byte[] content = FileUtil.getContent("E:\\PRJ-PLATFORM\\vue3-form-making-main\\src\\i18n\\en_US.json");
      String json = new String(content, StandardCharsets.UTF_8);
        json = json.replaceAll("\"(\\w+)\"(\\s*:\\s*)", "$1$2");
        FileUtil.saveFile("E:\\data\\json\\","en_US.json",json);

    }
    @Data
    @ToString
   public static class  Icon {
        String prefix;
        Map<String, Object> icons;
        Map<String, Object> aliases;
    }


}
