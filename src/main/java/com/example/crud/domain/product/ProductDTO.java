package com.example.crud.domain.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private String name;
    private Integer price_in_cents;

    @Override
    public String toString() {
        return "ProductDTO{" +
                "name='" + name + '\'' +
                ", price_in_cents=" + price_in_cents +
                '}';
    }


}
