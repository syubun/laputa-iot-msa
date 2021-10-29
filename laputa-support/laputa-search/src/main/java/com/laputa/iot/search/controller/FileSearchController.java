package com.laputa.iot.search.controller;

import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.search.entity.File;
import com.laputa.iot.search.service.FileSearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 文件搜索控制器
 *
 * @author Sommer.Jiang
 * @since 2021/1/10
 */
@RestController
@RequestMapping("searches/files")
public class FileSearchController {

    private final FileSearchService fileSearchService;

    public FileSearchController(FileSearchService fileSearchService) {
        this.fileSearchService = fileSearchService;
    }

    @RequestMapping
    public R<Page<File>> queryFile(@RequestParam String name, @RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        Page<File> files = fileSearchService.findAllByName(name, pageNumber, pageSize);
        return R.ok(files);
    }

}
