package com.laputa.iot.workflow.api.feign.client;

import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.core.constant.ServiceNameConstants;
import com.laputa.iot.common.feign.config.FeignRequestInterceptorConfig;
import com.laputa.iot.workflow.api.request.TaskRequestQuery;

import com.laputa.iot.workflow.api.feign.fallback.TaskFallBackFactory;
import com.laputa.iot.workflow.api.request.ExecuteTaskRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/8 22:38
 * @Description:
 */
@FeignClient(contextId = "taskClient", name = ServiceNameConstants.WORKFLOW_SERVICE,  configuration = FeignRequestInterceptorConfig.class, fallbackFactory = TaskFallBackFactory.class)
public interface TaskClient {
    /**
     * 执行任务
     * @param taskId
     * @return
     */
    @PostMapping(value = "/runtime/tasks/{taskId}")
    R executeTask(@PathVariable(value="taskId") String taskId, @RequestBody ExecuteTaskRequest executeTaskRequest);

    /**
     * 添加批注信息
     * @param taskId 任务ID
     * @param processInstanceId 流程实例ID
     * @param message 批注信息
     * @return
     */
    @PostMapping(value = "/runtime/tasks/comment")
    R addComments(@RequestParam(value="taskId") String taskId,@RequestParam(value="processInstanceId") String processInstanceId,@RequestParam(value="message") String message,@RequestParam(value="userId") String userId);

    /**
     *
     * @param taskRequestQuery
     * @return
     */
    @GetMapping(value = "/runtime/tasks")
    R getTask(TaskRequestQuery taskRequestQuery);


}
