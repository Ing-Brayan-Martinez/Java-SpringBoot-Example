package com.example.repository;

import com.example.domain.Children;

import java.util.List;
import java.util.Optional;


public interface ChildrenReposytory {

    Children insert(Children data);

    Children update(Children data);

    Optional<Children> findById(long key);

    List<Children> findAll();
}
