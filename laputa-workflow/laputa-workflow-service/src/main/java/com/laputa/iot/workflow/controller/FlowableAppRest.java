package com.laputa.iot.workflow.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.laputa.iot.workflow.service.ActProcessService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.*;
import org.flowable.editor.language.json.converter.util.CollectionUtils;
import org.flowable.engine.HistoryService;
import org.flowable.engine.ManagementService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.idm.api.User;
import org.flowable.job.api.Job;
import org.flowable.ui.common.security.SecurityUtils;
import org.flowable.ui.common.service.exception.BadRequestException;
import org.flowable.ui.common.service.exception.InternalServerErrorException;
import org.flowable.ui.task.model.debugger.BreakpointRepresentation;
import org.flowable.ui.task.service.debugger.DebuggerService;
import org.flowable.ui.task.service.editor.mapper.EventInfoMapper;
import org.flowable.ui.task.service.editor.mapper.InfoMapper;
import org.flowable.ui.task.service.editor.mapper.ReceiveTaskInfoMapper;
import org.flowable.ui.task.service.editor.mapper.SequenceFlowInfoMapper;
import org.flowable.ui.task.service.editor.mapper.ServiceTaskInfoMapper;
import org.flowable.ui.task.service.editor.mapper.UserTaskInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/5 21:14
 * @Description:  ?????? Flowable ?????? ui rest
 */
@RestController
@RequestMapping("/app")
@CrossOrigin
@Slf4j
@Api(value = "FlowableAppRest", tags = {"??????rest??????"})
public class FlowableAppRest {

    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected ActProcessService actProcessService;

    @Autowired
    protected RepositoryService repositoryService;
    @Autowired
    protected RuntimeService runtimeService;
    @Autowired
    protected ManagementService managementService;
    @Autowired
    protected DebuggerService debuggerService;
    @Autowired
    protected HistoryService historyService;

    protected List<String> eventElementTypes = new ArrayList<>();
    protected Map<String, InfoMapper> propertyMappers = new HashMap<>();

    public FlowableAppRest() {
        eventElementTypes.add("StartEvent");
        eventElementTypes.add("EndEvent");
        eventElementTypes.add("BoundaryEvent");
        eventElementTypes.add("IntermediateCatchEvent");
        eventElementTypes.add("ThrowEvent");

        propertyMappers.put("BoundaryEvent", new EventInfoMapper());
        propertyMappers.put("EndEvent", new EventInfoMapper());
        propertyMappers.put("IntermediateCatchEvent", new EventInfoMapper());
        propertyMappers.put("ReceiveTask", new ReceiveTaskInfoMapper());
        propertyMappers.put("StartEvent", new EventInfoMapper());
        propertyMappers.put("SequenceFlow", new SequenceFlowInfoMapper());
        propertyMappers.put("ServiceTask", new ServiceTaskInfoMapper());
        propertyMappers.put("ThrowEvent", new EventInfoMapper());
        propertyMappers.put("UserTask", new UserTaskInfoMapper());
    }

    /**
     * ??????????????????????????????
     *
     * @return
     */
    @RequestMapping(value = "/rest/account", method = RequestMethod.GET, produces = "application/json")
    public User getAccount() {
        User user = SecurityUtils.getCurrentUserObject();
        return user;
    }

    @RequestMapping(value = "/rest/stencil-sets/editor", method = RequestMethod.GET, produces = "application/json")
    public JsonNode getStencilSetForEditor() {
        try {
            JsonNode stencilNode = objectMapper.readTree(this.getClass().getClassLoader().getResourceAsStream("stencilset/stencilset_bpmn.json"));
            return stencilNode;
        } catch (Exception e) {
            log.error("Error reading bpmn stencil set json", e);
            throw new InternalServerErrorException("Error reading bpmn stencil set json");
        }
    }

    @RequestMapping(value = "/rest/stencil-sets/cmmneditor", method = RequestMethod.GET, produces = "application/json")
    public JsonNode getCmmnStencilSetForEditor() {
        try {
            JsonNode stencilNode = objectMapper.readTree(this.getClass().getClassLoader().getResourceAsStream("stencilset/stencilset_cmmn.json"));
            return stencilNode;
        } catch (Exception e) {
            log.error("Error reading bpmn stencil set json", e);
            throw new InternalServerErrorException("Error reading bpmn stencil set json");
        }
    }

