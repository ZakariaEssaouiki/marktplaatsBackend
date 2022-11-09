package com.marktplaats.model;

import javax.persistence.*;

@Entity
@Table(name = "GebruikerProducten")
public class GebruikerProducten {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @ManyToOne
    @JoinColumn(name = "gebruiker_id")
    public Gebruiker gebruiker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    public Product product;

    public Product getProduct() {return this.product;}

    public void setProduct(Product product) {this.product = product;}

    public Gebruiker getGebruiker() {return this.gebruiker;}

    public void setGebruiker(Gebruiker gebruiker) {this.gebruiker = gebruiker;}
}
