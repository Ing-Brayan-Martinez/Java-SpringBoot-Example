package com.example.repository;

import com.example.domain.Children;

import java.util.List;
import java.util.Optional;

public class EmptyChildrenRepositoryImpl implements ChildrenReposytory {

    @Override
    public Children insert(Children data) {
        return null;
    }

    @Override
    public Children update(Children data) {
        return null;
    }

    @Override
    public Children delete(long key) {
        return null;
    }

    @Override
    public Optional<Children> findById(long key) {
        return Optional.empty();
    }

    @Override
    public List<Children> findAll() {
        return null;
    }
}
