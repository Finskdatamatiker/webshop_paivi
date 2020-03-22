package com.example.webshop_paivi.repository;

import com.example.webshop_paivi.model.Product;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {}
