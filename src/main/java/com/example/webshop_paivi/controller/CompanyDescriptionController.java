package com.example.webshop_paivi.controller;

import com.example.webshop_paivi.model.CompanyDescription;
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
public class CompanyDescriptionController {

    private final IService<CompanyDescription> iCompanyDescriptionService;

    public CompanyDescriptionController(IService<CompanyDescription> iCompanyDescriptionService){
        this.iCompanyDescriptionService = iCompanyDescriptionService;
    }
    @GetMapping("/companyDescForside")
    public String visCompanyDescForside(Model model){
        List<CompanyDescription> listeBeskrivelser = iCompanyDescriptionService.findAll();
        model.addAttribute("beskrivelser", listeBeskrivelser);
        return "/companyDesc/companyDescForside";
    }

    @GetMapping("/createCompanyDesc/{p.id}")
    public String visCreate(@PathVariable("p.id") int id, CompanyDescription companyDesc, Model model){
        companyDesc.setId(id);
        model.addAttribute("companyDesc", companyDesc);
        return "/companyDesc/createCompanyDesc";
    }

    @PostMapping("/createCompanyDesc")
    public String lavCompanyDesc(@Valid CompanyDescription companyDesc, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            return "/companyDesc/createCompanyDesc";
        }
        iCompanyDescriptionService.save(companyDesc);
        return "redirect:/companyDescForside";
    }


    @GetMapping("/updateCompanyDesc/{id}")
    public String visUpdate(@PathVariable("id") long id, Model model){
        model.addAttribute("companyDesc", iCompanyDescriptionService.findById(id));
        return "/companyDesc/updateCompanyDesc";
    }

    @PostMapping("/updateCompanyDesc")
    public String update(@Valid CompanyDescription companyDesc, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            return "/companyDesc/updateCompanyDesc";
        }
        iCompanyDescriptionService.save(companyDesc);
        return "redirect:/companyDescForside";
    }


    @GetMapping("/deleteCompanyDesc/{id}")
    public String delete(@PathVariable("id") long id){
        iCompanyDescriptionService.deleteById(id);
        return "redirect:/companyDescForside";
    }

}
