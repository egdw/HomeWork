package com.homework.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/")
	public String index(Map<String,Object> map){
		String topic = HomeWorkTemp.topic;
		map.put("topic", topic);
		return "index";
	}
}
