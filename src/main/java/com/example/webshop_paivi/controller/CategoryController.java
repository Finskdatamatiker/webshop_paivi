package com.example.webshop_paivi.controller;

import com.example.webshop_paivi.model.Category;
import com.example.webshop_paivi.model.Product;
import com.example.webshop_paivi.service.IService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CategoryController {

    public final IService<Category> iCategoryService;
    public final IService<Product> iProductService;
    public CategoryController(IService<Category> iCategoryService, IService<Product> iProductService){
        this.iCategoryService = iCategoryService;
        this.iProductService = iProductService;
    }

    @GetMapping("/categoryForside")
    public String visCategoryside(Model model){
        List<Category> listeCategorier = iCategoryService.findAll();
        model.addAttribute("categorier", listeCategorier);
        return "/category/categoryForside";
    }


    @GetMapping("/createCategory")
    public String visCreate(Category category, Model model){
        List<Product> produkter = iProductService.findAll();
        model.addAttribute("produkter", produkter);
        model.addAttribute("category", category);
        return "/category/createCategory";
    }

    @PostMapping("/createCategory")
    public String lavCompany(@Valid Category category, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            return "/category/createCategory";
        }

        iCategoryService.save(category);
        return "redirect:/categoryForside";
    }

    @GetMapping("/updateCategory/{id}")
    public String visUpdate(@PathVariable("id") long id, Model model){
        model.addAttribute("category", iCategoryService.findById(id));
        return "/category/updateCategory";
    }

    @PostMapping("/updateCategory")
    public String update(@Valid Category category, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            return "/category/updateCategory";
        }
        iCategoryService.save(category);
        return "redirect:/categoryForside";
    }

    @GetMapping("/deleteCategory/{id}")
    public String delete(@PathVariable("id") long id){
            iCategoryService.deleteById(id);
        return "redirect:/categoryForside";
    }

}
