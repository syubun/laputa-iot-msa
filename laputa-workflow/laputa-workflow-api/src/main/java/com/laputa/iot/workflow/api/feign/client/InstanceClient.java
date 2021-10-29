package com.laputa.iot.workflow.api.feign.client;

import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.feign.config.FeignRequestInterceptorConfig;
import com.laputa.iot.workflow.api.feign.fallback.InstanceFallBackFactory;
import com.laputa.iot.common.core.constant.ServiceNameConstants;


import com.laputa.iot.workflow.api.request.ProcessInstanceCreateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/8 22:47
 * @Description:
 */
@FeignClient(contextId = "instanceClient", name = ServiceNameConstants.WORKFLOW_SERVICE, configuration = FeignRequestInterceptorConfig.class,  fallbackFactory = InstanceFallBackFactory.class)
public interface InstanceClient {

    /**
     * 开启流程
     * @return
     */
    @PostMapping("runtime/process-instances")
    R startByKey(@RequestBody ProcessInstanceCreateRequest request);

    /**
     * 删除流程
     * @param processInstanceId 流程实例ID
     * @return
     */
    @DeleteMapping(value = "runtime/process-instances/{processInstanceId}")
    R delete(@PathVariable(value="processInstanceId")  String processInstanceId);

}
