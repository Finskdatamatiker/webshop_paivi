package com.example.webshop_paivi.service.category;

import com.example.webshop_paivi.model.Category;
import com.example.webshop_paivi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategorier() {
        List<Category> liste = new ArrayList<>();
        for(Category c : categoryRepository.findAll()){
            liste.add(c);
        }
        return liste;
    }

    @Override
    public void gem(Category product) {
        categoryRepository.save(product);
    }

    @Override
    public Optional<Category> findMedId(long id) {
        try{
            return categoryRepository.findById(id);
        }catch (IllegalArgumentException ia){
            System.out.println(ia);
            return Optional.empty();
        }
    }

    @Override
    public void slet(long id) {
        categoryRepository.deleteById(id);
    }
}
