/**
 * 
 */
package com.gul.farmerbroker.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.gul.farmerbroker.configuration.ApplicationConfiguration;
import com.gul.farmerbroker.configuration.ApplicationContextHolder;

/**
 * @author Lynn
 *
 */
public class ImageUtil {
	/** 图片序列号全局标记变量 */
	private final static AtomicInteger SEQ = new AtomicInteger(0);

	/** 应用配置信息管理对象 */
	@Autowired
	private static ApplicationConfiguration appConf;

	/**
	 * 图片保存路径以及图片名称，保存路径为相对于图片根目录的路径
	 * 
	 * @param userName
	 * @return
	 */
	public static String getFileName(String userName, String orgName) {
		if (appConf == null) {
			appConf = ApplicationContextHolder.getBean(ApplicationConfiguration.class);
		}
		// 生成目录，并创建目录结构
		StringBuilder sb = new StringBuilder();
		sb.append(appConf.IMAGE_PATH).append(File.pathSeparator);
		sb.append(DateFormatUtils.format(Calendar.getInstance().getTime(), "yyyyMMdd")).append(File.pathSeparator);
		if (StringUtils.isNotBlank(userName)) {
			sb.append(userName).append(File.pathSeparator);
		}
		File f = new File(sb.toString());
		if (!f.exists()) {
			f.mkdirs();
		}

		// 生成文件名
		sb.append(DateFormatUtils.format(Calendar.getInstance().getTime(), "HHmmssSSS") + getSeq())
				.append(orgName.substring(orgName.lastIndexOf(".")));

		return sb.toString();
	}

	private static String getSeq() {
		int seq = SEQ.incrementAndGet() % 1000;
		String seqStr = "00" + seq;
		return seqStr.substring(seqStr.length() - 3);
	}

	/**
	 * 保存文件，直接以multipartFile形式
	 * 
	 * @param multipartFile
	 * @param path
	 *            文件保存绝对路径
	 * @return 返回文件名
	 * @throws IOException
	 */
	public static String saveImg(MultipartFile multipartFile, String path) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
		// String fileName = Constants.getUUID() + ".png";
		String fileName = DateFormatUtils.format(Calendar.getInstance(), "yyyyMMddHHmmssSSS") + ".png";
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
		byte[] bs = new byte[1024];
		int len;
		while ((len = fileInputStream.read(bs)) != -1) {
			bos.write(bs, 0, len);
		}
		bos.flush();
		bos.close();
		return fileName;
	}
}
