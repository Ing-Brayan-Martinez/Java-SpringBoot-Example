package com.example.convert;

import com.example.domain.Customer;
import com.example.service.dto.CustomerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class CustomerConvert implements Convert<CustomerDTO, Customer> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Customer fromDTO(CustomerDTO dto) {
        return this.modelMapper.map(dto, Customer.class);
    }

    @Override
    public Customer fromDTO(Customer entity, CustomerDTO dto) {
        this.modelMapper.map(dto, entity);
        return entity;
    }

    @Override
    public CustomerDTO toDTO(Customer entity) {
        return this.modelMapper.map(entity, CustomerDTO.class);
    }
}
