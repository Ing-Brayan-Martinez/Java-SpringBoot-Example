package com.example.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "invoice", schema="public")
public class Invoice extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoiceId;

    @Column(nullable = false, length = 100)
    private String code;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(nullable = false, columnDefinition = "numeric default 0")
    private BigDecimal baseAmt;

    @Column(nullable = false, columnDefinition = "numeric default 0")
    private BigDecimal taxAmt;

    @Column(nullable = false, columnDefinition = "numeric default 0")
    private BigDecimal taxPorcentage;

    @Column(nullable = false, columnDefinition = "numeric default 0")
    private BigDecimal totalAmt;

    @Column(nullable = false, length = 1)
    private String status;

}
