package com.example.crud.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;


public record ProdutoDTO (
        String id,
        @NotBlank
        String name,
        @NotNull
        Integer price_in_cents){

}
