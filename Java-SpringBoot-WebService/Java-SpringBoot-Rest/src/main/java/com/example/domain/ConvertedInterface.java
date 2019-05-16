package com.example.domain;


import com.example.domain.children.Children;
import com.example.infraestructure.util.Intent;

public interface ConvertedInterface<T> {

    public T onConvert(Intent command);

}
