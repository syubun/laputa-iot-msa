package com.laputa.iot.file.utils;

import cn.hutool.core.util.StrUtil;
import com.laputa.iot.common.core.constant.CommonConstants;
import com.laputa.iot.common.core.base.dto.R;
import com.laputa.iot.file.entity.SysFile;
import com.laputa.iot.file.entity.TChunkInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

/**
 * 文件工具类
 *
 * @author 作者 sommer.jiang
 */
@Slf4j
public class LaputaFileUtil {
	private LaputaFileUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static SysFile getFileInfo(MultipartFile file) {
		SysFile fileInfo = new SysFile();

		fileInfo.setOriginal(file.getOriginalFilename());
		fileInfo.setContentType(file.getContentType());
		fileInfo.setIsImg(fileInfo.getContentType().startsWith("image/"));
		fileInfo.setFileSize(file.getSize());

		return fileInfo;
	}

	/**
	 * 文件的md5
	 *
	 * @param inputStream
	 * @return
	 */
	public static String fileMd5(InputStream inputStream) {
		try {
			return DigestUtils.md5Hex(inputStream);
		} catch (IOException e) {
			log.error("fileMd5-error", e);
		}
		return null;
	}

	public static String saveFile(MultipartFile file, String path) {
		try {
			File targetFile = new File(path);
			if (targetFile.exists()) {
				return path;
			}
			if (!targetFile.getParentFile().exists()) {
				targetFile.getParentFile().mkdirs();
			}
			file.transferTo(targetFile);
			return path;
		} catch (Exception e) {
			log.error("saveFile-error", e);
		}
		return null;
	}

	public static boolean deleteFile(String pathname) {
		File file = new File(pathname);
		if (file.exists()) {
			boolean flag = file.delete();
			if (flag) {
				File[] files = file.getParentFile().listFiles();
				if (files == null || files.length == 0) {
					file.getParentFile().delete();
				}
			}
			return flag;
		}
		return false;
	}

	@Setter
	@Getter
	public static class FileObject {
		private String bucketName;
		private String fileName;
	}

	public static FileObject parsePath(String path) {
		FileObject s3Object = new FileObject();
		if (StrUtil.isNotEmpty(path)) {
			int splitIndex = path.lastIndexOf(CommonConstants.PATH_SPLIT);
			if (splitIndex != -1) {
				s3Object.bucketName = path.substring(0, splitIndex);
				s3Object.fileName = path.substring(splitIndex + 1);
			}
		}
		return s3Object;
	}

	public static String generatePath(String uploadFolder, TChunkInfo chunk) {
		StringBuilder sb = new StringBuilder();
		sb.append(uploadFolder).append("/").append(chunk.getIdentifier());
		//判断uploadFolder/identifier 路径是否存在，不存在则创建
		if (!Files.isWritable(Paths.get(sb.toString()))) {
			log.info("path not exist,create path: {}", sb.toString());
			try {
				Files.createDirectories(Paths.get(sb.toString()));
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}

		return sb.append("/")
				.append(chunk.getFilename())
				.append("-")
				.append(chunk.getChunkNumber()).toString();
	}

	/**
	 * 文件合并
	 *
	 * @param targetFile
	 * @param folder
	 */

	/**
	 *
	 * @param file
	 * @param folder
	 * @param filename
	 * @return
	 */
	public static R merge(String file, String folder, String filename){
		//默认合并成功

		try {
			//先判断文件是否存在
			if(fileExists(file)) {
				//文件已存在
				return	R.failed("文件已存在");
			}else {
				//不存在的话，进行合并
				Files.createFile(Paths.get(file));

				Files.list(Paths.get(folder))
						.filter(path -> !path.getFileName().toString().equals(filename))
						.sorted((o1, o2) -> {
							String p1 = o1.getFileName().toString();
							String p2 = o2.getFileName().toString();
							int i1 = p1.lastIndexOf("-");
							int i2 = p2.lastIndexOf("-");
							return Integer.valueOf(p2.substring(i2)).compareTo(Integer.valueOf(p1.substring(i1)));
						})
						.forEach(path -> {
							try {
								//以追加的形式写入文件
								Files.write(Paths.get(file), Files.readAllBytes(path), StandardOpenOption.APPEND);
								//合并后删除该块
								Files.delete(path);
							} catch (IOException e) {
								log.error(e.getMessage(), e);
							}
						});
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			//合并失败

			return	R.failed("合并失败");
		}

		return R.ok();
	}

	/**
	 * 根据文件的全路径名判断文件是否存在
	 * @param file
	 * @return
	 */
	public static boolean fileExists(String file) {
		boolean fileExists = false;
		Path path = Paths.get(file);
		fileExists = Files.exists(path,new LinkOption[]{ LinkOption.NOFOLLOW_LINKS});
		return fileExists;
	}
}
