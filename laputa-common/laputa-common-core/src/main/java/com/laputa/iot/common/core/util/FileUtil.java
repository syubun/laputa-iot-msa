package com.laputa.iot.common.core.util;


import org.springframework.core.io.Resource;
import org.springframework.util.Base64Utils;
//import sun.misc.BASE64Encoder;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.Base64;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**	文件处理
*  创建人：Sommer
 * 创建时间：2014年12月23日
 */
public class FileUtil {

	/**
	 * byte to file
	 *
	 * @param buf
	 * @param filePath
	 * @param fileName
	 */
	public static void byte2File(byte[] buf, String filePath, String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory()) {
				dir.mkdirs();
			}
			file = new File(filePath + File.separator + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(buf);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * byte 转化为 InputStream
	 *
	 * @param buf
	 * @return
	 */
	public static final InputStream byte2Input(byte[] buf) {
		return new ByteArrayInputStream(buf);
	}

	public static final String InputStream2String(InputStream inputStream) throws IOException {
		byte[] bytes = FileUtil.input2byte(inputStream);
		return new String(bytes);
	}
	/**
	 * InputStream  转化为 byte
	 *
	 * @param inStream
	 * @return
	 * @throws IOException
	 */
	public static final byte[] input2byte(InputStream inStream)
			throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = inStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		return in2b;
	}

	/**
	 * 获取年月日 FTP 地址
	 *
	 * @return
	 */
	public static String getDateFmtFilePath() {
		String filePath = "";
		Calendar cal = Calendar.getInstance();
		int monthTemp = cal.get(Calendar.MONTH) + 1;
		String year = String.valueOf(cal.get(Calendar.YEAR));//获取年份
		String month = String.valueOf(monthTemp > 9 ? monthTemp : ("0" + monthTemp));//获取月份
		String day = String.valueOf(cal.get(Calendar.DATE) > 9 ? cal.get(Calendar.DATE) : ("0" + cal.get(Calendar.DATE)));//获取日
		filePath = "/" + year + "/" + month + "/" + day;

		return filePath;
	}


	/**
	 * @param filename
	 * @return
	 * @Description: get file extension
	 */
	public static String getExtension(String filename) {
		return getExtension(filename, "");
	}

