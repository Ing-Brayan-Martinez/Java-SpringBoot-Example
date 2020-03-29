package com.example.controller;

import com.example.service.HomeService;
import com.example.service.dto.HomeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public final class HomeController {

    @Resource
    private HomeService homeService;

    @GetMapping("/homes")
    public List<HomeDTO> index() {
        return homeService.findAll();
    }

}
