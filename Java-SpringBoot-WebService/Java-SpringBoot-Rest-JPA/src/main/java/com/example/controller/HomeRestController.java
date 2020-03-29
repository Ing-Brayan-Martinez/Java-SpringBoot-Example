package com.example.controller;

import com.example.domain.Home;
import com.example.service.HomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public final class HomeRestController {

    @Resource
    private HomeService homeService;

    @GetMapping("/homes")
    public List<Home> index() {
        return homeService.findAllHomes();
    }

}