    /**
     * ????????????
     *
     * @param modelId
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(
            value = {"/rest/models/deployment/{modelId}"},
            method = {RequestMethod.PUT}
    )
    public void deploymentModel(@PathVariable String modelId) {
        try {
            this.actProcessService.deployByModel(modelId, "");
        } catch (Exception var4) {
            log.error("Error while deploymenting: ", var4);
            throw new BadRequestException("Model cannot be deploymenting: " + modelId);
        }
    }

    @RequestMapping(value = "/rest/process-definitions/{processDefinitionId}/model-json", method = RequestMethod.GET, produces = "application/json")
    public JsonNode getModelJSONForProcessDefinition(@PathVariable String processDefinitionId) {

        BpmnModel pojoModel = repositoryService.getBpmnModel(processDefinitionId);

        if (pojoModel == null || pojoModel.getLocationMap().isEmpty()) {
            throw new InternalServerErrorException("Process definition could not be found with id " + processDefinitionId);
        }

        return processProcessElements(pojoModel, null, null, Collections.EMPTY_LIST, "");
    }

    @RequestMapping(value = "/rest/process-instances/history/{processInstanceId}/model-json", method = RequestMethod.GET, produces = "application/json")
    public JsonNode getModelHistoryJSON(@PathVariable String processInstanceId) {
        //TODO ????????????
        HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (processInstance == null) {
            throw new BadRequestException("No process instance found with id " + processInstanceId);
        }

        //?????????????????????
        BpmnModel pojoModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());

        if (pojoModel == null || pojoModel.getLocationMap().isEmpty()) {
            throw new InternalServerErrorException("Process definition could not be found with id " + processInstance.getProcessDefinitionId());
        }

        // Fetch process-instance activities
        //??????????????????
        List<HistoricActivityInstance> activityInstances = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();

        Set<String> completedActivityInstances = new HashSet<>(); //??????????????????id
        Set<String> currentActivityinstances = new HashSet<>(); //???????????????????????????id
        if (CollectionUtils.isNotEmpty(activityInstances)) {
            for (HistoricActivityInstance activityInstance : activityInstances) {
                if (activityInstance.getEndTime() != null) {
                    completedActivityInstances.add(activityInstance.getActivityId());
                } else {
                    currentActivityinstances.add(activityInstance.getActivityId());
                }
            }
        }

        ObjectNode displayNode = processProcessElements(pojoModel, completedActivityInstances, currentActivityinstances, Collections.EMPTY_LIST, processInstanceId);
        return displayNode;
    }


    @RequestMapping(value = "/rest/process-instances/{processInstanceId}/model-json", method = RequestMethod.GET, produces = "application/json")
    public JsonNode getModelJSON(@PathVariable String processInstanceId) {

        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (processInstance == null) {
            throw new BadRequestException("No process instance found with id " + processInstanceId);
        }

        BpmnModel pojoModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());

        if (pojoModel == null || pojoModel.getLocationMap().isEmpty()) {
            throw new InternalServerErrorException("Process definition could not be found with id " + processInstance.getProcessDefinitionId());
        }

        // Fetch process-instance activities
        List<HistoricActivityInstance> activityInstances = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();

        Set<String> completedActivityInstances = new HashSet<>();
        Set<String> currentElements = new HashSet<>();
        if (CollectionUtils.isNotEmpty(activityInstances)) {
            for (HistoricActivityInstance activityInstance : activityInstances) {
                if (activityInstance.getEndTime() != null) {
                    completedActivityInstances.add(activityInstance.getActivityId());
                } else {
                    currentElements.add(activityInstance.getActivityId());
                }
            }
        }

        List<Job> jobs = managementService.createJobQuery().processInstanceId(processInstanceId).list();
        if (CollectionUtils.isNotEmpty(jobs)) {
            List<Execution> executions = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).list();
            Map<String, Execution> executionMap = new HashMap<>();
            for (Execution execution : executions) {
                executionMap.put(execution.getId(), execution);
            }

            for (Job job : jobs) {
                if (executionMap.containsKey(job.getExecutionId())) {
                    currentElements.add(executionMap.get(job.getExecutionId()).getActivityId());
                }
            }
        }

        // Gather completed flows
        List<String> completedFlows = gatherCompletedFlows(completedActivityInstances, currentElements, pojoModel);

        Set<String> completedElements = new HashSet<>(completedActivityInstances);
        completedElements.addAll(completedFlows);

        List<String> breakpoints = new ArrayList<>();
        for (BreakpointRepresentation breakpoint : debuggerService.getBreakpoints()) {
            breakpoints.add(breakpoint.getActivityId());
        }

        ObjectNode displayNode = processProcessElements(pojoModel, completedElements, currentElements, breakpoints, processInstanceId);

        if (completedActivityInstances != null) {
            ArrayNode completedActivities = displayNode.putArray("completedActivities");
            for (String completed : completedActivityInstances) {
                completedActivities.add(completed);
            }
        }

        if (currentElements != null) {
            ArrayNode currentActivities = displayNode.putArray("currentActivities");
            for (String current : currentElements) {
                currentActivities.add(current);
            }
        }

        if (completedFlows != null) {
            ArrayNode completedSequenceFlows = displayNode.putArray("completedSequenceFlows");
            for (String current : completedFlows) {
                completedSequenceFlows.add(current);
            }
        }

        return displayNode;
    }

    /**
     * @param pojoModel         ?????????
     * @param completedElements ?????????
     * @param currentElements   ?????????
     * @param breakpoints
     * @param processInstanceId ??????id
     * @return
     */

