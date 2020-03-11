package com.example.service.impl;

import com.example.convert.ChildrenConvert;
import com.example.domain.Children;
import com.example.repository.ChildrenRepository;
import com.example.service.ChildrenService;
import com.example.service.UserAuditableService;
import com.example.service.dto.ChildrenDTO;
import com.example.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChildrenServiceImpl implements ChildrenService {

    @Autowired
    private ChildrenRepository childrenRepository;

    @Autowired
    private ChildrenConvert childrenConvert;

    @Autowired
    private UserAuditableService userAuditableService;

    @Override
    public Optional<ChildrenDTO> save(ChildrenDTO dto) {
        return Optional.ofNullable(this.childrenRepository.insert(this.childrenConvert.fromDTO(dto)))
            .map(entity -> this.childrenConvert.toDTO(entity));
    }

    @Override
    public Optional<ChildrenDTO> update(ChildrenDTO dto) {
        final Optional<Children> user = this.childrenRepository.findById(dto.getChildrenId());
        return user
            .map(entity -> this.childrenRepository.update(this.childrenConvert.fromDTO(entity, dto)))
            .map(entity -> this.childrenConvert.toDTO(entity));
    }

    @Override
    public Optional<ChildrenDTO> delete(Long id) {
        final Optional<Children> optional = this.childrenRepository.findById(id);

        final UserDTO audit = this.userAuditableService.getCurrentUser()
            .orElseThrow(() -> new UsernameNotFoundException("No existe usuario solicitado"));

        return optional.map(entity -> {
                entity.setIsActive(!entity.getIsActive());
                entity.setUpdated(new Timestamp(System.currentTimeMillis()));
                entity.setUpdatedBy(audit.getUserId());
                return entity;
            })
            .map(entity -> this.childrenRepository.update(entity))
            .map(result -> this.childrenConvert.toDTO(result));
    }

    @Override
    public Optional<ChildrenDTO> findById(Long id) {
        return this.childrenRepository.findById(id)
            .map(entity -> this.childrenConvert.toDTO(entity));
    }

    @Override
    public List<ChildrenDTO> findAll() {
        return this.childrenRepository.findAll().stream()
            .map(entity -> this.childrenConvert.toDTO(entity))
            .collect(Collectors.toList());
    }

}
