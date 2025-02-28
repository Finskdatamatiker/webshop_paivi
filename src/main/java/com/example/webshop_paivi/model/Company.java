package com.example.webshop_paivi.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Indtast venligst navnet")
    @Size(min = 2, max = 40)
    private String cname;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    protected List<Product> product;

    public Company(){}

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCname() {
        return cname;
    }
    public void setCname(String cname) {
        this.cname = cname;
    }
    public List<Product> getProduct() {
        return product;
    }
    public void setProduct(List<Product> product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return cname;
    }
}
