package com.example.webshop_paivi.controller;

import com.example.webshop_paivi.model.Category;
import com.example.webshop_paivi.model.Company;
import com.example.webshop_paivi.model.CompanyDescription;
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
public class ProductController {

    private final IService<Product> iProductService;
    private final IService<Company> iCompanyService;
    private final IService<Category> iCategoryService;

    public ProductController(IService<Product> iPro, IService<Company> iCom, IService<Category> iCat) {
        this.iProductService = iPro;
        this.iCompanyService = iCom;
        this.iCategoryService = iCat;
    }

    @GetMapping("/")
    public String visForside(Model model){
        List<Product> liste = iProductService.findAll();
        if(liste.size() != 0) {

            model.addAttribute("produkter", liste);
        }
        return "/index";
    }


    @GetMapping("/create")
    public String visCreate(Product product, CompanyDescription comdes, Model model)
    {
        List<Company> liste = iCompanyService.findAll();
        if(liste.size() == 0){
            return "redirect:/";
        }
        else{
            model.addAttribute("virksomheder", liste);
            product.setCompany(liste.get(0));
            List<Category> listeCategorier = iCategoryService.findAll();
            model.addAttribute("categorier", listeCategorier);
            model.addAttribute("product", product);
            model.addAttribute("company_description", comdes);
            return "/create";
        }
    }

    @PostMapping("/create")
    public String lavProdukt(@Valid Product product, BindingResult br, @Valid CompanyDescription comdes, BindingResult br2, Model model)
    {
        if(br.hasErrors() || br2.hasErrors()){
            model.addAttribute("product", product);
            model.addAttribute("company_description", comdes);
            model.addAttribute("bindingResult", br);
            model.addAttribute("bindingResult2", br2);
            List<Company> liste = iCompanyService.findAll();
            model.addAttribute("virksomheder", liste);
            List<Category> listeCategorier = iCategoryService.findAll();
            model.addAttribute("categorier", listeCategorier);
            product.setCompany(liste.get(0));
            return "/create";
        }

        product.setCompany_description(comdes);
        iProductService.save(product);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String visUpdate(@PathVariable("id") long id, Model model){
        List<Company> liste = iCompanyService.findAll();
        List<Category> kategorier = iCategoryService.findAll();
        model.addAttribute("virksomheder", liste);
        model.addAttribute("kategorier", kategorier);
        model.addAttribute("product", iProductService.findById(id));
        return "/update";
    }

    @PostMapping("/update")
    public String update(@Valid Product product, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            //virksomheder og kategorier skal fragtes igen til forsiden
            List<Company> liste = iCompanyService.findAll();
            List<Category> kategorier = iCategoryService.findAll();
            model.addAttribute("virksomheder", liste);
            model.addAttribute("kategorier", kategorier);
            product.setCompany(liste.get(0));
            return "/update";
        }
        iProductService.save(product);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        iProductService.deleteById(id);
        return "redirect:/";
    }
}
