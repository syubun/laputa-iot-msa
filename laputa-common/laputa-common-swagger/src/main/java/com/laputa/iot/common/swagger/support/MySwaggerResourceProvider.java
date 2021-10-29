package com.laputa.iot.common.swagger.support;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.*;



@Component
@Slf4j
public class MySwaggerResourceProvider implements SwaggerResourcesProvider {
//    http://tool.chacuo.net/cryptaes CTR NOPADDING 128 laputaiotxlaputa laputaiotxlaputa base65 前端登录密码用这个完成
    private String passwd = "yRbO1KDk";
    /**
     * swagger2默认的url后缀
     */
    private static final String SWAGGER2URL = "/v2/api-docs";

    /**
     * 网关应用名称
     */
    @Value("${spring.application.name}")
    private String self;


//    @Value("${spring.cloud.gateway.routes}")
//    private  List<RouteDefinition> routes;

    public static Map<String, String> moduleMap = new HashMap<>();

    static {
        moduleMap.put("A权限管理", "/upms");
        moduleMap.put("B消息管理", "/msg");
//        moduleMap.put("C工作流管理", "/workflow");
    }

    @Override
    public List<SwaggerResource> get() {
        log.info("static SwaggerProvider");

        List resources = new ArrayList<>();
        moduleMap.forEach((k, v) -> {
            resources.add(swaggerResource(k, v));
        });
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location + SWAGGER2URL);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }

    /**
     * 网关路由
     */
//    private final RouteLocator routeLocator;

//    @Autowired
//    MyRouterList myRouterList;

    @Autowired
    public MySwaggerResourceProvider() {
//        if(myRouterList!=null && myRouterList.getList()!=null){
//            myRouterList.getList().stream().forEach(System.out::println);
//        }else {
//            log.info("myroutlist is null");
//        }

//        this.routeLocator = routeLocator;
    }
// 动态路由
//    @Override
//    public List<SwaggerResource> get() {
//        List<SwaggerResource> resources = new ArrayList<>();
//        List<String> routeHosts = new ArrayList<>();
//        // 由于我的网关采用的是负载均衡的方式，因此我需要拿到所有应用的serviceId
//        // 获取所有可用的host：serviceId
//        routeLocator.getRoutes().filter(route -> route.getUri().getHost() != null)
//                .filter(route -> !self.equals(route.getUri().getHost()))
//                .subscribe(route -> routeHosts.add(route.getUri().getHost()));
//
//        // 记录已经添加过的server，存在同一个应用注册了多个服务在nacos上
//        Set<String> dealed = new HashSet<>();
//        routeHosts.forEach(instance -> {
//            // 拼接url，样式为/serviceId/v2/api-info，当网关调用这个接口时，会自动通过负载均衡寻找对应的主机
//            String url = "/" + instance + SWAGGER2URL;
//            if (!dealed.contains(url)) {
//                dealed.add(url);
//                SwaggerResource swaggerResource = new SwaggerResource();
//                swaggerResource.setUrl(url);
//                swaggerResource.setName(instance);
//                resources.add(swaggerResource);
//            }
//        });
//        return resources;
//    }
}