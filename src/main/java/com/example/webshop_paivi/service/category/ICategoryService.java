package com.example.webshop_paivi.service.category;

import com.example.webshop_paivi.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
        List<Category> getCategorier();
        void gem(Category product);
        Optional<Category> findMedId(long id);
        void slet(long id);
}

