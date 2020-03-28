package com.example.webshop_paivi.service.company;

import com.example.webshop_paivi.model.Company;
import com.example.webshop_paivi.repository.CompanyRepository;
import com.example.webshop_paivi.service.IService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService implements IService<Company> {

    private final CompanyRepository companyRepository;
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAll() {
        List<Company> companylist = new ArrayList<>();
        for(Company c : companyRepository.findAll()){
            companylist.add(c);
        }
        return companylist;
    }

    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Optional<Company> findById(long id) {
        try{
            return companyRepository.findById(id);
        }catch (IllegalArgumentException ia) {
            System.out.println(ia);
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(long id) {
        companyRepository.deleteById(id);
    }
}
