package com.laputa.iot.common.swagger.model;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

//@Configuration
//@Component
//@ConfigurationProperties(prefix = "spring.cloud.gateway.routes")
public class MyRouterList {

  private static List<MyRouter> list;//必须是static修饰，不然获取不到配置的值

  public static List<MyRouter> getList() {
    return list;
  }

  public static void setList(List<MyRouter> list) {
    MyRouterList.list = list;
  }

  @Override
  public String toString() {
    return "AntPoolConfigList{" +
            "list=" + list +
            '}';
  }
}
