package com.example.webshop_paivi.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class CompanyDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Indtast venligst beskrivelse")
    @Length(max = 2000)
    private String co_description;


    /**
     * Jeg har valgt at have CompanyDescription som parent. Man skal ikke ,
     * , fordi hvis man sletter
     * CompanyDescription,
     */
    @OneToOne(mappedBy = "company_description", fetch = FetchType.LAZY)
    protected Product product;

    public CompanyDescription (){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCo_description() {
        return co_description;
    }

    public void setCo_description(String coDescription) {
        this.co_description = coDescription;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return co_description;
    }
}
