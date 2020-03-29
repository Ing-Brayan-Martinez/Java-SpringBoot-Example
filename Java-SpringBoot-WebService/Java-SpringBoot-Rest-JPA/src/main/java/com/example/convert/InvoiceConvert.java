package com.example.convert;

import com.example.domain.Invoice;
import com.example.service.dto.InvoiceDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class InvoiceConvert implements Convert<InvoiceDTO, Invoice> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Invoice fromDTO(InvoiceDTO dto) {
        return this.modelMapper.map(dto, Invoice.class);
    }

    @Override
    public Invoice fromDTO(Invoice entity, InvoiceDTO dto) {
        this.modelMapper.map(dto, entity);
        return entity;
    }

    @Override
    public InvoiceDTO toDTO(Invoice entity) {
        return this.modelMapper.map(entity, InvoiceDTO.class);
    }
}