    protected ObjectNode processProcessElements(BpmnModel pojoModel, Set<String> completedElements, Set<String> currentElements, Collection<String> breakpoints, String processInstanceId) {
        ObjectNode displayNode = objectMapper.createObjectNode();
        GraphicInfo diagramInfo = new GraphicInfo();

        //?????????
        ArrayNode elementArray = objectMapper.createArrayNode();
        //?????????
        ArrayNode flowArray = objectMapper.createArrayNode();
        ArrayNode collapsedArray = objectMapper.createArrayNode();

        if (CollectionUtils.isNotEmpty(pojoModel.getPools())) {
            ArrayNode poolArray = objectMapper.createArrayNode();
            boolean firstElement = true;
            for (Pool pool : pojoModel.getPools()) {
                ObjectNode poolNode = objectMapper.createObjectNode();
                poolNode.put("id", pool.getId());
                poolNode.put("name", pool.getName());
                GraphicInfo poolInfo = pojoModel.getGraphicInfo(pool.getId());
                fillGraphicInfo(poolNode, poolInfo, true);
                org.flowable.bpmn.model.Process process = pojoModel.getProcess(pool.getId());
                if (process != null && CollectionUtils.isNotEmpty(process.getLanes())) {
                    ArrayNode laneArray = objectMapper.createArrayNode();
                    for (Lane lane : process.getLanes()) {
                        ObjectNode laneNode = objectMapper.createObjectNode();
                        laneNode.put("id", lane.getId());
                        laneNode.put("name", lane.getName());
                        fillGraphicInfo(laneNode, pojoModel.getGraphicInfo(lane.getId()), true);
                        laneArray.add(laneNode);
                    }
                    poolNode.set("lanes", laneArray);
                }
                poolArray.add(poolNode);

                double rightX = poolInfo.getX() + poolInfo.getWidth();
                double bottomY = poolInfo.getY() + poolInfo.getHeight();
                double middleX = poolInfo.getX() + (poolInfo.getWidth() / 2);
                if (firstElement || middleX < diagramInfo.getX()) {
                    diagramInfo.setX(middleX);
                }
                if (firstElement || poolInfo.getY() < diagramInfo.getY()) {
                    diagramInfo.setY(poolInfo.getY());
                }
                if (rightX > diagramInfo.getWidth()) {
                    diagramInfo.setWidth(rightX);
                }
                if (bottomY > diagramInfo.getHeight()) {
                    diagramInfo.setHeight(bottomY);
                }
                firstElement = false;
            }
            displayNode.set("pools", poolArray);

        } else {
            // in initialize with fake x and y to make sure the minimal
            // values are set
            diagramInfo.setX(9999);
            diagramInfo.setY(1000);
        }

        for (org.flowable.bpmn.model.Process process : pojoModel.getProcesses()) {
            processElements(process.getFlowElements(), pojoModel, elementArray, flowArray, collapsedArray,
                    diagramInfo, completedElements, currentElements, breakpoints, null, processInstanceId);

            processArtifacts(process.getArtifacts(), pojoModel, elementArray, flowArray, diagramInfo);
        }

        displayNode.set("elements", elementArray);
        displayNode.set("flows", flowArray);
        displayNode.set("collapsed", collapsedArray);

        displayNode.put("diagramBeginX", diagramInfo.getX());
        displayNode.put("diagramBeginY", diagramInfo.getY());
        displayNode.put("diagramWidth", diagramInfo.getWidth());
        displayNode.put("diagramHeight", diagramInfo.getHeight());
        return displayNode;
    }

