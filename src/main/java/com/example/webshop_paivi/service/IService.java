package com.example.webshop_paivi.service;

import java.util.List;
import java.util.Optional;

public interface IService<T> {

    List<T> findAll();
    void save(T product);
    Optional<T> findById(long id);
    void deleteById(long id);
}
