package com.example.convert;

public interface Convert<T, S> {

    S fromDTO(T dto);

    S fromDTO(S entity, T dto);

    T toDTO(S entity);
}
