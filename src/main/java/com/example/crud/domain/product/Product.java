package com.example.crud.domain.product;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Table(name="product")
@Entity(name = "product")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private Integer price_in_cents;
    private Boolean active;

    public Product(ProdutoDTO dados) {
        this.name = dados.name();
        this.price_in_cents = dados.price_in_cents();
        this.active = true;
    }

    public Product() {

    }
}
