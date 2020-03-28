package com.example.jdbc.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {

    Optional<T> save(T dto);

    Optional<T> update(T dto);

    Optional<T> delete(Long id);

    Optional<T> findById(Long id);

    List<T> findAll();

}
