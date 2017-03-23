package com.homework.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private boolean uploadSuccess = false;

	/**
	 * 文件上传方法
	 */
	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	public String upload(@RequestParam("files") MultipartFile[] files,
			HttpServletRequest request) {
		String remoteAddr = request.getRemoteAddr();
		boolean canUpload = isCanUpload(remoteAddr);
		if (!canUpload) {
			request.setAttribute("error", "您今日上传数量已经达到极限!");
			return "error";
		}
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				boolean b = saveFile(file, request);
				System.out.println(b);
				uploadSuccess = b;
			}
		}
		if (uploadSuccess) {
			return "success";
		} else {
			request.setAttribute("error", "上传失败!请重新上传!");
			return "error";
		}
	}

	public boolean saveFile(MultipartFile file, HttpServletRequest request) {
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				int lastIndexOf = file.getOriginalFilename().lastIndexOf(".");
				if (lastIndexOf != -1) {
					String substring = file.getOriginalFilename().substring(
							lastIndexOf + 1);
					System.out.println(substring);
					if("rar".equals(substring.toLowerCase())){
						StringBuffer sb = new StringBuffer();
						// 文件保存路径
						StringBuffer append = sb
								.append(request.getSession().getServletContext()
										.getRealPath("/")).append("WEB-INF/upload")
								.append(File.separatorChar)
								.append(dateFormat.format(new Date()))
								.append(File.separatorChar).append(UUID.randomUUID())
								.append(file.getOriginalFilename());
						File file2 = new File(append.toString());
						if (!file2.getParentFile().exists()) {
							file2.getParentFile().mkdirs();
						}
						file.transferTo(file2);
						return true;
					}
					if("zip".equals(substring.toLowerCase())){
						StringBuffer sb = new StringBuffer();
						// 文件保存路径
						StringBuffer append = sb
								.append(request.getSession().getServletContext()
										.getRealPath("/")).append("WEB-INF/upload")
								.append(File.separatorChar)
								.append(dateFormat.format(new Date()))
								.append(File.separatorChar).append(UUID.randomUUID())
								.append(file.getOriginalFilename());
						File file2 = new File(append.toString());
						if (!file2.getParentFile().exists()) {
							file2.getParentFile().mkdirs();
						}
						file.transferTo(file2);
						return true;
					}
				} else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean isCanUpload(String remoteAddr) {
		Map<String, Integer> clicks = HomeWorkTemp.clicks;
		Integer object = clicks.get(remoteAddr);
		String tempDate = HomeWorkTemp.tempDate;
		// 先添加第一个上传的人的日期
		if (tempDate == null) {
			tempDate = HomeWorkTemp.format.format(new Date());
		} else {
			// 和但钱时间进行比较
			// 如果不相同就进行时间交换.并且所以的记录全部清零
			String currentTime = HomeWorkTemp.format.format(new Date());
			if (!currentTime.equals(tempDate)) {
				HomeWorkTemp.tempDate = currentTime;
				HomeWorkTemp.clicks.clear();
			}
		}
		if (object == null || object == 0) {
			object = 1;
		} else {
			object++;
		}
		if (object > 4) {
			return false;
		}
		clicks.put(remoteAddr, object);
		return true;
	}

	@RequestMapping(value = "updateTopic", method = RequestMethod.POST)
	public String updateTopic(String topic, HttpSession httpSession,
			HttpServletRequest request) {
		if (topic != null && !topic.isEmpty()
				&& "hdy".equals(httpSession.getAttribute("username"))) {
			HomeWorkTemp.topic = topic;
			return "success";
		}
		request.setAttribute("error", "更改标题失败");
		return "error";
	}
}
