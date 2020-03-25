package com.example.webshop_paivi.model;

import org.hibernate.annotations.Cascade;

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

    /**
     * Category er parent i denne relation, fordi mappedBy categorys i child Product.
     * (kunne også være omvendt).
     * Så Category kan ikke slettes, hvis der er produkter (children) tilknyttede til den.
     * Så hvis man fjerner cascade REMOVE, dvs. man prøver at give lov til at man kan
     * slette kategori uden at slette produkter, crasher koden.
     * Så det lader til, at JPA styrer relationen uafhængigt af cascad = Parent må ikke
     * slettes, hvis der er children.
     * Så cascade giver ingenting i denne ende af relationen.
     * Har løst det med at tjekke i contoller, om der er produkter. Er der det, må
     * kategori ikke slettes.
     */

    @ManyToMany(mappedBy = "categorys",fetch = FetchType.LAZY)
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