    /**
     * @param elementList
     * @param model
     * @param elementArray
     * @param flowArray
     * @param collapsedArray
     * @param diagramInfo
     * @param completedElements ?????????
     * @param currentElements   ?????????
     * @param breakpoints
     * @param collapsedNode
     * @param processInstanceId
     */


    protected void processElements(Collection<FlowElement> elementList, BpmnModel model, ArrayNode elementArray, ArrayNode flowArray,
                                   ArrayNode collapsedArray, GraphicInfo diagramInfo, Set<String> completedElements,
                                   Set<String> currentElements, Collection<String> breakpoints, ObjectNode collapsedNode, String processInstanceId) {

        for (FlowElement element : elementList) {

            ObjectNode elementNode = objectMapper.createObjectNode();
            if (completedElements != null) {
                //j2eefast ??????????????????????????????????????????
                if (element instanceof SequenceFlow) {
                    elementNode.put("completed", (completedElements.contains(((SequenceFlow) element).getSourceRef()) &&
                            completedElements.contains(((SequenceFlow) element).getTargetRef())) || completedElements.contains(((SequenceFlow) element).getSourceRef()) &&
                            currentElements.contains(((SequenceFlow) element).getTargetRef()));
                } else {
                    elementNode.put("completed", completedElements.contains(element.getId()));
                }
            }
            if (!breakpoints.isEmpty()) {
                elementNode.put("breakpoint", breakpoints.contains(element.getId()));
            }
            Collection<String> brokenExecutions = this.debuggerService.getBrokenExecutions(element.getId(), processInstanceId);
            if (!brokenExecutions.isEmpty()) {
                ArrayNode brokenExecutionsNode = elementNode.putArray("brokenExecutions");
                for (String brokenExecution : brokenExecutions) {
                    brokenExecutionsNode.add(brokenExecution);
                }
            }

            if (currentElements != null) {
                if (element instanceof SequenceFlow) {
                    elementNode.put("current", false);
                } else {
                    elementNode.put("current", currentElements.contains(element.getId()));
                }
            }

            if (element instanceof SequenceFlow) {
                SequenceFlow flow = (SequenceFlow) element;
                elementNode.put("id", flow.getId());
                elementNode.put("type", "sequenceFlow");
                elementNode.put("sourceRef", flow.getSourceRef());
                elementNode.put("targetRef", flow.getTargetRef());
                elementNode.put("name", flow.getName());
                List<GraphicInfo> flowInfo = model.getFlowLocationGraphicInfo(flow.getId());
                if (CollectionUtils.isNotEmpty(flowInfo)) {
                    ArrayNode waypointArray = objectMapper.createArrayNode();
                    for (GraphicInfo graphicInfo : flowInfo) {
                        ObjectNode pointNode = objectMapper.createObjectNode();
                        fillGraphicInfo(pointNode, graphicInfo, false);
                        waypointArray.add(pointNode);
                        fillDiagramInfo(graphicInfo, diagramInfo);
                    }
                    elementNode.set("waypoints", waypointArray);

                    String className = element.getClass().getSimpleName();
                    if (propertyMappers.containsKey(className)) {
                        elementNode.set("properties", propertyMappers.get(className).map(element));
                    }

                    if (collapsedNode != null) {
                        ((ArrayNode) collapsedNode.get("flows")).add(elementNode);
                    } else {
                        flowArray.add(elementNode);
                    }
                }

            } else {

                elementNode.put("id", element.getId());
                elementNode.put("name", element.getName());

                GraphicInfo graphicInfo = model.getGraphicInfo(element.getId());
                if (graphicInfo != null) {
                    fillGraphicInfo(elementNode, graphicInfo, true);
                    fillDiagramInfo(graphicInfo, diagramInfo);
                }

                String className = element.getClass().getSimpleName();
                elementNode.put("type", className);
                fillEventTypes(className, element, elementNode);

                if (element instanceof ServiceTask) {
                    ServiceTask serviceTask = (ServiceTask) element;
                    if (ServiceTask.MAIL_TASK.equals(serviceTask.getType())) {
                        elementNode.put("taskType", "mail");

                    } else if ("camel".equals(serviceTask.getType())) {
                        elementNode.put("taskType", "camel");

                    } else if ("mule".equals(serviceTask.getType())) {
                        elementNode.put("taskType", "mule");

                    } else if (ServiceTask.HTTP_TASK.equals(serviceTask.getType())) {
                        elementNode.put("taskType", "http");
                    } else if (ServiceTask.SHELL_TASK.equals(serviceTask.getType())) {
                        elementNode.put("taskType", "shell");
                    }
                }

                if (propertyMappers.containsKey(className)) {
                    elementNode.set("properties", propertyMappers.get(className).map(element));
                }

                if (collapsedNode != null) {
                    ((ArrayNode) collapsedNode.get("elements")).add(elementNode);
                } else {
                    elementArray.add(elementNode);
                }

                if (element instanceof SubProcess) {
                    SubProcess subProcess = (SubProcess) element;
                    ObjectNode newCollapsedNode = collapsedNode;
                    // skip collapsed sub processes
                    if (graphicInfo != null && graphicInfo.getExpanded() != null && !graphicInfo.getExpanded()) {
                        elementNode.put("collapsed", "true");
                        newCollapsedNode = objectMapper.createObjectNode();
                        newCollapsedNode.put("id", subProcess.getId());
                        newCollapsedNode.putArray("elements");
                        newCollapsedNode.putArray("flows");
                        collapsedArray.add(newCollapsedNode);
                    }

                    processElements(subProcess.getFlowElements(), model, elementArray, flowArray, collapsedArray,
                            diagramInfo, completedElements, currentElements, breakpoints, newCollapsedNode, processInstanceId);
                    processArtifacts(subProcess.getArtifacts(), model, elementArray, flowArray, diagramInfo);
                }
            }
        }
    }

