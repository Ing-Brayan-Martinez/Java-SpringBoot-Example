package com.example.service.impl;

import com.example.convert.HomeConvert;
import com.example.domain.Home;
import com.example.domain.User;
import com.example.repository.HomeRepository;
import com.example.service.HomeService;
import com.example.service.UserAuditableService;
import com.example.service.dto.HomeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private UserAuditableService userAuditableService;

    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private HomeConvert homeConvert;


    @Override
    public Optional<HomeDTO> save(HomeDTO dto) {
        return Optional.of(this.homeRepository.save(Objects.requireNonNull(this.homeConvert.fromDTO(dto))))
            .map(entity -> this.homeConvert.toDTO(entity));
    }

    @Override
    public Optional<HomeDTO> update(HomeDTO dto) {
        final Optional<Home> optional = this.homeRepository.findById(dto.getHomeId());
        return optional
            .map(entity -> this.homeRepository.save(Objects.requireNonNull(this.homeConvert.fromDTO(entity, dto))))
            .map(entity -> this.homeConvert.toDTO(entity));
    }

    @Override
    public Optional<HomeDTO> delete(Long id) {
        final Optional<Home> optional = this.homeRepository.findById(id);

        final User audit = this.userAuditableService.getCurrentUser()
            .orElseThrow(() -> new UsernameNotFoundException("No existe usuario solicitado"));

        return optional.map(entity -> {
                entity.setIsActive(!entity.getIsActive());
                entity.setUpdated(LocalDateTime.now());
                entity.setUpdatedBy(audit);
                return entity;
            })
            .map(entity -> this.homeRepository.save(entity))
            .map(result -> this.homeConvert.toDTO(result));
    }

    @Override
    public Optional<HomeDTO> findById(Long id) {
        return this.homeRepository.findById(id)
            .map(entity -> this.homeConvert.toDTO(entity));
    }

    @Override
    public List<HomeDTO> findAll() {
        return this.homeRepository.findAll().stream()
            .map(entity -> this.homeConvert.toDTO(entity))
            .collect(Collectors.toList());
    }
}
