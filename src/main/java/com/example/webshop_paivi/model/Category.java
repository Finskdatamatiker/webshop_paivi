package com.example.webshop_paivi.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Indtast venligst navn")
    private String caname;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="category_product",
            joinColumns = {@JoinColumn(name = "category_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name="product_id", nullable = false)})
    protected List<Product> products;


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
