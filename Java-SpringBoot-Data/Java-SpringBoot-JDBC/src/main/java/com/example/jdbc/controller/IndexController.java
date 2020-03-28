package com.example.jdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public final class IndexController {

	@GetMapping("/")
	public String index() {
		return "redirect:/swagger-ui.html";
	}

}
