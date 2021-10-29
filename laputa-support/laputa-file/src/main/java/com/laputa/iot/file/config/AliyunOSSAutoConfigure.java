package com.laputa.iot.file.config;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.oss.properties.FileServerProperties;
import com.laputa.iot.file.entity.SysFile;
import com.laputa.iot.file.service.impl.AbstractIFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * 阿里云配置
 *
 * @author 作者 sommer.jiang
 */
@Configuration
@ConditionalOnProperty(name = "laputa.file-server.type", havingValue = "aliyun")
public class AliyunOSSAutoConfigure {
    @Autowired
    private FileServerProperties fileProperties;

    /**
     * 阿里云文件存储client
     * 只有配置了aliyun.oss.access-key才可以使用
     */
    @Bean
    public OSSClient ossClient() {
        OSSClient ossClient = new OSSClient(fileProperties.getOss().getEndpoint()
                , new DefaultCredentialProvider(fileProperties.getOss().getAccessKey(), fileProperties.getOss().getAccessKeySecret())
                , null);
        return ossClient;
    }

    @Service
    public class AliyunOssServiceImpl extends AbstractIFileService {
        @Autowired
        private OSSClient ossClient;

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
        public Long saveFile(MultipartFile file,String bucket, String fileName, String url) {
            return null;
        }

        @Override
        protected void deleteFile(String objectPath) {

        }


        protected void uploadFile(MultipartFile file, SysFile fileInfo) throws Exception {
            ossClient.putObject(fileProperties.getOss().getBucketName(), fileInfo.getFileName(), file.getInputStream());
            fileInfo.setUrl(fileProperties.getOss().getCustomDomain() + "/" + fileInfo.getFileName());
        }


        protected boolean deleteFile(SysFile fileInfo) {
            ossClient.deleteObject(fileProperties.getOss().getBucketName(), fileInfo.getFileName());
            return true;
        }

        @Override
        public void out(String id, OutputStream os) {

        }
    }
}
