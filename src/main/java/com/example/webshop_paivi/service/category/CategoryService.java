package com.example.webshop_paivi.service.category;

import com.example.webshop_paivi.model.Category;
import com.example.webshop_paivi.repository.CategoryRepository;
import com.example.webshop_paivi.service.IService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements IService<Category> {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        List<Category> liste = new ArrayList<>();
        for(Category c : categoryRepository.findAll()){
            liste.add(c);
        }
        return liste;
    }

    @Override
    public void save(Category product) {
        categoryRepository.save(product);
    }

    @Override
    public Optional<Category> findById(long id) {
        try{
            return categoryRepository.findById(id);
        }catch (IllegalArgumentException ia){
            System.out.println(ia);
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(long id) {
        categoryRepository.deleteById(id);
    }
}
