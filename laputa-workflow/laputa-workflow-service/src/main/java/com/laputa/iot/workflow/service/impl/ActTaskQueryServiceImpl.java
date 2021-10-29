package com.laputa.iot.workflow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;

import com.laputa.iot.common.core.util.BeanCopyUtil;
import com.laputa.iot.workflow.api.enums.VariablesEnum;
import com.laputa.iot.workflow.api.request.TaskRequestQuery;
import com.laputa.iot.workflow.api.vo.TaskVO;
import com.laputa.iot.workflow.constants.ProcessConstants;
import com.laputa.iot.workflow.service.ActTaskQueryService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.api.history.HistoricTaskInstanceQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/4 22:34
 * @Description: 任务查询
 */
@Service
@RequiredArgsConstructor
public class ActTaskQueryServiceImpl implements ActTaskQueryService {

    private final TaskService taskService;
    private final HistoryService historyService;
    private final RuntimeService runtimeService;

    @Override
    public TaskQuery createTaskQuery() {
        return taskService.createTaskQuery();
    }

    @Override
    public HistoricTaskInstanceQuery createHistoricTaskInstanceQuery() {
        return historyService.createHistoricTaskInstanceQuery();
    }

    @Override
    public Task taskId(String taskId) {
        return createTaskQuery().taskId(taskId).singleResult();
    }

    @Override
    public TaskVO queryTaskVOById(String taskId) {
        Task task = createTaskQuery().taskId(taskId).singleResult();
        TaskVO taskVO = new TaskVO();
        BeanUtils.copyProperties(task, taskVO, "variables");
        return taskVO;
    }

    @Override
    public List<TaskVO> queryByParams(TaskRequestQuery taskRequestQuery) {
        TaskQuery taskQuery = buildQueryParams(taskRequestQuery);
        if (StringUtils.isNotBlank(taskRequestQuery.getTaskId())) {
            //任务id查询单条记录
            Task task = createTaskQuery().taskId(taskRequestQuery.getTaskId()).includeProcessVariables().singleResult();
            TaskVO taskVO = new TaskVO();
            BeanUtil.copyProperties(task, taskVO, "variables");
            taskVO.setVariables(task.getProcessVariables());
            return Lists.newArrayList(taskVO);
        }
        List<Task> tasks = taskQuery.includeProcessVariables().list();
        List<TaskVO> taskVOS = BeanCopyUtil.copyListProperties(tasks, TaskVO::new);
        return taskVOS;
    }

    @Override
    public List<Task> taskCandidateUser(String candidateUser, int page, int pageSize) {
        int firstResult = (page - 1) * pageSize;
        int maxResults = page * pageSize;
        return createTaskQuery().taskCandidateUser(candidateUser)
                .orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .listPage(firstResult, maxResults);
    }

    @Override
    public List<Task> taskAssignee(String assignee, int page, int pageSize) {
        int firstResult = (page - 1) * pageSize;
        int maxResults = page * pageSize;
        return createTaskQuery().taskAssignee(assignee).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .listPage(firstResult, maxResults);
    }

    @Override
    public List<Task> taskCandidateOrAssigned(String userId) {
        return createTaskQuery().taskCandidateOrAssigned(userId)
                .orderByTaskPriority().desc()
                .orderByTaskCreateTime().desc()
                .list();
    }

    @Override
    public List<Task> taskCandidateOrAssignedOrGroup(String userId, String groupId) {
        if (StringUtils.isBlank(groupId)) {
            return this.taskCandidateOrAssigned(userId);
        }
        return createTaskQuery().taskCandidateOrAssigned(userId).taskCandidateGroup(groupId).includeProcessVariables()
                .orderByTaskPriority().desc()
                .orderByTaskCreateTime().desc()
                .list();
    }

