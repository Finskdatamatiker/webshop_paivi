package com.example.webshop_paivi.controller;

import com.example.webshop_paivi.model.Category;
import com.example.webshop_paivi.model.Company;
import com.example.webshop_paivi.model.Product;
import com.example.webshop_paivi.service.category.ICategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoryController {

    public final ICategoryService iCategoryService;
    public CategoryController(ICategoryService iCategoryService){
        this.iCategoryService = iCategoryService;
    }

    @GetMapping("/categoryForside")
    public String visCategoryside(Category category, Model model){
        List<Category> listeCategorier = iCategoryService.getCategorier();
        List<Product> listeProdukterICategory = category.getProducts();
        model.addAttribute("categorier", listeCategorier);
        model.addAttribute("produkterI", listeProdukterICategory);
        return "/category/categoryForside";
    }
}
