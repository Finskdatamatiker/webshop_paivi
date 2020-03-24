package com.example.webshop_paivi.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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

    /**
     * Category kan have mange produkter, et produkt kan tilhøre mange kategorier.
     * Når man opretter et produkt, kan man tilføje en kategori til den (har ikke gjort det obligatorisk)
     * Men jeg har valgt, at category opretter man uden at tilknytte kategorier.
     * Så det er gennem produkter, at kategorier vælges = produktet mapped by kategorier
     * Man kunne gøre det omvendt eller begge veje, men jeg har valgt produktet som indgangen.
     * Her bliver andre produkter ikke slettet fra det samme kategori, så remove er ikke med
     */

    @ManyToMany(fetch = FetchType.LAZY, cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    @JoinTable(name="products_categorys", joinColumns = { @JoinColumn(name = "product_id")}, inverseJoinColumns = { @JoinColumn(name="category_id")})
    protected List<Category> categorys = new ArrayList<>();

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

    public List<Category> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<Category> categorys) {
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
