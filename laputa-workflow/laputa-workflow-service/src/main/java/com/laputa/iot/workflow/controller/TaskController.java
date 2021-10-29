package com.laputa.iot.workflow.controller;

import com.laputa.iot.common.core.base.BaseController;

import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.workflow.api.enums.ActionEnum;
import com.laputa.iot.workflow.api.request.ExecuteTaskRequest;
import com.laputa.iot.workflow.api.request.TaskRequestQuery;
import com.laputa.iot.workflow.service.ActHistTaskService;
import com.laputa.iot.workflow.service.ActTaskQueryService;
import com.laputa.iot.workflow.service.ActTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @author: wangdingfeng
 * @Date: 2020/4/5 14:33
 * @Description:
 */
@RestController
@RequestMapping("/runtime/tasks")
@Api(value = "Task", tags = {"流程任务"})
@RequiredArgsConstructor
public class TaskController extends BaseController {

    private final ActTaskQueryService actTaskQueryService;
    private final ActTaskService actTaskService;
    private final ActHistTaskService actHistTaskService;


    @GetMapping("/count")
    @ApiOperation(value = "根据用户ID或者用户组ID，查询该用户代办", produces = "application/json")
    public R count(String userId) {
        return success(actTaskQueryService.countTaskCandidateOrAssignedOrGroup(userId,null));
    }

    @PostMapping
    @ApiOperation(value = "根据用户ID或者用户组ID，查询该用户代办", produces = "application/json")
    public R page(@RequestBody TaskRequestQuery taskRequestQuery) {
        return success(actTaskQueryService.taskCandidateOrAssignedOrGroupPage(taskRequestQuery));
    }

    @GetMapping
    @ApiOperation(value = "查询任务", produces = "application/json")
    public R getTask(TaskRequestQuery taskRequestQuery) {
        return success(actTaskQueryService.queryByParams(taskRequestQuery));
    }

    @PostMapping(value = "/comment")
    @ApiOperation(value = "添加批注信息", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "processInstanceId", value = "流程实例ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "message", value = "批注信息", required = true, dataType = "String"),
    })
    public R addComments(@RequestParam String taskId,@RequestParam String processInstanceId,@RequestParam String message, @RequestParam String userId){
        return success(actTaskService.addComment(taskId,processInstanceId,message,userId));
    }

    @GetMapping(value = "/comment")
    @ApiOperation(value = "查询批注信息", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "processInstanceId", value = "流程实例ID", required = true, dataType = "String")})
    public R getTaskComments(String processInstanceId) {
        return success(actTaskService.getProcessInstanceComments(processInstanceId));
    }

    @GetMapping(value = "/his")
    @ApiOperation(value = "查询已办任务", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "current", value = "页码",defaultValue = "1",required = false, dataType = "long"),
            @ApiImplicitParam(name = "size", value = "数量",defaultValue = "20",required = false, dataType = "long"),
    })
    public R hisPage(TaskRequestQuery taskRequestQuery) {
        return success(actHistTaskService.pageListByUser(taskRequestQuery));
    }

    @PostMapping(value = "/{taskId}")
    @ApiOperation(value = "执行任务", notes = "任务执行类型 claim：签收 unclaim 反签收 complete 完成 delegate 任务委派 resolve 任务签收完成 返回任务人 assignee 任务转办", produces = "application/json")
    public R executeTask(@PathVariable String taskId, @RequestBody ExecuteTaskRequest executeTaskRequest) {
        Map<String, Object> map = actTaskService.execute(taskId, executeTaskRequest.getAssignee(), executeTaskRequest.getAction(), executeTaskRequest.getVariables(), executeTaskRequest.getLocalScope());
        return success(ActionEnum.actionOf(executeTaskRequest.getAction()).getName(), map);
    }

    @PutMapping
    @ApiOperation(value = "任务撤回",notes = "注意：当前与目标定义Key为设计模板时任务对应的ID,而非数据主键ID",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processInstanceId", value = "流程实例ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "currentTaskKey", value = "当前任务定义Key", required = true, dataType = "String"),
            @ApiImplicitParam(name = "targetTaskKey", value = "目标任务定义Key", required = true, dataType = "String")
    })
    public R withdraw(String processInstanceId, String currentTaskKey, String targetTaskKey) {
        actTaskService.withdraw(processInstanceId, currentTaskKey, targetTaskKey);
        return success("任务撤回成功");
    }

    @GetMapping(value = "/records/{processInstanceId}")
    @ApiOperation(value = "查询流程记录", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "processInstanceId", value = "流程实例ID", required = true, dataType = "String")})
    public R records(@PathVariable String processInstanceId){
        return success(actHistTaskService.listByInstanceIdFilter(processInstanceId,null));
    }


}
