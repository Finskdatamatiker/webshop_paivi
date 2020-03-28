package com.example.webshop_paivi.service.companydescription;

import com.example.webshop_paivi.model.CompanyDescription;
import com.example.webshop_paivi.repository.CompanyDescriptionRepository;
import com.example.webshop_paivi.service.IService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyDescriptionService implements IService<CompanyDescription> {

    private final CompanyDescriptionRepository companyDescriptionRepository;

    public CompanyDescriptionService(CompanyDescriptionRepository companyDescriptionRepository){
        this.companyDescriptionRepository = companyDescriptionRepository;
    }

    @Override
    public List<CompanyDescription> findAll() {
        List<CompanyDescription> liste = new ArrayList<>();
        for(CompanyDescription c : companyDescriptionRepository.findAll()){
            liste.add(c);
        }
        return liste;
    }

    @Override
    public void save(CompanyDescription compDes) {
        companyDescriptionRepository.save(compDes);

    }

    @Override
    public Optional<CompanyDescription> findById(long id) {
        try {
            return companyDescriptionRepository.findById(id);
        }catch (IllegalArgumentException ia){
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(long id) {
        companyDescriptionRepository.deleteById(id);

    }
}