    protected void processArtifacts(Collection<Artifact> artifactList, BpmnModel model, ArrayNode elementArray, ArrayNode flowArray, GraphicInfo diagramInfo) {

        for (Artifact artifact : artifactList) {

            if (artifact instanceof Association) {
                ObjectNode elementNode = objectMapper.createObjectNode();
                Association flow = (Association) artifact;
                elementNode.put("id", flow.getId());
                elementNode.put("type", "association");
                elementNode.put("sourceRef", flow.getSourceRef());
                elementNode.put("targetRef", flow.getTargetRef());
                fillWaypoints(flow.getId(), model, elementNode, diagramInfo);
                flowArray.add(elementNode);

            } else {

                ObjectNode elementNode = objectMapper.createObjectNode();
                elementNode.put("id", artifact.getId());

                if (artifact instanceof TextAnnotation) {
                    TextAnnotation annotation = (TextAnnotation) artifact;
                    elementNode.put("text", annotation.getText());
                }

                GraphicInfo graphicInfo = model.getGraphicInfo(artifact.getId());
                if (graphicInfo != null) {
                    fillGraphicInfo(elementNode, graphicInfo, true);
                    fillDiagramInfo(graphicInfo, diagramInfo);
                }

                String className = artifact.getClass().getSimpleName();
                elementNode.put("type", className);

                elementArray.add(elementNode);
            }
        }
    }

    protected void fillWaypoints(String id, BpmnModel model, ObjectNode elementNode, GraphicInfo diagramInfo) {
        List<GraphicInfo> flowInfo = model.getFlowLocationGraphicInfo(id);
        ArrayNode waypointArray = objectMapper.createArrayNode();
        for (GraphicInfo graphicInfo : flowInfo) {
            ObjectNode pointNode = objectMapper.createObjectNode();
            fillGraphicInfo(pointNode, graphicInfo, false);
            waypointArray.add(pointNode);
            fillDiagramInfo(graphicInfo, diagramInfo);
        }
        elementNode.set("waypoints", waypointArray);
    }

