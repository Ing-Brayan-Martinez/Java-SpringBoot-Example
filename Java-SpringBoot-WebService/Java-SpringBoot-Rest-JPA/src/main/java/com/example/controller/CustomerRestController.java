package com.example.controller;

import com.example.service.CustomerService;
import com.example.service.dto.CustomerDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public final class CustomerRestController {

    @Resource
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<CustomerDTO> index() {
        return customerService.findAll();
    }

}
