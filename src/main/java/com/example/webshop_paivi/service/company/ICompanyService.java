package com.example.webshop_paivi.service.company;

import com.example.webshop_paivi.model.Company;

import java.util.List;
import java.util.Optional;

public interface ICompanyService {
    List<Company> getVirksomheder();
    void gem(Company product);
    Optional<Company> findMedId(long id);
    void slet(long id);
}
