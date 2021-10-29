package com.laputa.iot.workflow.api.feign.fallback;

import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.core.constant.ServiceNameConstants;
import com.laputa.iot.workflow.api.feign.client.TaskClient;
import com.laputa.iot.workflow.api.request.TaskRequestQuery;

import com.laputa.iot.workflow.api.request.ExecuteTaskRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/8 22:44
 * @Description:
 */
@Component
@Slf4j
public class TaskFallBackFactory implements FallbackFactory<TaskClient> {
    @Override
    public TaskClient create(Throwable throwable) {
        return new TaskClient() {
            @Override
            public R executeTask(String taskId, ExecuteTaskRequest executeTaskRequest) {
                log.error("调用spark-flowable服务TaskClient:executeTask方法失败!,错误日志:{}", throwable.getMessage());
                return R.hystrixError(ServiceNameConstants.WORKFLOW_SERVICE, "TaskClient:executeTask");
            }

            @Override
            public R addComments(String taskId, String processInstanceId, String message,String userId) {
                log.error("调用spark-flowable服务TaskClient:addComments!,错误日志:{}", throwable.getMessage());
                return R.hystrixError(ServiceNameConstants.WORKFLOW_SERVICE, "TaskClient:addComments");
            }

            @Override
            public R getTask(TaskRequestQuery taskRequestQuery) {
                log.error("调用spark-flowable服务TaskClient:getTask!,错误日志:{}", throwable.getMessage());
                return R.hystrixError(ServiceNameConstants.WORKFLOW_SERVICE, "TaskClient:getTask");
            }
        };
    }
}
