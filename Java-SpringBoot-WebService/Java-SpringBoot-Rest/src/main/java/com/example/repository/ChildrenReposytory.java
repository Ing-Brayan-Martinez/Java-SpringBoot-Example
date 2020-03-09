package com.example.repository;

import com.example.domain.Children;

import java.util.List;
import java.util.Optional;


public interface ChildrenReposytory {

    public Children insert(Children data);

    public Children update(Children data);

    public Optional<Children> findById(long key);

    public List<Children> findAll();
}
