package com.laputa.iot.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.laputa.iot.file.entity.SysFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 上传存储db
 *
 * @author sommer.jiang
 */
@Mapper
public interface FileMapper extends BaseMapper<SysFile> {

}
