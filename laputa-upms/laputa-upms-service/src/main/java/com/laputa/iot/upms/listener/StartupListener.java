package com.laputa.iot.upms.listener;

import cn.hutool.core.net.NetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedHashSet;

/**
 * 项目启动监听器, 当项目启动时, 遍历当前对象存储的所有内容, 添加到缓存中.
 * @author sommer.jiang
 */
@Component
@Slf4j
public class StartupListener implements ApplicationListener<ApplicationStartedEvent> {

    @Resource
    private Environment environment;

    @Override
    public void onApplicationEvent(@NonNull ApplicationStartedEvent event) {
        printStartInfo();
    }


    private void printStartInfo() {
        String serverPort = environment.getProperty("server.port", "4001");

        LinkedHashSet<String> localIps = NetUtil.localIps();
        StringBuilder indexAddr = new StringBuilder();

        for (String localIp : localIps) {
            String addr = String.format("http://%s:%s", localIp, serverPort);
            indexAddr.append(addr).append("\t");
        }
        log.info("UPMS started at          " + indexAddr);

    }

    // private void cacheAllFile() {
    //     try {
    //         fileAsyncCacheService.cacheGlobalFile();
    //     } catch (Exception e) {
    //         throw new InitializeException("初始化缓存异常.", e);
    //     }
    // }
}