    @Override
    public Page<TaskVO> taskCandidateOrAssignedOrGroupPage(TaskRequestQuery query) {
        TaskQuery taskQuery = buildQueryParams(query);
        if (StringUtils.isNotBlank(query.getBusinessType())) {
            taskQuery.processVariableValueEquals(VariablesEnum.businessType.toString(), query.getBusinessType());
        }
        if (StringUtils.isNotBlank(query.getBusinessName())) {
            taskQuery.processVariableValueLike(VariablesEnum.businessName.toString(), ProcessConstants.contactLike(query.getBusinessName()));
        }
        List<Task> taskList = taskQuery.includeProcessVariables()
                .orderByTaskPriority().desc()
                .orderByTaskCreateTime().desc()
                .listPage(query.getFirstResult(), query.getMaxResults());
        List<TaskVO> voList = Lists.newArrayList();
        taskList.forEach(task -> {
            TaskVO taskVO = new TaskVO();
            BeanUtil.copyProperties(task, taskVO);
            //查询业务主键
            taskVO.setBusinessKey(runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult().getBusinessKey());
            //放入流程参数
            taskVO.setVariables(task.getProcessVariables());
            voList.add(taskVO);
        });
        Long count = taskQuery.count();
        Page page = new Page(query.getCurrent(), query.getSize(), count);
        page.setRecords(voList);
        return page;
    }

    @Override
    public List<HistoricTaskInstance> taskAssigneeHistory(String assignee, int page, int pageSize) {
        int firstResult = (page - 1) * pageSize;
        int maxResults = page * pageSize;
        return createHistoricTaskInstanceQuery().taskAssignee(assignee).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .listPage(firstResult, maxResults);
    }

    /**
     * 构建自定义流程参数查询
     *
     * @param args
     * @return
     */
    private TaskQuery buildTaskQueryByVariables(Map<String, Object> args) {
        TaskQuery tq = createTaskQuery();
        if (args != null && args.size() > 0) {
            for (Map.Entry<String, Object> entry : args.entrySet()) {
                if (VariablesEnum.businessName.toString().equals(entry.getKey()) || VariablesEnum.businessType.toString().equals(entry.getKey())) {
                    tq.processVariableValueLike(entry.getKey(), ProcessConstants.contactLike(String.valueOf(entry.getValue())));
                } else {
                    tq.processVariableValueEquals(entry.getKey(), entry.getValue());
                }
            }
        }

        return tq;
    }

    @Override
    public List<Task> taskCandidateUserByCondition(String candidateUser, Map<String, Object> variables, int page, int pageSize) {
        int firstResult = (page - 1) * pageSize;
        int maxResults = page * pageSize;
        return buildTaskQueryByVariables(variables).taskCandidateUser(candidateUser)
                .orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .listPage(firstResult, maxResults);
    }

    @Override
    public List<Task> taskAssigneeByCondition(String assignee, Map<String, Object> variables, int page, int pageSize) {
        int firstResult = (page - 1) * pageSize;
        int maxResults = page * pageSize;
        return buildTaskQueryByVariables(variables).taskAssignee(assignee).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .listPage(firstResult, maxResults);
    }

    @Override
    public List<Task> taskCandidateOrAssignedByCondition(String userId, Map<String, Object> variables, int page,
                                                         int pageSize) {
        int firstResult = (page - 1) * pageSize;
        int maxResults = page * pageSize;
        return buildTaskQueryByVariables(variables).taskCandidateOrAssigned(userId).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .listPage(firstResult, maxResults);
    }

    @Override
    public long countTaskCandidateUser(String candidateUser) {
        return createTaskQuery().taskCandidateUser(candidateUser)
                .orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .count();
    }

    @Override
    public long countTaskAssignee(String assignee) {
        return createTaskQuery().taskAssignee(assignee).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .count();
    }

    @Override
    public long countTaskCandidateOrAssigned(String userId) {
        return createTaskQuery().taskCandidateOrAssigned(userId).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .count();
    }

    @Override
    public long countTaskCandidateOrAssignedOrGroup(String userId, List<String> groupIds) {
        if (CollectionUtil.isEmpty(groupIds)) {
            return this.countTaskCandidateOrAssigned(userId);
        }
        return createTaskQuery().taskCandidateOrAssigned(userId).taskCandidateGroupIn(groupIds).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .count();
    }

