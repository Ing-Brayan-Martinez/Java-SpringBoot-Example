package com.example.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "invoice_line", schema="public")
public class InvoiceLine extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceLineId;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @Column
    private String description;

    @Column(nullable = false, columnDefinition = "numeric default 0")
    private Integer unit;

    @Column(nullable = false, columnDefinition = "numeric default 0")
    private BigDecimal itemAmt;

    @Column(nullable = false, columnDefinition = "numeric default 0")
    private BigDecimal totalAmt;

    @Column(nullable = false, length = 1)
    private String status;

}
