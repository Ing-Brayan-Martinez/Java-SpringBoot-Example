package com.example.convert;

import com.example.domain.Home;
import com.example.service.dto.HomeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class HomeConvert implements Convert<HomeDTO, Home> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Home fromDTO(HomeDTO dto) {
        return this.modelMapper.map(dto, Home.class);
    }

    @Override
    public Home fromDTO(Home entity, HomeDTO dto) {
        this.modelMapper.map(dto, entity);
        return entity;
    }

    @Override
    public HomeDTO toDTO(Home entity) {
        return this.modelMapper.map(entity, HomeDTO.class);
    }
}
