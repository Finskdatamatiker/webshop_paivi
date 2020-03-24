package com.example.webshop_paivi.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Product {

    /**
     * name og description ændret til pname og pdescription, fordi name og
     * description er reserverede ord i mySQL.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Indtast venligst produktnavnet")
    private String pname;
    @NotNull
    private double price;
    @NotBlank(message = "Indtast venligst beskrivelse")
    private String pdescription;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="company_id", nullable = false)
    protected Company company;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Category> categorys;


    public Product() {}

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getPname() {
        return pname;
    }
    public void setPname(String name) {
        this.pname = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getPdescription() {
        return pdescription;
    }
    public void setPdescription(String description) {
        this.pdescription = description;
    }

    public List<Category> getCategory() {
        return categorys;
    }

    public void setCategory(List<Category> category) {
        this.categorys = categorys;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return pname;
    }
}
