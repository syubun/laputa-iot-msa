package com.laputa.iot.file.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.file.entity.SysFile;
import com.laputa.iot.file.entity.TChunkInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;

/**
 * 文件service
 *
 * @author 作者 sommer.jiang
*/

public interface IFileService extends IService<SysFile> {
	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	R upload(MultipartFile file ) throws Exception;
	
	R<SysFile> findList(Map<String, Object> params);


	void out(String id, OutputStream os);


	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	R uploadFile(MultipartFile file);

	/**
	 * 读取文件
	 * @param bucket 桶名称
	 * @param fileName 文件名称
	 * @param response 输出流
	 */
	void getFile(String bucket, String fileName, HttpServletResponse response);

	/**
	 * 删除文件
	 * @param id
	 * @return
	 */
	Boolean deleteFile(Long id);

	void getUrlFile(String bucket, String fileName, String contentType, HttpServletResponse response);

	void getFileById(Long id, HttpServletResponse response);

	Long saveFile(MultipartFile file,String bucketName, String fileName,String url);

    boolean saveChunk(TChunkInfo chunk);

	ArrayList<Integer> checkChunk(TChunkInfo chunk);
}
