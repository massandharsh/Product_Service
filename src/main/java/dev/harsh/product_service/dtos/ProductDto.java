package dev.harsh.product_service.dtos;

import dev.harsh.product_service.models.Price;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductDto {
    private String title;

    private String description;

    private String image;

    private Price price;
}
