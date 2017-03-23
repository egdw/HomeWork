package com.homework.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ListController {

	@RequestMapping("list")
	public String entryList(String username, String password,
			Map<String, Object> map, HttpServletRequest request,
			HttpSession httpSession) {
		if (username == null || password == null || username.isEmpty()
				|| password.isEmpty()) {
			return "error";
		}
		if (username.equals("hdy") && password.equals("hzkjzyjsxy")) {
			ArrayList<FileModel> list = getFiles(request);
			httpSession.setAttribute("username", "hdy");
			map.put("list", list);
			map.put("topic", HomeWorkTemp.topic);
			return "list";
		}
		return "error";
	}

	public ArrayList<FileModel> getFiles(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		StringBuffer append = sb
				.append(request.getSession().getServletContext()
						.getRealPath("/")).append("WEB-INF/upload")
				.append(File.separatorChar);
		File file = new File(append.toString());
		File[] listFiles = file.listFiles();
		if (listFiles == null || listFiles.length == 0) {
			return null;
		}
		ArrayList<FileModel> arrayList = new ArrayList<FileModel>();
		for (int i = 0; i < listFiles.length; i++) {
			if (listFiles[i].getName().substring(0, 1).equals(".")) {
				continue;
			}
			FileModel fileModel = new FileModel();
			fileModel.setFileName(listFiles[i].getName());
			fileModel.setFile(listFiles[i]);
			arrayList.add(fileModel);
		}
		return arrayList;
	}
}
