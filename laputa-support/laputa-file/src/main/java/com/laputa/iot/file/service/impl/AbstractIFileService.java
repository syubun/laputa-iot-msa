package com.laputa.iot.file.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.common.data.mybatis.conditions.Wraps;
import com.laputa.iot.common.data.mybatis.conditions.query.LaputaWrapper;
import com.laputa.iot.common.security.util.SecurityUtils;
import com.laputa.iot.file.entity.SysFile;
import com.laputa.iot.file.entity.TChunkInfo;
import com.laputa.iot.file.mapper.FileMapper;
import com.laputa.iot.file.service.IFileService;
import com.laputa.iot.file.utils.LaputaFileUtil;
import com.laputa.iot.common.oss.model.ObjectInfo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;

/**
 * AbstractIFileService 抽取类
 *
 * 根据 laputa.file-server.type 实例化具体对象
 *
 * @author 作者 sommer.jiang
 */
@Slf4j
public abstract class AbstractIFileService extends ServiceImpl<FileMapper, SysFile> implements IFileService {
    private static final String FILE_SPLIT = ".";

    @Override
    public R upload(MultipartFile file) {
        SysFile fileInfo = LaputaFileUtil.getFileInfo(file);
        if (!fileInfo.getOriginal().contains(FILE_SPLIT)) {
            throw new IllegalArgumentException("缺少后缀名");
        }
        R<ObjectInfo> fileresult = uploadFile(file);
        fileInfo.setUrl(fileresult.getData().getObjectUrl());
        // 设置文件来源
        fileInfo.setSource(fileType());
        // 将文件信息保存到数据库
        baseMapper.insert(fileInfo);

        return R.ok(fileInfo);
    }

    /**
     * 文件来源
     *
     * @return
     */
    protected abstract String fileType();

    /**
     * 上传文件
     *
     * @param file
     */
    public abstract R uploadFile(MultipartFile file);

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

    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveFile(MultipartFile file,String bucketname, String fileName, String url) {
        SysFile sysFile = new SysFile();
        sysFile.setFileName(fileName);
        sysFile.setOriginal(file.getOriginalFilename());
        sysFile.setFileSize(file.getSize());
        sysFile.setFileExten(cn.hutool.core.io.FileUtil.extName(file.getOriginalFilename()));
        sysFile.setBucketName(bucketname);
        sysFile.setCreateUser(SecurityUtils.getUser().getId());
        sysFile.setContentType(file.getContentType());
        sysFile.setUrl(url);
        sysFile.setVersion(1);
        sysFile.setSource(fileType());

        sysFile.setIsImg(sysFile.getContentType().startsWith("image/"));
        this.save(sysFile);
        LaputaWrapper<SysFile> query = Wraps.lbQ();
        query.eq(SysFile::getFileName,fileName);
        sysFile = this.getOne(query);
        if(sysFile==null){
            return -1L;
        }
        return sysFile.getId();
    }

    /**
     * 删除文件
     * @param id 文件id
     */

    public void delete(String id) {
        SysFile fileInfo = baseMapper.selectById(id);
        if (fileInfo != null) {
            baseMapper.deleteById(fileInfo.getId());
            this.deleteFile(fileInfo.getFileName());
        }
    }

    /**
     * 删除文件资源
     *
     * @param objectPath 文件路径
     */
    protected abstract void deleteFile(String objectPath);

    @Override
    public R findList(Map<String, Object> params) {
        Page<SysFile> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
//        List<SysFile> list = baseMapper.findList(page, params);
//        return R.<List<SysFile>>builder().data(list).code(0).build();
        return null;
    }

    @Override
    public void out(String id, OutputStream os) {

    }

    @Override
    public boolean saveChunk(TChunkInfo chunk) {
        return  true;
    }

    @Override
    public ArrayList<Integer> checkChunk(TChunkInfo chunk) {
//        return baseMapper.selectChunkNumbers(chunk);
        return null;
    }


}
