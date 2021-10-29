package com.laputa.iot.workflow.service;

import org.flowable.engine.task.Comment;

import java.util.List;
import java.util.Map;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/4 21:47
 * @Description: 流程任务相关Service
 */
public interface ActTaskService {
    /**
     * 为流程任务设置变量。
     * 如果变量尚未存在，则将在任务中创建该变量。
     *
     * @param taskId        任务的id，不能为null.
     * @param variableName  变量键名.
     * @param variableValue 变量键值.
     * @throws
     */
    void setVariableLocal(String taskId, String variableName, Object variableValue);

    /**
     * 为流程任务设置多对变量。
     * 如果变量尚未存在，则将在任务中创建该变量。
     *
     * @param taskId    任务的id，不能为null.
     * @param variables 多对变量键值对.
     * @throws
     */
    void setVariablesLocal(String taskId, Map<String, ? extends Object> variables);

    /**
     * 执行任务
     * @param taskId 任务id
     * @param userId 用户id
     * @param action 任务执行类型 claim：签收 unclaim 反签收 complete 完成 delegate 任务委派 resolve 任务签收完成 返回任务人 assignee 任务转办
     * @param variables 任务变量
     * @param localScope 存储范围。如果为true，则提供的变量将存储在任务本地（当任务结束后，再也取不到这个值），
     *                   而不是流程实例范围（默认是存放在流程实例中）。
     */
    Map<String, Object> execute(String taskId, String userId, String action, Map<String,Object> variables,Boolean localScope);

    /**
     * 任务签收。
     *
     * @param taskId 任务的id，不能为null.
     * @param userId 签收人标识.
     * @return
     * @throws Exception
     */
    void claim(String taskId, String userId) throws Exception;

    /**
     * 任务反签收
     *
     * @param taskId 任务的id，不能为null.
     * @return
     * @throws Exception
     */
    void unClaim(String taskId) throws Exception;


    /**
     * 执行任务
     *
     * @param taskId 任务的id，不能为null.
     * @throws Exception
     */
    void complete(String taskId) throws Exception;


    /**
     * 执行任务，并设置任务变量。
     *
     * @param taskId    任务的id，不能为null.
     * @param variables 任务变量.
     * @return
     * @throws Exception
     */
    void complete(String taskId, Map<String, Object> variables);

    /**
     * 执行任务，并设置任务变量。
     *
     * @param taskId     任务的id，不能为null.
     * @param variables  任务变量.
     * @param localScope 存储范围。如果为true，则提供的变量将存储在任务本地（当任务结束后，再也取不到这个值），
     *                   而不是流程实例范围（默认是存放在流程实例中）。
     * @return
     * @throws
     */
    Map<String, Object> complete(String taskId, Map<String, Object> variables, boolean localScope);


    /**
     * 任务移交：将任务的所有权转移给其他用户。
     *
     * @param taskId 任务的id，不能为null.
     * @param userId 接受所有权的人.
     */

    void setAssignee(String taskId, String userId);

    /**
     * 任务委派
     *
     * @param taskId 任务的id，不能为null.
     * @param userId 被委派人ID.
     */
    void delegate(String taskId, String userId);


    /**
     * 委派任务完成，归还委派人
     *
     * @param taskId 任务的id，不能为null.
     */
    void resolveTask(String taskId);


    /**
     * 更改任务拥有者
     *
     * @param taskId 任务的id，不能为null.
     * @param userId 任务拥有者.
     */
    void setOwner(String taskId, String userId);

    /**
     * 删除任务
     *
     * @param taskId 任务的id，不能为null.
     */
    void delete(String taskId);

    /**
     * 删除任务，附带删除理由
     *
     * @param taskId 任务的id，不能为null.
     * @param reason 删除理由.
     */
    void deleteWithReason(String taskId, String reason);

    /**
     * 为任务添加任务处理人。
     *
     * @param taskId 任务的id，不能为null.
     * @param userId 任务处理人ID.
     * @throws Exception
     */
    void addCandidateUser(String taskId, String userId) throws Exception;


    /**
     * 为流程任务 和/或 流程实例添加注释。
     *
     * @param taskId            流程任务ID.
     * @param processInstanceId 流程实例ID.
     * @param message           注释信息
     * @param userId 当前用户id
     * @return
     * @throws Exception
     */
    Comment addComment(String taskId, String processInstanceId, String message,String userId);


    /**
     * 查询与任务相关的注释信息。
     *
     * @param processInstanceId 流程实例ID.
     * @return
     */
    List<Comment> getProcessInstanceComments(String processInstanceId);

    /**
     * 任务撤回
     *
     * @param processInstanceId 流程实例ID.
     * @param currentActivityId 当前活动任务ID.
     * @param newActivityId     撤回到达的任务ID.
     */
    void withdraw(String processInstanceId, String currentActivityId, String newActivityId);
}
