package com.laputa.iot.file.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.oss.properties.FileServerProperties;
import com.laputa.iot.common.oss.template.MinioTemplate;
import com.laputa.iot.file.entity.SysFile;
import com.laputa.iot.file.utils.LaputaFileUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sommer.jiang
 * @date 2021/2/13
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@Service
@ConditionalOnProperty(prefix = FileServerProperties.PREFIX, name = "type", havingValue = FileServerProperties.TYPE_MIOIO)
@Slf4j
public class MinioService extends AbstractIFileService {

    @Resource
    private MinioTemplate template;
    @Resource
    private FileServerProperties fileServerProperties;

    @Override
    protected String fileType() {
        return FileServerProperties.TYPE_MIOIO;
    }

    @Override
    public R uploadFile(MultipartFile file) {
        String fileName = IdUtil.simpleUUID() + StrUtil.DOT + FileUtil.extName(file.getOriginalFilename());
        String bucketName = fileServerProperties.getOss().getBucketName();
        Map<String, String> resultMap = new HashMap<>(4);
        resultMap.put("fileName", fileName);
        resultMap.put("url", String.format("/file/file/%s/%s", bucketName, fileName));
        Long id = -1L;
        boolean isExist = template.bucketExists(bucketName);
        if (!isExist) {
            template.createBucket(bucketName);
        }
        try {
            template.putObject(bucketName, fileName, file.getInputStream());
            String url = template.getObjectURL(bucketName, fileName, 604800);
            //文件管理数据记录,收集管理追踪文件
            id = saveFile(file, bucketName, fileName, url);
        } catch (Exception e) {
            log.error("上传失败", e);
            return R.failed(e.getLocalizedMessage());
        }
        resultMap.put("id", Long.toString(id));
        return R.ok(resultMap);
    }

    @SneakyThrows
    @Override
    public void getFile(String bucket, String fileName, HttpServletResponse response) {
        try (InputStream inputStream = template.getObject(bucket, fileName)) {
            response.setContentType("application/octet-stream; charset=UTF-8");
            IoUtil.copy(inputStream, response.getOutputStream());
        } catch (Exception e) {
            log.error("文件读取异常");
            log.error(e.getLocalizedMessage());
        }
    }


    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteFile(Long id) {
        SysFile sysFile = getById(id);
        if (sysFile == null) {
            return false;
        }

        try {
            template.delete(sysFile.getBucketName(),sysFile.getFileName());
        } catch (Exception e) {
            log.error("文件删除异常", e);
            return false;
        }
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public void getUrlFile(String bucket, String fileName, String contentType, HttpServletResponse response) {
        try (InputStream inputStream = template.getObject(bucket, fileName)) {
            response.setContentType(contentType);
            IoUtil.copy(inputStream, response.getOutputStream());
        } catch (Exception e) {
            log.error("文件读取异常", e);
        }
    }

    @Override
    public void getFileById(Long id, HttpServletResponse response) {
        SysFile sysFile = getById(id);
        if (sysFile == null) {
            return;
        }

        try (InputStream inputStream = template.getObject(sysFile.getBucketName(), sysFile.getFileName())) {
            response.setContentType(sysFile.getContentType());
            IoUtil.copy(inputStream, response.getOutputStream());
        } catch (Exception e) {
            log.error("文件读取异常", e);
        }

    }


    @Override
    protected void deleteFile(String objectPath) {
        LaputaFileUtil.FileObject s3Object = LaputaFileUtil.parsePath(objectPath);
        template.delete(s3Object.getBucketName(), s3Object.getFileName());

    }

    @Override
    public void out(String id, OutputStream os) {
        SysFile fileInfo = baseMapper.selectById(id);
        if (fileInfo != null) {
            LaputaFileUtil.FileObject s3Object = LaputaFileUtil.parsePath(fileInfo.getUrl());
            template.out(s3Object.getBucketName(), s3Object.getFileName(), os);
        }
    }


}
