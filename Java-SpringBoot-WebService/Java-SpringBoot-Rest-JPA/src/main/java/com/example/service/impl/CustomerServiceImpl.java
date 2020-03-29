package com.example.service.impl;

import com.example.convert.CustomerConvert;
import com.example.domain.Customer;
import com.example.domain.User;
import com.example.repository.CustomerRepository;
import com.example.service.CustomerService;
import com.example.service.UserAuditableService;
import com.example.service.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private UserAuditableService userAuditableService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerConvert customerConvert;


    @Override
    public Optional<CustomerDTO> save(CustomerDTO dto) {
        return Optional.of(this.customerRepository.save(Objects.requireNonNull(this.customerConvert.fromDTO(dto))))
            .map(entity -> this.customerConvert.toDTO(entity));
    }

    @Override
    public Optional<CustomerDTO> update(CustomerDTO dto) {
        final Optional<Customer> optional = this.customerRepository.findById(dto.getCustomerId());
        return optional
            .map(entity -> this.customerRepository.save(Objects.requireNonNull(this.customerConvert.fromDTO(entity, dto))))
            .map(entity -> this.customerConvert.toDTO(entity));
    }

    @Override
    public Optional<CustomerDTO> delete(Long id) {
        final Optional<Customer> optional = this.customerRepository.findById(id);

        final User audit = this.userAuditableService.getCurrentUser()
            .orElseThrow(() -> new UsernameNotFoundException("No existe usuario solicitado"));

        return optional.map(entity -> {
                entity.setIsActive(!entity.getIsActive());
                entity.setUpdated(LocalDateTime.now());
                entity.setUpdatedBy(audit);
                return entity;
            })
            .map(entity -> this.customerRepository.save(entity))
            .map(result -> this.customerConvert.toDTO(result));
    }

    @Override
    public Optional<CustomerDTO> findById(Long id) {
        return this.customerRepository.findById(id)
            .map(entity -> this.customerConvert.toDTO(entity));
    }

    @Override
    public List<CustomerDTO> findAll() {
        return this.customerRepository.findAll().stream()
            .map(entity -> this.customerConvert.toDTO(entity))
            .collect(Collectors.toList());
    }
}
