package com.example.webshop_paivi.controller;

import com.example.webshop_paivi.model.Category;
import com.example.webshop_paivi.model.Company;
import com.example.webshop_paivi.model.Product;
import com.example.webshop_paivi.service.IProductService;
import com.example.webshop_paivi.service.category.ICategoryService;
import com.example.webshop_paivi.service.company.ICompanyService;
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

    private final IProductService iProductService;
    private final ICompanyService iCompanyService;
    private final ICategoryService iCategoryService;

    public ProductController(IProductService iPro, ICompanyService iCom, ICategoryService iCat) {
        this.iProductService = iPro;
        this.iCompanyService = iCom;
        this.iCategoryService = iCat;
    }

    @GetMapping("/")
    public String visForside(Model model){
        List<Product> liste = iProductService.getProdukter();
        if(liste.size() != 0) {
            model.addAttribute("produkter", liste);
        }
        return "/index";
    }

    /**
     * "tom" produkt fragtes til create-siden i model for at bliver udfyldt
     * @param product
     * @param model
     * @return
     */
    @GetMapping("/create")
    public String visCreate(Product product, Model model)
    {
        List<Company> liste = iCompanyService.getVirksomheder();
        if(liste.size() == 0){
            return "redirect:/";
        }
        else{
            model.addAttribute("virksomheder", liste);
            //første element som default, fordi null ikke var tilladt
            product.setCompany(liste.get(0));
            //kategory er ikke must
            List<Category> listeCategorier = iCategoryService.getCategorier();
            model.addAttribute("categorier", listeCategorier);
            model.addAttribute("product", product);
            return "/create";
        }
    }

    /**
     * Produktoplysninger bliver flettet i template med thymeleaf, som også giver adgang
     * til fields her i konstruktør
     * @param product
     * @return
     */
    @PostMapping("/create")
    public String lavProdukt(@Valid Product product, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            //virksomheder skal fragtes igen til forsiden
            List<Company> liste = iCompanyService.getVirksomheder();
            model.addAttribute("virksomheder", liste);
            product.setCompany(liste.get(0));
            return "/create";
        }


        iProductService.gem(product);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String visUpdate(@PathVariable("id") long id, Model model){
        List<Company> liste = iCompanyService.getVirksomheder();
        List<Category> kategorier = iCategoryService.getCategorier();
        model.addAttribute("virksomheder", liste);
        model.addAttribute("kategorier", kategorier);
        model.addAttribute("product", iProductService.findMedId(id));
        return "/update";
    }

    @PostMapping("/update")
    public String update(@Valid Product product, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            //virksomheder og kategorier skal fragtes igen til forsiden
            List<Company> liste = iCompanyService.getVirksomheder();
            List<Category> kategorier = iCategoryService.getCategorier();
            model.addAttribute("virksomheder", liste);
            model.addAttribute("kategorier", kategorier);
            product.setCompany(liste.get(0));
            return "/update";
        }
        iProductService.gem(product);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        iProductService.slet(id);
        return "redirect:/";
    }
}
