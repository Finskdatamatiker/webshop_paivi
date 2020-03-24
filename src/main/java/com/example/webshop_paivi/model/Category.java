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

    /**
     * Category kan have mange produkter, et produkt kan tilhøre mange kategorier.
     * Når man opretter et produkt, kan man tilføje en kategori til den (har ikke gjort det obligatorisk)
     * Men jeg har valgt, at category opretter man uden at tilknytte kategorier.
     * Så det er gennem produkter, at kategorier vælges = produktet mapped by kategorier
     * Man kunne gøre det omvendt eller begge veje, men jeg har valgt produktet som indgangen.
     * Jeg skal ikke slette kategori, hvis jeg slette produkt og omvendt, så cascade.remove er ikke med.
     */
   @ManyToMany(mappedBy = "categorys", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
