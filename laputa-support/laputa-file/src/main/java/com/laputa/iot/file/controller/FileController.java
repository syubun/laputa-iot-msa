package com.laputa.iot.file.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.data.mybatis.conditions.Wraps;
import com.laputa.iot.common.data.mybatis.conditions.query.LaputaWrapper;
import com.laputa.iot.common.log.annotation.SysLog;

import com.laputa.iot.common.oss.properties.FileServerProperties;
import com.laputa.iot.common.security.annotation.Inner;

import com.laputa.iot.file.api.dto.UploadResult;
import com.laputa.iot.file.entity.SysFile;
import com.laputa.iot.file.entity.TChunkInfo;
import com.laputa.iot.file.service.IFileService;
import com.laputa.iot.file.utils.LaputaFileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

/**
 * 文件上传
 *
 * @author 作者 sommer.jiang
 */
@RestController
@AllArgsConstructor
@RequestMapping("/file")
@Api(value = "file", tags = "文件管理")
@Slf4j
public class FileController {



    @Resource
    private FileServerProperties fileServerProperties;

    @Resource
    private IFileService fileService;

    /**
     * 文件上传
     * 根据fileType选择上传方式
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/files-anon")
    public R uploadAnon(@RequestParam("file") MultipartFile file) throws Exception {
        return fileService.upload(file);
    }

    /**
     * 文件删除
     *
     * @param id
     */
    @DeleteMapping("/files/{id}")
    public R delete(@PathVariable Long id) {
        try {
            fileService.deleteFile(id);
            return R.ok("操作成功");
        } catch (Exception ex) {
            return R.failed("操作失败");
        }
    }

    /**
     * 文件查询
     *
     * @param params
     * @return
     */
    @GetMapping("/files")
    public R<SysFile> findFiles(@RequestParam Map<String, Object> params) {
        return fileService.findList(params);
    }

    /**
     * 分页查询
     * @param page 分页对象
     * @param file 文件管理
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public R getSysFilePage(Page page, SysFile file) {
//        		LbqWrapper lbqWrapper = new LbqWrapper(file);

		LaputaWrapper<SysFile> query = Wraps.lbQ(file);
        return R.ok(fileService.page(page,query));
    }

    /**
     * 通过id删除文件管理
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除文件管理", notes = "通过id删除文件管理")
    @SysLog("删除文件管理")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_file_del')")
    public R removeById(@PathVariable Long id) {
        return R.ok( fileService.deleteFile(id));
    }

    /**
     * 上传文件 文件名采用uuid,避免原始文件名中带"-"符号导致下载的时候解析出现异常
     * @param file 资源
     * @return R(/ admin / bucketName / filename)
     */
    @PostMapping(value = "/upload")
    public R upload(@RequestPart("file") MultipartFile file) {
        return fileService.uploadFile(file);
    }

    /**
     * 获取文件
     * @param bucket 桶名称
     * @param fileName 文件空间/名称
     * @param response
     * @return
     */
    @Inner(false)
    @GetMapping("/{bucket}/{fileName}")
    public void file(@PathVariable String bucket, @PathVariable String fileName, HttpServletResponse response) {
        fileService.getFile(bucket, fileName, response);
    }

    /**
     * 获取文件
     * @param bucket 桶名称
     * @param fileName 文件空间/名称
     * @param response
     * @return
     */
    @Inner(false)
    @GetMapping("/down/{bucket}/{fileName}")
    public void download(@PathVariable String bucket, @PathVariable String fileName, HttpServletResponse response) {
        fileService.getFile(bucket, fileName, response);
    }


    /**
     * 获取文件
     * @param bucket 桶名称
     * @param fileName 文件空间/名称
     * @param response
     * @return
     */
//	@Inner(false)
    @GetMapping("/url/{bucket}/{fileName}")
    public void fileUrl(@PathVariable String bucket, @PathVariable String fileName, HttpServletResponse response) {
        fileService.getUrlFile(bucket, fileName,"image/png", response);
    }

