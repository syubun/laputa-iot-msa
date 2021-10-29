package com.laputa.iot.file.config;

import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.oss.properties.FileServerProperties;
import com.laputa.iot.file.entity.SysFile;
import com.laputa.iot.file.service.impl.AbstractIFileService;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * 七牛云配置
 *
 * @author 作者 owen E-mail: 624191343@qq.com
 */
@Configuration
@ConditionalOnProperty(name = "laputa.file-server.type", havingValue = "qiniu")
public class QiniuOSSAutoConfigure {
    @Autowired
    private FileServerProperties fileProperties;

    /**
     * 华东机房
     */
    @Bean
    public com.qiniu.storage.Configuration qiniuConfig() {
        return new com.qiniu.storage.Configuration(Zone.zone2());
    }

    /**
     * 构建一个七牛上传工具实例
     */
    @Bean
    public UploadManager uploadManager() {
        return new UploadManager(qiniuConfig());
    }

    /**
     * 认证信息实例
     *
     * @return
     */
    @Bean
    public Auth auth() {
        return Auth.create(fileProperties.getOss().getAccessKey(), fileProperties.getOss().getAccessKeySecret());
    }

    /**
     * 构建七牛空间管理实例
     */
    @Bean
    public BucketManager bucketManager() {
        return new BucketManager(auth(), qiniuConfig());
    }

    @Service
    public class QiniuOssServiceImpl extends AbstractIFileService {
        @Autowired
        private UploadManager uploadManager;
        @Autowired
        private BucketManager bucketManager;
        @Autowired
        private Auth auth;

        @Override
        protected String fileType() {
            return fileProperties.getType();
        }

        @Override
        public R uploadFile(MultipartFile file) {
            return null;
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
        public Long saveFile(MultipartFile file, String bucketName, String fileName, String url) {
            return null;
        }

        @Override
        protected void deleteFile(String objectPath) {

        }


        protected void uploadFile(MultipartFile file, SysFile fileInfo) throws Exception {
            // 调用put方法上传
            uploadManager.put(file.getBytes(), fileInfo.getFileName(), auth.uploadToken(fileProperties.getOss().getBucketName()));
            fileInfo.setUrl(fileProperties.getOss().getEndpoint() + "/" + fileInfo.getFileName());
//            fileInfo.setUrl(fileProperties.getOss().getEndpoint() + "/" + fileInfo.getFileName());
        }


        protected boolean deleteFile(SysFile fileInfo) {
            try {
                Response response = bucketManager.delete(fileProperties.getOss().getBucketName(), fileInfo.getUrl());
                int retry = 0;
                while (response.needRetry() && retry++ < 3) {
                    response = bucketManager.delete(fileProperties.getOss().getBucketName(), fileInfo.getUrl());
                }
            } catch (QiniuException e) {
                return false;
            }
            return true;
        }

        @Override
        public void out(String id, OutputStream os) {

        }
    }
}
