package com.coma.study.common.file;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {

	public String fileSave(String dir, MultipartFile attach) throws Exception {
		File file = new File(dir);
		if (!file.exists()) file.mkdirs();
		
		String fileName = UUID.randomUUID().toString();
		fileName = fileName + "_" + attach.getOriginalFilename();
		
		file = new File(file, fileName);
		// 1. MultipartFile의 메서드를 사용하는 방법
		// attach.transferTo(file);
		
		// 2. Spring이 지원하는 FileCopyUtils의 메서드를 사용하는 방법
		FileCopyUtils.copy(attach.getBytes(), file);
		
		return fileName;
	}
	
	public boolean fileDelete(String dir, String fileName) throws Exception {
		File file = new File(dir, fileName);
		return file.delete();
	}
}
