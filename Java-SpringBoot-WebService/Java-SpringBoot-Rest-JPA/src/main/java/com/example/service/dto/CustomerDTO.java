package com.example.service.dto;

import lombok.Data;

@Data
public class CustomerDTO {

    private Long customerId;

    private String code;

    private String name;

    private String address;

    private String phone;

    private String status;

}
