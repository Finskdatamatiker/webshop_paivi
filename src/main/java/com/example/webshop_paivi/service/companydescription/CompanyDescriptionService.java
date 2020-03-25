package com.example.webshop_paivi.service.companydescription;

import com.example.webshop_paivi.model.CompanyDescription;
import com.example.webshop_paivi.repository.CompanyDescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyDescriptionService implements ICompanyDescriptionService {

    private final CompanyDescriptionRepository companyDescriptionRepository;

    public CompanyDescriptionService(CompanyDescriptionRepository companyDescriptionRepository){
        this.companyDescriptionRepository = companyDescriptionRepository;
    }

    @Override
    public List<CompanyDescription> getComBeskrivelser() {
        List<CompanyDescription> liste = new ArrayList<>();
        for(CompanyDescription c : companyDescriptionRepository.findAll()){
            liste.add(c);
        }
        return liste;
    }

    @Override
    public void gem(CompanyDescription compDes) {
        companyDescriptionRepository.save(compDes);

    }

    @Override
    public Optional<CompanyDescription> findMedId(long id) {
        try {
            return companyDescriptionRepository.findById(id);
        }catch (IllegalArgumentException ia){
            return Optional.empty();
        }
    }

    @Override
    public void slet(long id) {
        companyDescriptionRepository.deleteById(id);

    }
}