    protected void fillEventTypes(String className, FlowElement element, ObjectNode elementNode) {
        if (eventElementTypes.contains(className)) {
            Event event = (Event) element;
            if (CollectionUtils.isNotEmpty(event.getEventDefinitions())) {
                EventDefinition eventDef = event.getEventDefinitions().get(0);
                ObjectNode eventNode = objectMapper.createObjectNode();
                if (eventDef instanceof TimerEventDefinition) {
                    TimerEventDefinition timerDef = (TimerEventDefinition) eventDef;
                    eventNode.put("type", "timer");
                    if (StringUtils.isNotEmpty(timerDef.getTimeCycle())) {
                        eventNode.put("timeCycle", timerDef.getTimeCycle());
                    }
                    if (StringUtils.isNotEmpty(timerDef.getTimeDate())) {
                        eventNode.put("timeDate", timerDef.getTimeDate());
                    }
                    if (StringUtils.isNotEmpty(timerDef.getTimeDuration())) {
                        eventNode.put("timeDuration", timerDef.getTimeDuration());
                    }

                } else if (eventDef instanceof ErrorEventDefinition) {
                    ErrorEventDefinition errorDef = (ErrorEventDefinition) eventDef;
                    eventNode.put("type", "error");
                    if (StringUtils.isNotEmpty(errorDef.getErrorCode())) {
                        eventNode.put("errorCode", errorDef.getErrorCode());
                    }

                } else if (eventDef instanceof SignalEventDefinition) {
                    SignalEventDefinition signalDef = (SignalEventDefinition) eventDef;
                    eventNode.put("type", "signal");
                    if (StringUtils.isNotEmpty(signalDef.getSignalRef())) {
                        eventNode.put("signalRef", signalDef.getSignalRef());
                    }

                } else if (eventDef instanceof MessageEventDefinition) {
                    MessageEventDefinition messageDef = (MessageEventDefinition) eventDef;
                    eventNode.put("type", "message");
                    if (StringUtils.isNotEmpty(messageDef.getMessageRef())) {
                        eventNode.put("messageRef", messageDef.getMessageRef());
                    }
                }
                elementNode.set("eventDefinition", eventNode);
            }
        }
    }

    protected void fillGraphicInfo(ObjectNode elementNode, GraphicInfo graphicInfo, boolean includeWidthAndHeight) {
        commonFillGraphicInfo(elementNode, graphicInfo.getX(), graphicInfo.getY(), graphicInfo.getWidth(), graphicInfo.getHeight(), includeWidthAndHeight);
    }

    protected void commonFillGraphicInfo(ObjectNode elementNode, double x, double y, double width, double height, boolean includeWidthAndHeight) {

        elementNode.put("x", x);
        elementNode.put("y", y);
        if (includeWidthAndHeight) {
            elementNode.put("width", width);
            elementNode.put("height", height);
        }
    }

    protected void fillDiagramInfo(GraphicInfo graphicInfo, GraphicInfo diagramInfo) {
        double rightX = graphicInfo.getX() + graphicInfo.getWidth();
        double bottomY = graphicInfo.getY() + graphicInfo.getHeight();
        double middleX = graphicInfo.getX() + (graphicInfo.getWidth() / 2);
        if (middleX < diagramInfo.getX()) {
            diagramInfo.setX(middleX);
        }
        if (graphicInfo.getY() < diagramInfo.getY()) {
            diagramInfo.setY(graphicInfo.getY());
        }
        if (rightX > diagramInfo.getWidth()) {
            diagramInfo.setWidth(rightX);
        }
        if (bottomY > diagramInfo.getHeight()) {
            diagramInfo.setHeight(bottomY);
        }
    }

    protected List<String> gatherCompletedFlows(Set<String> completedActivityInstances, Set<String> currentActivityinstances, BpmnModel pojoModel) {

        List<String> completedFlows = new ArrayList<>();
        List<String> activities = new ArrayList<>(completedActivityInstances);
        activities.addAll(currentActivityinstances);

        // TODO: not a robust way of checking when parallel paths are active, should be revisited
        // Go over all activities and check if it's possible to match any outgoing paths against the activities
        for (FlowElement activity : pojoModel.getMainProcess().getFlowElements()) {
            if (activity instanceof FlowNode) {
                int index = activities.indexOf(activity.getId());
                if (index >= 0 && index + 1 < activities.size()) {
                    List<SequenceFlow> outgoingFlows = ((FlowNode) activity).getOutgoingFlows();
                    for (SequenceFlow flow : outgoingFlows) {
                        String destinationFlowId = flow.getTargetRef();
                        if (destinationFlowId.equals(activities.get(index + 1))) {
                            completedFlows.add(flow.getId());
                        }
                    }
                }
            }
        }
        return completedFlows;
    }
}