	/**
	 * @param filename
	 * @param defExt
	 * @return
	 * @Description: get file extension
	 */
	public static String getExtension(String filename, String defExt) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');
			if ((i >-1) && (i < (filename.length() - 1))) {
				return filename.substring(i + 1);
			}
		}
		return defExt;
	}

	/**
	 * @param filename
	 * @return
	 * @Description: get file extension
	 */
	public static String getBaseName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');
			if ((i >-1) && (i < (filename.length() - 1))) {
				return filename.substring(0, i);
			}
		}
		return "";
	}
	
	/**获取文件大小 返回 KB 保留3位小数  没有文件时返回0
	 * @param filepath 文件完整路径，包括文件名
	 * @return
	 */
	public static Double getFilesize(String filepath){
		File backupath = new File(filepath);
		return Double.valueOf(backupath.length())/1000.000;
	}



	
	/**
	 * 创建目录

	 * @return 
	 */
	public static Boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if(!dir.getParentFile().exists()){				//判断有没有父路径，就是判断文件整个路径是否存在
			return dir.getParentFile().mkdirs();		//不存在就全部创建
		}
		return false;
	}

	/**
	 * 删除文件
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt

	 *            String
	 * @return boolean
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myDelFile = new File(filePath);
			myDelFile.delete();
		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 读取到字节数组0
	 * @param filePath //路径
	 * @throws IOException
	 */
	public static byte[] getContent(String filePath) throws IOException {
		File file = new File(filePath);
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length
				&& (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}
		fi.close();
		return buffer;
	}


	// 图片Base64字符串解析头begin部分
	private static final String HEAD_BEGIN = "data:image/";

	// 图片Base64字符串解析头end部分
	private static final String HEAD_END = ";base64,";

	public static String getPicBase64Str(byte[] bytes, String type) {
		Base64.Encoder encoder = Base64.getEncoder();


		String encodedText = encoder.encodeToString(bytes);  // 转换图片为base64编码


		return HEAD_BEGIN + type + HEAD_END + encodedText;
	}

	/**
	 * 读取到字节数组1
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(String filePath) throws IOException {

		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(f));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bos.close();
		}
	}

	/**
	 * 读取到字节数组2
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray2(String filePath) throws IOException {
		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}
		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
				// do nothing
				// System.out.println("reading");
			}
			return byteBuffer.array();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
	 * 

	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray3(String filePath) throws IOException {

		FileChannel fc = null;
		RandomAccessFile rf = null;
		try {
			rf = new RandomAccessFile(filePath, "r");
			fc = rf.getChannel();
			MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0,
					fc.size()).load();
			//System.out.println(byteBuffer.isLoaded());
			byte[] result = new byte[(int) fc.size()];
			if (byteBuffer.remaining() > 0) {
				// System.out.println("remain");
				byteBuffer.get(result, 0, byteBuffer.remaining());
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				rf.close();
				fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}



	// public final static String ExternalStorageDirectory =
	// "/storage/extSdCard";

	static FileOutputStream fos;
	public static String PROJECTPATH;


	public FileUtil() {
		super();
		PROJECTPATH = this.getClass().getResource("/").getPath();
		// TODO Auto-generated constructor stub
//		System.out.println(PROJECTPATH);

	}

	public static void openStream(String dirPath, String fileName) {

		File file;
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		file = new File(dirPath, fileName);
		if (file.exists()) {
			file.delete();
		}
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writeStream(String content) {
		try {
			fos.write(content.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeStream() {
		try {
			fos.flush();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void saveFile(String dirPath, String fileName, String content) {


		File file;
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		file = new File(dirPath, fileName);
		if (file.exists()) {
			file.delete();
		}
		// Log.d("FileUtils", filepath);
		// TODO Auto-generated method stub
		try {

			FileOutputStream os = new FileOutputStream(file);
			os.write(content.getBytes());
			os.flush();
			os.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * restore content to file
	 * @param dirPath
	 * @param fileName
	 * @param content
	 */
	public static void saveFile(String dirPath, String fileName, byte[] content) {


		File file;
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		file = new File(dirPath, fileName);
		if (file.exists()) {
			file.delete();
		}
		// Log.d("FileUtils", filepath);
		// TODO Auto-generated method stub
		try {

			FileOutputStream os = new FileOutputStream(file);
			os.write(content);
			os.flush();
			os.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}






	public static String getFileName(String pathandname) {
		int start = pathandname.lastIndexOf("/");
		int end = pathandname.lastIndexOf(".");
		if (start != -1 && end != -1) {
			return pathandname.substring(start + 1, end);
		} else {
			return null;
		}
	}

	public static File[] getFileDir(String filePath) {
		// System.out.println(filePath);
		File f = new File(filePath);
		File[] files = f.listFiles();

		return files;

	}




	public static void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			}
		} else {

		}
	}


	/**
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件
	 *
	 * @param fileName
	 *            文件名
	 */
	public static void readFileByChars(String fileName) {
		File file = new File(fileName);
		Reader reader = null;
		try {
			System.out.println("以字符为单位读取文件内容，一次读一个字节：");
			// 一次读一个字符
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while ((tempchar = reader.read()) != -1) {
				// 对于windows下，rn这两个字符在一起时，表示一个换行。
				// 但如果这两个字符分开显示时，会换两次行。
				// 因此，屏蔽掉r，或者屏蔽n。否则，将会多出很多空行。
				if (((char) tempchar) != 'r') {
					System.out.print((char) tempchar);
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println("以字符为单位读取文件内容，一次读多个字节：");
			// 一次读多个字符
			char[] tempchars = new char[30];
			int charread = 0;
			reader = new InputStreamReader(new FileInputStream(fileName));
			// 读入多个字符到字符数组中，charread为一次读取字符数
			while ((charread = reader.read(tempchars)) != -1) {
				// 同样屏蔽掉r不显示
				if ((charread == tempchars.length)
						&& (tempchars[tempchars.length - 1] != 'r')) {
					System.out.print(tempchars);
				} else {
					for (int i = 0; i < charread; i++) {
						if (tempchars[i] == 'r') {
							continue;
						} else {
							System.out.print(tempchars[i]);
						}
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	public static String readByReader(File file) {

		FileReader fis = null;
		try {
			fis = new FileReader(file);
			char[] arr = new char[1024 * 1000 * 6];
			int len = fis.read(arr);
			String data = new String(arr, 0, len);
			fis.close();
			return data;
			// System.out.println(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void saveDocument(String name, String content) {
		FileWriter fw;

		try {
			fw = new FileWriter(new File(name));
			fw.write(content);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public static String getDocument(String name) {
		Scanner in = null;
		StringBuilder sb = new StringBuilder();
		try {
			in = new Scanner(new File(name));
			while (in.hasNext()) {
				sb.append(in.nextLine());
				// System.out.println(in.nextLine());
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	private static int uByte(byte b) {
		int i = b;
		if (b < 0) {
			i = (b & 0x7F) + 128;
		}
		return i;

	}

	public static void unZipFiles(String zipPath,String descDir)throws IOException{
		unZipFiles(new File(zipPath), descDir);
	}

	/**
	 * 读取txt里的单行内容
	 * @param fileP  文件路径
	 */
	public static String readTxtFile(String fileP) {
		try {

			String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
			filePath = filePath.replaceAll("file:/", "");
			filePath = filePath.replaceAll("%20", " ");
			filePath = filePath.trim() + fileP.trim();
			if(filePath.indexOf(":") != 1){
				filePath = File.separator + filePath;
			}
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { 		// 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);	// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					return lineTxt;
				}
				read.close();
			}else{
				System.out.println("找不到指定的文件,查看此路径是否正确:"+filePath);
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
		}
		return "";
	}
	/**
	 * 根据url拿取file
	 *
	 * @param url
	 * @param suffix
	 *            文件后缀名
	 * */
	public static File createFileByUrl(String url, String suffix) {
		byte[] byteFile = getImageFromNetByUrl(url);
		if (byteFile != null) {
			File file = getFileFromBytes(byteFile, suffix);
			return file;
		} else {
//			log.info("生成文件失败！");
			return null;
		}
	}

	/**
	 * 根据地址获得数据的字节流
	 *
	 * @param strUrl
	 *            网络连接地址
	 * @return
	 */
	private static byte[] getImageFromNetByUrl(String strUrl) {
		try {
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
			byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
			return btImg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 从输入流中获取数据
	 *
	 * @param inStream
	 *            输入流
	 * @return
	 * @throws Exception
	 */
	private static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}


	public static String readResource(Resource resource) throws Exception {
		InputStream is = resource.getInputStream();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = is.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		is.close();
		byte[] contentBytes = outStream.toByteArray();
		return new String(contentBytes);
	}


	// 创建临时文件
	private static File getFileFromBytes(byte[] b, String suffix) {
		BufferedOutputStream stream = null;
		File file = null;
		try {
			file = File.createTempFile("pattern", "." + suffix);
			System.out.println("临时文件位置："+file.getCanonicalPath());
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}


//	public MultipartFile createImg(String url){
//		try {
//			// File转换成MutipartFile
//			File file = createFileByUrl(url, "jpg");
//			FileInputStream inputStream = new FileInputStream(file);
//			MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
//			//注意这里面填啥，MultipartFile里面对应的参数就有啥，比如我只填了name，则
//			//MultipartFile.getName()只能拿到name参数，但是originalFilename是空。
//			return multipartFile;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//	}
	@SuppressWarnings("rawtypes")
	public static void unZipFiles(File zipFile,String descDir)throws IOException{
		File pathFile = new File(descDir);
		if(!pathFile.exists()){
			pathFile.mkdirs();
		}
		ZipFile zip = new ZipFile(zipFile);
		for(Enumeration entries = zip.entries(); entries.hasMoreElements();){
			ZipEntry entry = (ZipEntry)entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir+zipEntryName).replaceAll("\\*", "/");;
			//判断路径是否存在,不存在则创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if(!file.exists()){
				file.mkdirs();
			}
			//判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if(new File(outPath).isDirectory()){
				continue;
			}
			//输出文件路径信息
			System.out.println(outPath);

			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while((len=in.read(buf1))>0){
				out.write(buf1,0,len);
			}
			in.close();
			out.close();
		}
		System.out.println("******************解压完毕********************");
	}









	public static String getFileContent(File file) {
		Scanner in = null;
		StringBuilder sb = new StringBuilder();
		try {
			in = new Scanner(file);
			while (in.hasNext()) {
				sb.append(in.nextLine());
				// System.out.println(in.nextLine());
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}



	/**
	 * 判断文件是否是图像文件
	 */
	public static boolean isImage(String name) {
		boolean valid = true;
		try {
			Image image = ImageIO.read(new File(name));
			if (image == null) {
				valid = false;
				System.out.println("The file" + name + "could not be opened , it is not an image");
			}
		} catch (IOException ex) {
			valid = false;
			System.out.println("The file" + name + "could not be opened , an error occurred.");
		}
		return valid;
	}

	/**
		* 复制文件流到新的文件
     *
			 * @param inStream 文件流
     * @param file     新文件
     * @return 是否复制成功
     */
	public static boolean copyInputStreamToFile(final InputStream inStream, File file) throws IOException {
		int bytesum = 0;
		int byteread = 0;
		byte[] buffer = new byte[1024];
		FileOutputStream fs = new FileOutputStream(file);
		while ((byteread = inStream.read(buffer)) != -1) {
			bytesum += byteread; //字节数 文件大小
			fs.write(buffer, 0, byteread);
		}
		inStream.close();
		fs.close();
		return true;
	}


	/**
	 * storePath是文件的路径还是目录的路径， 如果storePath是文件路径则删除该文件，
	 * 如果storePath是目录路径则删除该目录下的所有文件
	 *
	 * @param storePath
	 */
	public static void deleteFileOrPath(String storePath) {
		File fileOrPath = new File(storePath);
		if (fileOrPath.exists()) {
			if (fileOrPath.isDirectory()) {
				File files[] = fileOrPath.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			fileOrPath.delete();
		}
	}




	/**
	 * <p>将文件转成base64 字符串</p>
	 * @param path 文件路径
	 * @return
	 * @throws Exception
	 */
	public static String encodeBase64File(String path) throws Exception {
		final Base64.Encoder encoder = Base64.getEncoder();
		File file = new File(path);
		FileInputStream inputFile = new FileInputStream(file);
		byte[] buffer = new byte[(int)file.length()];
		inputFile.read(buffer);
		inputFile.close();

		return encoder.encodeToString(buffer);
	}
	/**
	 * <p>将base64字符解码保存文件</p>
	 * @param base64Code
	 * @param targetPath
	 * @throws Exception
	 */
	public static void decoderBase64File(String base64Code,String targetPath) throws Exception {
		final  Base64.Decoder decoder = Base64.getDecoder();
		byte[] buffer = decoder.decode(base64Code);
		FileOutputStream out = new FileOutputStream(targetPath);
		out.write(buffer);
		out.close();
	}
	/**
	 * <p>将base64字符保存文本文件</p>
	 * @param base64Code
	 * @param targetPath
	 * @throws Exception
	 */
	public static void toFile(String base64Code,String targetPath) throws Exception {
		byte[] buffer = base64Code.getBytes();
		FileOutputStream out = new FileOutputStream(targetPath);
		out.write(buffer);
		out.close();
	}

	/**
	 * base64ToFile
	 * @param filePath
	 * @param base64Data
	 * @return
	 * @throws Exception
	 */
	public static boolean base64ToFile(String filePath, String base64Data)  throws Exception {
		String dataPrix = "";
		String data = "";

		if(base64Data == null || "".equals(base64Data)){
			return false;
		}else{
			String [] d = base64Data.split("base64,");
			if(d != null && d.length == 2){
				dataPrix = d[0];
				data = d[1];
			}else{
				return false;
			}
		}

		// 因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
		byte[] bs = Base64Utils.decodeFromString(data);
		// 使用apache提供的工具类操作流
		org.apache.commons.io.FileUtils.writeByteArrayToFile(new File(filePath), bs);

		return true;
	}

}