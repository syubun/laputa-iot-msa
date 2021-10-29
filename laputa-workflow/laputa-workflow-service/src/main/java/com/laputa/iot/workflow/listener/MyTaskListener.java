package com.laputa.iot.workflow.listener;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.flowable.engine.delegate.TaskListener;

import org.flowable.task.service.delegate.BaseTaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.flowable.biz.listener
 * @ClassName: TaskListener
 * @Author: wangdingfeng
 * @Description: 任务监听器
 * @Date: 2020/4/8 14:22
 * @Version: 1.0
 */
@Slf4j
@Component
public class MyTaskListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        String eventName = delegateTask.getEventName();
        switch (eventName) {
            case BaseTaskListener.EVENTNAME_CREATE:
                log.info("当前监听创建事件:create");
                //开启消息发送线程
                ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1,new BasicThreadFactory.Builder().namingPattern("task-thread-pool-%d").daemon(true).build());
                scheduledThreadPoolExecutor.submit(new MessageRunnableTask(delegateTask));
                break;
            case BaseTaskListener.EVENTNAME_ASSIGNMENT:
                log.info("当前监听指派事件:assignment");
                break;
            case BaseTaskListener.EVENTNAME_COMPLETE:
                log.info("当前监听完成事件:complete");
                break;
            case BaseTaskListener.EVENTNAME_DELETE:
                log.info("当前监听销毁事件:delete");
                break;
            default:
                break;
        }
    }
}
