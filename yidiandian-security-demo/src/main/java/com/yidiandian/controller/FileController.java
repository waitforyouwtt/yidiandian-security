package com.yidiandian.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.yidiandian.entity.FileInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 凤凰[小哥哥]
 *
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

	private String folder = "E:\\putong\\yidiandian-security\\yidiandian-security-demo\\src\\main\\resources\\upload-folder";

	/**
	 * 上传文件
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/upload")
	public FileInfo upload(MultipartFile file) throws Exception {
		log.info(file.getOriginalFilename());
		log.info("文件的真实名字：{},文件的名字：{},文件的大小：{}",file.getOriginalFilename(),file.getName(),file.getSize());

		File localFile = new File(folder, System.currentTimeMillis() + ".txt");

		file.transferTo(localFile);

		return new FileInfo(localFile.getAbsolutePath());
	}

	/**
	 * 文件下载
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
				OutputStream outputStream = response.getOutputStream();) {
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=test.txt");
			IOUtils.copy(inputStream, outputStream);
			outputStream.flush();
		}
	}
}
