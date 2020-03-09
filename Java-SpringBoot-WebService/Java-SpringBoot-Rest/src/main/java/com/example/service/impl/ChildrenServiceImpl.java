package com.example.service.impl;

import com.example.convert.ChildrenConverted;
import com.example.domain.Children;
import com.example.repository.impl.ChildrenRepositoryImpl;
import com.example.service.ChildrenService;
import com.example.service.dto.ChildrenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChildrenServiceImpl implements ChildrenService {

    @Autowired
    private ChildrenRepositoryImpl repository;

    @Autowired
    private ChildrenConverted factory;

    @Override
    public Optional<ChildrenDTO> save(ChildrenDTO dto) {
        Children data = this.factory.fromDto(null);
        this.repository.insert(data);
        return Optional.empty();
    }

    @Override
    public Optional<ChildrenDTO> update(ChildrenDTO dto) {
        Children data = this.factory.fromDto(null);
        this.repository.update(data);
        return Optional.empty();
    }

    @Override
    public Optional<ChildrenDTO> delete(Long id) {
        this.repository.delete(id);
        return Optional.empty();
    }

    @Override
    public Optional<ChildrenDTO> findById(Long id) {
        Optional<Children> children = this.repository.findById(id);
        return Optional.empty();
    }

    @Override
    public List<ChildrenDTO> findAll() {
        List<Children> children = this.repository.findAll();
        return null;
    }

}
