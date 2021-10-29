package com.laputa.iot.workflow.controller;

import cn.hutool.core.bean.BeanUtil;

import com.laputa.iot.common.core.base.BaseController;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.workflow.api.dto.PageDTO;
import com.laputa.iot.workflow.api.dto.ProcessInstanceDTO;
import com.laputa.iot.workflow.api.enums.ActionEnum;
import com.laputa.iot.workflow.api.request.ProcessInstanceCreateRequest;
import com.laputa.iot.workflow.service.ActInstanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/5 21:14
 * @Description: 流程实例
 */
@RestController
@RequestMapping("runtime/process-instances")
@Api(value = "Instance", tags = {"流程实例"})
@RequiredArgsConstructor
public class InstanceController extends BaseController {

    private final ActInstanceService actInstanceService;

    @PostMapping
    @ApiOperation(value = "启动流程实例__通过流程定义key", notes = "实例启动成功，返回当前活动任务", produces = "application/json")
    public R startByKey(@RequestBody ProcessInstanceCreateRequest request) {
        ProcessInstance pi = actInstanceService.startProcessInstanceByKey(request);
        return success(pi.getId());
    }

    @GetMapping
    @ApiOperation(value = "分页查询流程实例")
    public R page(ProcessInstanceDTO processInstanceDTO, PageDTO page){
        return success(actInstanceService.findPage(processInstanceDTO,page));
    }

    @GetMapping(value = "/{processInstanceId}")
    @ApiOperation(value = "查询", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processInstanceId", value = "流程实例ID", required = true, dataType = "String")
    })
    public R get(@PathVariable String processInstanceId){
        Map<String,Object> objectMap = BeanUtil.beanToMap(actInstanceService.processInstanceId(processInstanceId));
        return success(objectMap);
    }

    @PutMapping(value = "/{processInstanceId}/{action}")
    @ApiOperation(value = "操作流程", notes = "suspend: 挂起流程,activate: 激活流程", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "action", value = "任务类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "processInstanceId", value = "流程实例ID", required = true, dataType = "String")
    })
    public R actionById(@PathVariable("processInstanceId") String processInstanceId, @PathVariable("action") String action) {
        actInstanceService.action(action, processInstanceId);
        return success(ActionEnum.actionOf(action).getName());
    }

    @DeleteMapping(value = "/{processInstanceId}")
    @ApiOperation(value = "删除", notes = "删除流程", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processInstanceId", value = "流程实例ID", required = true, dataType = "String")
    })
    public R delete(@PathVariable String processInstanceId){
        actInstanceService.deleteProcessInstance(processInstanceId,null);
        return success("删除成功");
    }

    @ApiOperation(value = "获取人员", notes = "参与流程实例的人员信息", produces = "application/json")
    @GetMapping(value = "/{processInstanceId}/identitylinks", produces = "application/json")
    public R getIdentityLinks(@PathVariable String processInstanceId){
        return success(actInstanceService.getIdentityLinks(processInstanceId));
    }

    @PostMapping(value = "/{processInstanceId}/identitylinks")
    @ApiOperation(value = "多实例加签", notes = "数据变化：act_ru_task、act_ru_identitylink各生成一条记录", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityDefId", value = "流程环节定义ID", required = true),
            @ApiImplicitParam(name = "instanceId", value = "流程实例ID", required = true)
    })
    public R addMultiInstanceExecution(@PathVariable String processInstanceId, @RequestParam("activityDefId") String activityDefId, @RequestBody Map<String, Object> variables) {
        actInstanceService.addMultiInstanceExecution(activityDefId, processInstanceId, variables);
        return success("加签成功");
    }

    @DeleteMapping(value = "/{currentChildExecutionId}/identitylinks")
    @ApiOperation(value = "多实例减签", notes = "数据变化：act_ru_task减1、act_ru_identitylink不变", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentChildExecutionId", value = "子执行流ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "flag", value = "子执行流是否已执行", required = true, dataType = "boolean")
    })
    public R deleteMultiInstanceExecution(@PathVariable String currentChildExecutionId, boolean flag) {
        actInstanceService.deleteMultiInstanceExecution(currentChildExecutionId, flag);
        return success("减签成功");
    }

}
