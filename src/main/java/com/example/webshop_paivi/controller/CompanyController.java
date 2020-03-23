package com.example.webshop_paivi.controller;

import com.example.webshop_paivi.model.Company;
import com.example.webshop_paivi.model.Product;
import com.example.webshop_paivi.service.company.ICompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CompanyController {


    private final ICompanyService iCompanyService;

    public CompanyController(ICompanyService iCompanyService) {
        this.iCompanyService = iCompanyService;
    }

    @GetMapping("/companyForside")
    public String visCompanyForside(Company company, Model model){
        List<Company> listeVirksomheder = iCompanyService.getVirksomheder();
        List<Product> listeProdukterTilCompany = company.getProduct();
        model.addAttribute("virksomheder", listeVirksomheder);
        model.addAttribute("produkterVirksomhed", listeProdukterTilCompany);
        return "/company/companyForside";
    }

    /**
     * "tom" produkt fragtes til create-siden i model for at bliver udfyldt
     * @param company
     * @param model
     * @return
     */
    @GetMapping("/createCompany")
    public String visCreate(Company company, Model model){
        model.addAttribute("company", company);
        return "/company/createCompany";
    }

    /**
     * Prdduktoplysninger bliver flettet i template med thymeleaf, som også giver adgang
     * til fields her i konstruktør
     * @param company
     * @return
     */
    @PostMapping("/createCompany")
    public String lavCompany(Company company)
    {
        iCompanyService.gem(company);
        return "redirect:/companyForside";
    }


    @GetMapping("/updateCompany/{id}")
    public String visUpdate(@PathVariable("id") long id, Model model){
        model.addAttribute("company", iCompanyService.findMedId(id));
        return "/company/updateCompany";
    }

    @PostMapping("/updateCompany")
    public String update(Company company){
        iCompanyService.gem(company);
        return "redirect:/companyForside";
    }

    @GetMapping("/deleteCompany/{id}")
    public String delete(@PathVariable("id") long id){
        iCompanyService.slet(id);
        return "redirect:/companyForside";
    }


}
