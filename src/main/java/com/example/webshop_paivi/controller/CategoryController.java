package com.example.webshop_paivi.controller;

import com.example.webshop_paivi.model.Category;
import com.example.webshop_paivi.model.Company;
import com.example.webshop_paivi.model.Product;
import com.example.webshop_paivi.service.IProductService;
import com.example.webshop_paivi.service.category.ICategoryService;
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

    public final ICategoryService iCategoryService;
    public final IProductService iProductService;
    public CategoryController(ICategoryService iCategoryService, IProductService iProductService){
        this.iProductService = iProductService;
        this.iCategoryService = iCategoryService;
    }

    @GetMapping("/categoryForside")
    public String visCategoryside(Model model){
        List<Category> listeCategorier = iCategoryService.getCategorier();
        model.addAttribute("categorier", listeCategorier);
        return "/category/categoryForside";
    }


    @GetMapping("/createCategory")
    public String visCreate(Category category, Model model){
        List<Product> produkter = iProductService.getProdukter();
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

        iCategoryService.gem(category);
        return "redirect:/categoryForside";
    }

    @GetMapping("/updateCategory/{id}")
    public String visUpdate(@PathVariable("id") long id, Model model){
        model.addAttribute("category", iCategoryService.findMedId(id));
        return "/category/updateCategory";
    }

    @PostMapping("/updateCategory")
    public String update(@Valid Category category, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            return "/category/updateCategory";
        }
        iCategoryService.gem(category);
        return "redirect:/categoryForside";
    }

    @GetMapping("/deleteCategory/{id}")
    public String delete(@PathVariable("id") long id){
        iCategoryService.slet(id);
        return "redirect:/categoryForside";
    }

}
