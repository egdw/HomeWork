package com.homework.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DownloadController {

	@RequestMapping(value = "download")
	public void download(String fileName, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		StringBuffer append = sb
				.append(request.getSession().getServletContext()
						.getRealPath("/")).append("WEB-INF/upload")
				.append(File.separatorChar).append(fileName)
				.append(File.separatorChar);
		StringBuffer append2= sb2
				.append(request.getSession().getServletContext()
						.getRealPath("/")).append("WEB-INF/zip")
				.append(File.separatorChar).append(fileName).append(".zip");
		ZipTools zipTools = new ZipTools();
		System.out.println(append2.toString());
		System.out.println(append.toString());
		zipTools.compress(append2.toString(), append.toString(), false);
		File file = new File(append2.toString());
		System.out.println(file.exists());
		if (file.exists()) {
			response.setContentType("application/force-download");// 设置强制下载不打开
			response.addHeader("Content-Disposition", "attachment;fileName="
					+ fileName+".zip");// 设置文件名
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
