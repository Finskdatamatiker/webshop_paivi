package com.example.webshop_paivi.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Indtast venligst navn")
    private String caname;

    @ManyToMany(mappedBy = "categorys",fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    protected List<Product> products = new ArrayList<>();

    public Category(){}

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getCaname() { return caname; }
    public void setCaname(String caname) { this.caname = caname; }
    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products;}

    @Override
    public String toString() {
        return caname;
    }
}
