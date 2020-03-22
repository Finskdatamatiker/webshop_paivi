package com.example.webshop_paivi.controller;

import com.example.webshop_paivi.model.Company;
import com.example.webshop_paivi.model.Product;
import com.example.webshop_paivi.service.IProductService;
import com.example.webshop_paivi.service.company.ICompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class ProductController {

    private final IProductService iProductService;
    private final ICompanyService iCompanyService;

    public ProductController(IProductService iProductService, ICompanyService iCompanyService) {
        this.iProductService = iProductService;
        this.iCompanyService = iCompanyService;
    }

    @GetMapping("/")
    public String visForside(Model model){
        List<Product> liste = iProductService.getProdukter();
        model.addAttribute("produkter", liste);
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
        model.addAttribute("virksomheder", liste);
        model.addAttribute("product", product);
        return "/create";
    }

    /**
     * Prdduktoplysninger bliver flettet i template med thymeleaf, som også giver adgang
     * til fields her i konstruktør
     * @param product
     * @return
     */
    @PostMapping("/create")
    public String lavProdukt(Product product)
    {
        iProductService.gem(product);
        return "redirect:/";
    }


    @GetMapping("/update/{id}")
    public String visUpdate(@PathVariable("id") long id, Model model){
        List<Company> liste = iCompanyService.getVirksomheder();
        model.addAttribute("virksomheder", liste);
        model.addAttribute("product", iProductService.findMedId(id));
        return "/update";
    }

    @PostMapping("/update")
    public String update(Product product){
        iProductService.gem(product);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        iProductService.slet(id);
        return "redirect:/";
    }


}
