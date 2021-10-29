package com.laputa.iot.workflow.api.feign.fallback;


import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.core.constant.ServiceNameConstants;
import com.laputa.iot.workflow.api.feign.client.InstanceClient;
import com.laputa.iot.workflow.api.request.ProcessInstanceCreateRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;


/**
 * @author: wangdingfeng
 * @Date: 2020/4/8 22:52
 * @Description:
 */
@Component
@Slf4j
public class InstanceFallBackFactory implements FallbackFactory<InstanceClient> {
    @Override
    public InstanceClient create(Throwable throwable) {
        return new InstanceClient() {
            @Override
            public R startByKey(ProcessInstanceCreateRequest request) {
                log.error("调用spark-flowable服务InstanceClient:startByKey方法失败!,错误日志:{}", throwable);
                return R.hystrixError(ServiceNameConstants.WORKFLOW_SERVICE, "InstanceClient:startByKey");
            }

            @Override
            public R delete(String processInstanceId) {
                log.error("调用spark-flowable服务InstanceClient:delete方法失败!,错误日志:{}", throwable);
                return R.hystrixError(ServiceNameConstants.WORKFLOW_SERVICE, "InstanceClient:delete");
            }
        };
    }
}