    @Override
    public long countTaskCandidateUserByCondition(String candidateUser, Map<String, Object> variables) {
        return buildTaskQueryByVariables(variables).taskCandidateUser(candidateUser)
                .orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .count();
    }

    @Override
    public long countTaskAssigneeByCondition(String assignee, Map<String, Object> variables) {
        return buildTaskQueryByVariables(variables).taskAssignee(assignee).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .count();
    }

    @Override
    public long countTaskCandidateOrAssignedByCondition(String userId, Map<String, Object> variables) {
        return createTaskQuery().taskCandidateOrAssigned(userId).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .count();
    }

    @Override
    public Task processInstanceId(String processInstanceId) {

        return createTaskQuery().processInstanceId(processInstanceId).active().singleResult();
    }

    @Override
    public List<Task> processInstanceId4Multi(String processInstanceId) {
        List<Task> resultList = new ArrayList<>();
        TaskQuery taskQuery = createTaskQuery().processInstanceId(processInstanceId).active();

        long count = taskQuery.count();
        //多实例情况，当前活动任务不止一条数据
        if (count > 1) {
            resultList.addAll(taskQuery.list());
        } else {
            resultList.add(taskQuery.singleResult());
        }
        return resultList;
    }

    @Override
    public String findBusinessKeyByTaskId(String taskId) {

        Task task = this.createTaskQuery().taskId(taskId).singleResult();

        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();

        if (pi != null) {
            return pi.getBusinessKey();
        }

        return null;
    }

    @Override
    public String findVariableByTaskId(String taskId, String variableName) {
        Object value = taskService.getVariable(taskId, variableName);
        return String.valueOf(value);
    }

    @Override
    public Map<String, Object> getVariableByTaskId(String taskId) {
        return taskService.getVariables(taskId);
    }

    @Override
    public long countTaskAssigneeByTaskQuery(String assignee, TaskQuery query) {
        return query.taskAssignee(assignee).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .count();
    }

    @Override
    public List<Task> taskAssigneeByTaskQuery(String assignee, TaskQuery query, int page, int pageSize) {
        int firstResult = (page - 1) * pageSize;
        int maxResults = page * pageSize;
        return query.taskAssignee(assignee).orderByTaskPriority().desc()
                .orderByTaskCreateTime().asc()
                .listPage(firstResult, maxResults);
    }

    @Override
    public TaskQuery buildQueryParams(TaskRequestQuery taskRequestQuery) {
        TaskQuery taskQuery = createTaskQuery();
        if (StringUtils.isNotBlank(taskRequestQuery.getUserId())) {
            taskQuery.taskCandidateOrAssigned(taskRequestQuery.getUserId());
        }
        if (CollectionUtil.isNotEmpty(taskRequestQuery.getGroupIds())) {
            taskQuery.taskCandidateGroupIn(taskRequestQuery.getGroupIds());
        }
        if (StringUtils.isNotBlank(taskRequestQuery.getProcessInstanceId())) {
            taskQuery.processInstanceId(taskRequestQuery.getProcessInstanceId());
        }
        if (StringUtils.isNotBlank(taskRequestQuery.getBusinessKey())) {
            taskQuery.processInstanceBusinessKey(taskRequestQuery.getBusinessKey());
        }
        if (StringUtils.isNotBlank(taskRequestQuery.getBusinessCode())) {
            taskQuery.processVariableValueLike(VariablesEnum.businessCode.toString(), ProcessConstants.contactLike(taskRequestQuery.getBusinessCode()));
        }
        if (StringUtils.isNotBlank(taskRequestQuery.getBusinessType())) {
            taskQuery.processVariableValueEquals(VariablesEnum.businessType.toString(), taskRequestQuery.getBusinessType());
        }
        if (StringUtils.isNotBlank(taskRequestQuery.getBusinessName())) {
            taskQuery.processVariableValueLike(VariablesEnum.businessName.toString(), ProcessConstants.contactLike(taskRequestQuery.getBusinessName()));
        }
        return taskQuery;
    }

}
