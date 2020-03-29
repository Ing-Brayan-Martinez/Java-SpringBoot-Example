package com.example.service.impl;

import com.example.convert.InvoiceConvert;
import com.example.domain.Invoice;
import com.example.domain.User;
import com.example.repository.InvoiceRepository;
import com.example.service.InvoiceService;
import com.example.service.UserAuditableService;
import com.example.service.dto.InvoiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private UserAuditableService userAuditableService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceConvert homeConvert;


    @Override
    public Optional<InvoiceDTO> save(InvoiceDTO dto) {
        return Optional.of(this.invoiceRepository.save(Objects.requireNonNull(this.homeConvert.fromDTO(dto))))
            .map(entity -> this.homeConvert.toDTO(entity));
    }

    @Override
    public Optional<InvoiceDTO> update(InvoiceDTO dto) {
        final Optional<Invoice> optional = this.invoiceRepository.findById(dto.getInvoiceId());
        return optional
            .map(entity -> this.invoiceRepository.save(Objects.requireNonNull(this.homeConvert.fromDTO(entity, dto))))
            .map(entity -> this.homeConvert.toDTO(entity));
    }

    @Override
    public Optional<InvoiceDTO> delete(Long id) {
        final Optional<Invoice> optional = this.invoiceRepository.findById(id);

        final User audit = this.userAuditableService.getCurrentUser()
            .orElseThrow(() -> new UsernameNotFoundException("No existe usuario solicitado"));

        return optional.map(entity -> {
                entity.setIsActive(!entity.getIsActive());
                entity.setUpdated(LocalDateTime.now());
                entity.setUpdatedBy(audit);
                return entity;
            })
            .map(entity -> this.invoiceRepository.save(entity))
            .map(result -> this.homeConvert.toDTO(result));
    }

    @Override
    public Optional<InvoiceDTO> findById(Long id) {
        return this.invoiceRepository.findById(id)
            .map(entity -> this.homeConvert.toDTO(entity));
    }

    @Override
    public List<InvoiceDTO> findAll() {
        return this.invoiceRepository.findAll().stream()
            .map(entity -> this.homeConvert.toDTO(entity))
            .collect(Collectors.toList());
    }
}
