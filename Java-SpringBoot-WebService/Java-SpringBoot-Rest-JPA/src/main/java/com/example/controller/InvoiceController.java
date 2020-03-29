package com.example.controller;

import com.example.service.InvoiceService;
import com.example.service.dto.InvoiceDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public final class InvoiceController {

    @Resource
    private InvoiceService invoiceService;

    @GetMapping("/invoices")
    public List<InvoiceDTO> index() {
        return invoiceService.findAll();
    }

}
