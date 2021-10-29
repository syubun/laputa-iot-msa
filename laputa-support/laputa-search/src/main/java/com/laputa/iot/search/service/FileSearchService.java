package com.laputa.iot.search.service;


import com.laputa.iot.search.entity.File;
import org.springframework.data.domain.Page;

/**
 * 文件服务搜索服务接口
 *
 * @author Sommer.Jiang
 * @since 2021/1/10
 */
public interface FileSearchService {

    /**
     * 通过文件名搜索文件列表
     *
     * @param name       文件名
     * @param pageNumber 页数
     * @param pageSize   页大小
     * @return 文件列表
     */
    Page<File> findAllByName(String name, int pageNumber, int pageSize);

}
