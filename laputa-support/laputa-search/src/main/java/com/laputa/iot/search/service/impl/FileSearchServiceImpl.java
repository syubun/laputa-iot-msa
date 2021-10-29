package com.laputa.iot.search.service.impl;


import com.laputa.iot.search.entity.File;
import com.laputa.iot.search.repository.FileSearchRepository;
import com.laputa.iot.search.service.FileSearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 文件搜索服务实现类
 *
 * @author Sommer.Jiang
 * @since 2021/1/10
 */
@Service
public class FileSearchServiceImpl implements FileSearchService {

    private final FileSearchRepository fileSearchRepository;

    public FileSearchServiceImpl(FileSearchRepository fileSearchRepository) {
        this.fileSearchRepository = fileSearchRepository;
    }

    @Override
    public Page<File> findAllByName(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return fileSearchRepository.findAllByName(name, pageable);
    }

}
