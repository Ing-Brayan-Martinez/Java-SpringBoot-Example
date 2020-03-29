package com.example.controller;

import com.example.domain.Invoice;
import com.example.service.InvoiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public final class InvoiceRestController {

    @Resource
    private InvoiceService invoiceService;

    @GetMapping("/invoices")
    public List<Invoice> index() {
        return invoiceService.findAllInvoice();
    }

}
