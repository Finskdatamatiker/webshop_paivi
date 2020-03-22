package com.example.webshop_paivi.model;

import javax.persistence.*;

@Entity
public class Product {

    /**
     * name og description ændret til pname og pdescription, fordi name og
     * description er reserverede ord i mySQL.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String pname;
    private double price;
    private String pdescription;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="company_id", nullable = false)
    protected Company company;

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