    /**
     * 获取文件
     * @param id 桶名称
     * @return
     */
    @Inner(false)
    @GetMapping("/getById/{id}")
    public void getFileById(@PathVariable Long  id, HttpServletResponse response) {
        fileService.getFileById(id,  response);
    }


    /**
     * 上传文件块
     * @param chunk
     * @return
     */
    @PostMapping("/chunk")
    public R uploadChunk(TChunkInfo chunk) {


        MultipartFile file = chunk.getUpfile();
        log.info("file originName: {}, chunkNumber: {}", file.getOriginalFilename(), chunk.getChunkNumber());

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(LaputaFileUtil.generatePath(fileServerProperties.getUploadFolder(), chunk));
            //文件写入指定路径
            Files.write(path, bytes);
            if(!fileService.saveChunk(chunk))return R.failed("写入数据库错误");

        } catch (IOException e) {
            e.printStackTrace();
            return R.failed("文件操作错误");
        }
        return R.ok();
    }

    @GetMapping("/chunk")
    public R checkChunk(TChunkInfo chunk, HttpServletResponse response) {

        //默认返回其他状态码，前端不进去checkChunkUploadedByResponse函数，正常走标准上传
        response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);

        String file = fileServerProperties.getUploadFolder() + "/" + chunk.getIdentifier() + "/" + chunk.getFilename();

        //先判断整个文件是否已经上传过了，如果是，则告诉前端跳过上传，实现秒传
        if(LaputaFileUtil.fileExists(file)) {
            UploadResult ur = new UploadResult();
            ur.setSkipUpload(true);
            ur.setLocation(file);
            ur.setMessage("完整文件已存在，直接跳过上传，实现秒传");
            return R.ok(ur);
        }

        //如果完整文件不存在，则去数据库判断当前哪些文件块已经上传过了，把结果告诉前端，跳过这些文件块的上传，实现断点续传
        ArrayList<Integer> list = fileService.checkChunk(chunk);
        if (list !=null && list.size() > 0) {
            UploadResult ur = new UploadResult();
            ur.setSkipUpload(false);
            ur.setUploadedChunks(list);
            response.setStatus(HttpServletResponse.SC_OK);
            ur.setMessage("部分文件块已存在，继续上传剩余文件块，实现断点续传");
            return  R.ok(ur);
        }
        return R.failed();
    }

    @PostMapping("/mergeFile")
    public R mergeFile(@RequestBody SysFile fileInfoVO){



        //前端组件参数转换为model对象
        SysFile fileInfo = new SysFile();
        fileInfo.setFileName(fileInfoVO.getFileName());
        fileInfo.setId(fileInfoVO.getId());
        fileInfo.setId(fileInfoVO.getId());
        fileInfo.setFileSize(fileInfoVO.getFileSize());
//        fileInfo.setRefProjectId(fileInfoVO.getRefProjectId());

        //进行文件的合并操作
        String filename = fileInfo.getFileName();
        String file = fileServerProperties.getUploadFolder()+ "/" + fileInfo.getId() + "/" + filename;
        String folder = fileServerProperties.getUploadFolder() + "/" + fileInfo.getId();
        R fileSuccess = LaputaFileUtil.merge(file, folder, filename);

        fileInfo.setUrl(file);

        //文件合并成功后，保存记录至数据库
        if(fileSuccess.getIsSuccess()) {
            if(fileService.save(fileInfo)) return R.ok();
        }

        //如果已经存在，则判断是否同一个项目，同一个项目的不用新增记录，否则新增
//        if("300".equals(fileSuccess)) {
//            List<SysFile> tfList = fileService.query().eq(fileInfo.getFileName());
//            if(tfList != null) {
//                if(tfList.size() == 0 || (tfList.size() > 0 && !fileInfo.getRefProjectId().equals(tfList.get(0).getRefProjectId()))) {
//                    if(fileService.save(fileInfo)) return R.ok();
//                }
//            }
//        }

        return R.failed();
    }
}
