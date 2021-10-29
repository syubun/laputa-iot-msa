package com.laputa.iot.workflow.controller;


import com.laputa.iot.common.core.base.BaseController;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.workflow.api.dto.PageDTO;
import com.laputa.iot.workflow.api.dto.ProcessDefinitionDTO;
import com.laputa.iot.workflow.api.vo.DeploymentVO;
import com.laputa.iot.workflow.service.ActProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipInputStream;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/5 13:48
 * @Description: 流程定义
 */
@RestController
@RequestMapping("/runtime/process-definitions")
@Api(value = "Process", tags = {"流程定义"}, description = "注意：如果部署流程定义时指定了tenantId,那么在启动流程实例的时候，也需要传递tenantId，否则报错")
@RequiredArgsConstructor
public class ProcessController extends BaseController {

    private final ActProcessService actProcessService;

    @GetMapping
    @ApiOperation(value = "分页查询流程定义实例")
    public R page(ProcessDefinitionDTO processDefinitionDTO, PageDTO page){
        return success(actProcessService.listDefinitionPage(processDefinitionDTO,page));
    }

    @DeleteMapping(value = "/{deploymentId}")
    @ApiOperation(value = "删除流程部署", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deploymentId", value = "流程部署ID", required = true, dataType = "String")
    })
    public R delete(@PathVariable String deploymentId){
        actProcessService.deleteDeployment(deploymentId,true);
        return success("删除成功");
    }

    @PostMapping(value = "/files/zip", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "部署压缩包形式的模板(.zip .bar)，主子流程定义部署", notes = "可用于一次性部署多个资源文件（.bpmn .drl .form等）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "主模板名称（模板ID）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "category", value = "模板类别", required = false, dataType = "String"),
            @ApiImplicitParam(name = "tenantId", value = "系统标识", required = false, dataType = "String"),
            @ApiImplicitParam(name = "file", value = "模板文件", required = true, dataType = "__file")
    })
    public R deployByZip(String name, String category, String tenantId, MultipartFile file) {
        DeploymentVO deployment = null;
        try (ZipInputStream zipIn = new ZipInputStream(file.getInputStream(), Charset.forName("UTF-8"))) {
            deployment = actProcessService.deploy(name, category, tenantId, zipIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success(deployment);
    }

    @PostMapping(value = "/file", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "部署流程模板文件", notes = "模板与工作流微服务解耦，模板文件可来自网络中某个位置，也可以来自业务项目。" +
            "tenantId用于记录流程定义归属于哪个业务系统，实际使用中，可以为每个系统设置一个固定标识，文件的结尾必须要是.bpmn或者bpmn.xml")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "模板名称（模板ID）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "category", value = "模板类别", required = false, dataType = "String"),
            @ApiImplicitParam(name = "tenantId", value = "系统标识", required = false, dataType = "String"),
            @ApiImplicitParam(name = "file", value = "模板文件", required = true, dataType = "__file")
    })
    public R deployByInputStream(String name, String tenantId, String category,String key,@RequestParam("file") MultipartFile file) {
        DeploymentVO deploy = null;
        if (actProcessService.exist(name)) {
            return fail("模板名称重复");
        } else {
            try (InputStream in = file.getInputStream()) {
                deploy = actProcessService.deploy(name, tenantId, category, in);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return success(deploy);
    }

    @GetMapping("/resource")
    @ApiOperation(value = "查看流程部署图片", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "procDefId", value = "流程定义ID", required = false, dataType = "String"),
            @ApiImplicitParam(name = "proInsId", value = "流程实例ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "resType", value = "资源类型(xml|image)", required = true, dataType = "String")
    })
    public void resourceRead(String procDefId, String proInsId, String resType, HttpServletResponse response) throws Exception {
        InputStream resourceAsStream = actProcessService.resourceRead(procDefId, proInsId, resType);
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }

    @GetMapping("/resource/downloadXml/{deploymentId}")
    @ApiOperation(value = "下载xml", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deploymentId", value = "流程定义ID", required = true, dataType = "String")
    })
    public void downloadXml(HttpServletResponse response, HttpServletRequest request,@PathVariable("deploymentId") String processDefinitionId){
        actProcessService.downloadXml(response,request,processDefinitionId);
    }
}
