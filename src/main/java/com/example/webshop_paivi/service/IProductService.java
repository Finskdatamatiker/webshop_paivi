package com.example.webshop_paivi.service;

import com.example.webshop_paivi.model.Product;
import java.util.List;
import java.util.Optional;

/**
 * Her giver jeg mine egne navne til metoderne, fordi implementeringer med
 * CrudRepositorys metoder findes i den implementerende klasse. Det er metoderne
 * fra interface, at jeg kalder på i constoller
 */
public interface IProductService {

    List<Product> getProdukter();
    void gem(Product product);
    Optional<Product> findMedId(long id);
    void slet(long id);

}
