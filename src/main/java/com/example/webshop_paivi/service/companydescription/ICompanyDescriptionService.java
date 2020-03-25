package com.example.webshop_paivi.service.companydescription;

import com.example.webshop_paivi.model.CompanyDescription;

import java.util.List;
import java.util.Optional;

public interface ICompanyDescriptionService {

    List<CompanyDescription> getComBeskrivelser();
    void gem(CompanyDescription compDes);
    Optional<CompanyDescription> findMedId(long id);
    void slet(long id);
}
