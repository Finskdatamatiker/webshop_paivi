package com.example.webshop_paivi.service;

import com.example.webshop_paivi.model.Product;
import com.example.webshop_paivi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IService<Product> {
    
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        List<Product> productlist = new ArrayList<>();
        for(Product p : productRepository.findAll()){
            productlist.add(p);
        }
        return productlist;
    }

    @Override
    public void save(Product product)
    {
        productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(long id) {
        try{
            return productRepository.findById(id);
        }catch (IllegalArgumentException ia){
            System.out.println(ia);
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(long id){ productRepository.deleteById(id);}
}
