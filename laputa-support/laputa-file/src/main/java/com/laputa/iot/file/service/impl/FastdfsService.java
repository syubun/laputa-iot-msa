package com.laputa.iot.file.service.impl;


import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.oss.properties.FileServerProperties;
import com.laputa.iot.common.oss.template.FdfsTemplate;
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
@ConditionalOnProperty(prefix = FileServerProperties.PREFIX, name = "type", havingValue = FileServerProperties.TYPE_FDFS)
public class FastdfsService extends AbstractIFileService {
    @Resource
    private FdfsTemplate fdfsTemplate;

    @Override
    protected String fileType() {
        return FileServerProperties.TYPE_FDFS;
    }

    @Override
    public R uploadFile(MultipartFile file) {
        return R.ok(fdfsTemplate.upload(file));
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
        fdfsTemplate.delete(objectPath);
    }

    @Override
    public void out(String id, OutputStream os) {
    }
}
