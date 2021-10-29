package com.laputa.iot.file.config;

import cn.hutool.core.util.StrUtil;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.oss.properties.FileServerProperties;
import com.laputa.iot.file.entity.SysFile;
import com.laputa.iot.file.service.impl.AbstractIFileService;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * FastDFS配置
 *
 * @author sommer.jiang
 */
@Configuration
@ConditionalOnProperty(name = "laputa.file-server.type", havingValue = "fastdfs")
public class FastdfsAutoConfigure {
    @Autowired
    private FileServerProperties fileProperties;

    @Service
    public class FastdfsServiceImpl extends AbstractIFileService {
        @Autowired
        private FastFileStorageClient storageClient;

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


//        protected void uploadFile(MultipartFile file, FileInfo fileInfo) throws Exception {
//            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
//            fileInfo.setUrl("http://" + fileProperties.getFdfs().getWebUrl() + "/" + storePath.getFullPath());
//            fileInfo.setPath(storePath.getFullPath());
//        }


        protected boolean deleteFile(SysFile fileInfo) {
            if (fileInfo != null && StrUtil.isNotEmpty(fileInfo.getUrl())) {
                StorePath storePath = StorePath.parseFromUrl(fileInfo.getUrl());
                storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
            }
            return true;
        }

        @Override
        public void out(String id, OutputStream os) {

        }
    }
}
