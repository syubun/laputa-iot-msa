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

package com.laputa.iot.upms.config;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.laputa.iot.upms.service.SysRouteConfService;
import com.laputa.iot.common.core.constant.CacheConstants;
import com.laputa.iot.common.gateway.support.DynamicRouteInitEvent;
import com.laputa.iot.common.gateway.vo.RouteDefinitionVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.scheduling.annotation.Async;

import java.net.URI;
import java.util.*;

/**
 * @author sommer.jiang
 * @date 2018/10/31
 * <p>
 * ?????????????????????????????????????????????????????????Redis
 * sommer.jiang 20200723
 */

@Slf4j
//@Configuration
@AllArgsConstructor
public class DynamicRouteInitRunner {

    private final RedisTemplate redisTemplate;

    private final SysRouteConfService routeConfService;

    private final LaputaNacos laputaNacos;

    @Async
    @Order
    @EventListener({WebServerInitializedEvent.class, DynamicRouteInitEvent.class})
    public void initRoute() {
        redisTemplate.delete(CacheConstants.ROUTE_KEY);
        log.info("???????????????????????????");
//		log.info(laputaNacos.toString());
        List<RouteDefinitionVo> newVos = new ArrayList<>();
        routeConfService.list().forEach(route -> {
            RouteDefinitionVo vo = new RouteDefinitionVo();
            vo.setRouteName(route.getRouteName());
            vo.setId(route.getRouteId());
            vo.setUri(URI.create(route.getUri()));
            vo.setOrder(route.getOrder());

            JSONArray filterObj = JSONUtil.parseArray(route.getFilters());
            vo.setFilters(filterObj.toList(FilterDefinition.class));
            JSONArray predicateObj = JSONUtil.parseArray(route.getPredicates());
            vo.setPredicates(predicateObj.toList(PredicateDefinition.class));
            newVos.add(vo);
            log.info("????????????ID???{},{}", route.getRouteId(), vo);
            redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RouteDefinitionVo.class));
            redisTemplate.opsForHash().put(CacheConstants.ROUTE_KEY, route.getRouteId(), vo);

        });

        //?????? JSON ?????????
//        JsonNode jsonNodeTree;
//        //????????? YAML ?????????
//        String yamlStr = null;
//        try {
//            jsonNodeTree = new ObjectMapper().readTree(JSONUtil.toJsonStr(JSONUtil.toJsonStr(newVos)));
//            yamlStr = new YAMLMapper().writeValueAsString(jsonNodeTree);
//            FileUtil.saveFile("E:\\ffout","config.yml",yamlStr);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        log.info(yamlStr);

//        Yaml yaml = new Yaml();
////??? JSON ??????????????? Map
////        Map<String,Object> map = (Map<String, Object>) yaml.load(JSONUtil.toJsonStr(newVos));
////????????? YAML ?????????
//        Iterator<RouteDefinitionVo> ts = newVos.listIterator();
//        String yamlStr = yaml.dumpAll(ts);
//        log.info(yamlStr);
//        FileUtil.saveFile("E:\\ffout","config.yml",yamlStr);
        // ????????????????????????
        redisTemplate.convertAndSend(CacheConstants.ROUTE_JVM_RELOAD_TOPIC, "????????????,??????????????????");
        log.debug("??????????????????????????? ");
    }

    static <T> Iterable<T> iterableReverseList(final List<T> l) {
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    ListIterator<T> listIter = l.listIterator(l.size());
                    public boolean hasNext() { return listIter.hasPrevious(); }
                    public T next() { return listIter.previous(); }
                    public void remove() { listIter.remove(); }
                };
            }
        };
    }

    /**
     * redis ????????????,?????? gateway_redis_route_reload_topic,????????????Redis
     *
     * @param redisConnectionFactory redis ??????
     * @return
     */
    @Bean
    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener((message, bytes) -> {
            log.warn("???????????????Redis ????????????????????????");
            initRoute();
        }, new ChannelTopic(CacheConstants.ROUTE_REDIS_RELOAD_TOPIC));
        return container;
    }

}
