package com.data.table.ViewApp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.table.ViewApp.doa.DatabasesAccess;

@Controller
public class ViewController {
	@Autowired
	DatabasesAccess databasesAccess;

	@GetMapping("/test")
	@ResponseBody
	public Map<String, Object> getQueryResult() {
		return databasesAccess.fetchUsers();
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("name", "View App");
		return "index.html";
	}
}
