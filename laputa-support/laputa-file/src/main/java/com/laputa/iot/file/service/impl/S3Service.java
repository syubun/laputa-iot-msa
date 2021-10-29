package com.laputa.iot.file.service.impl;

import cn.hutool.core.util.StrUtil;

import com.laputa.iot.common.core.constant.CommonConstants;
import com.laputa.iot.common.core.base.dto.R;

import com.laputa.iot.common.oss.properties.FileServerProperties;
import com.laputa.iot.common.oss.template.OssTemplate;

import com.laputa.iot.file.entity.SysFile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * @author sommer.jiang
 * @date 2021/2/13
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@Service
@ConditionalOnProperty(prefix = FileServerProperties.PREFIX, name = "type", havingValue = FileServerProperties.TYPE_S3)
public class S3Service extends AbstractIFileService {

    @Resource
    private OssTemplate s3Template;

    @Override
    protected String fileType() {
        return FileServerProperties.TYPE_S3;
    }

    @Override
    public R uploadFile(MultipartFile file) {
        return R.ok(s3Template.upload(file));
    }

    @Override
    public void getFile(String bucket, String fileName, HttpServletResponse response) {

    }

    @Override
    public Boolean deleteFile(Long id) {
        return null;
    }

    @Override
    public void getUrlFile(String bucket, String fileName, String contentType, HttpServletResponse response) {

    }

    @Override
    public void getFileById(Long id, HttpServletResponse response) {

    }



    @Override
    protected void deleteFile(String objectPath) {
        S3Object s3Object = parsePath(objectPath);
        s3Template.delete(s3Object.bucketName, s3Object.objectName);
    }

    @Override
    public void out(String id, OutputStream os) {
        SysFile fileInfo = baseMapper.selectById(id);
        if (fileInfo != null) {
            S3Object s3Object = parsePath(fileInfo.getUrl());
            s3Template.out(s3Object.bucketName, s3Object.objectName, os);
        }
    }

    @Setter
    @Getter
    private class S3Object {
        private String bucketName;
        private String objectName;
    }

    private S3Object parsePath(String path) {
        S3Object s3Object = new S3Object();
        if (StrUtil.isNotEmpty(path)) {
            int splitIndex = path.lastIndexOf(CommonConstants.PATH_SPLIT);
            if (splitIndex != -1) {
                s3Object.bucketName = path.substring(0, splitIndex);
                s3Object.objectName = path.substring(splitIndex + 1);
            }
        }
        return s3Object;
    }
}
