package com.example.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceDTO {

    private long invoiceId;

    private String code;

    private CustomerDTO customer;

    private BigDecimal baseAmt;

    private BigDecimal taxAmt;

    private BigDecimal taxPorcentage;

    private BigDecimal totalAmt;

    private String status;

}
