package com.coma.study.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.coma.study.board.BoardFileDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileDownloadView extends AbstractView {
	@Value("${app.upload}")
	private String path;

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardFileDTO boardFileDTO = (BoardFileDTO) model.get("boardFile");
		String filePath = path + model.get("boardName").toString();
		
		File file = new File(filePath, boardFileDTO.getSavedName());
		response.setContentLengthLong(file.length()); // 파일의 총 크기
		
		String fileName = URLEncoder.encode(boardFileDTO.getOriginName(), "UTF-8"); // 파일 이름 인코딩
		
		response.setHeader("Content-Disposition", "attachment;fileName=\"" + fileName + "\""); // 헤더 설정
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		FileInputStream fileInputStream = new FileInputStream(file);
		OutputStream outputStream = response.getOutputStream();
		
		try (fileInputStream; outputStream) {
			FileCopyUtils.copy(fileInputStream, outputStream);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
