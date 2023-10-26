package dev.harsh.product_service.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.harsh.product_service.models.Category;
import dev.harsh.product_service.models.Price;
import dev.harsh.product_service.models.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@Setter
@ToString
public class GenericProductDto {
    private String id;
    private String title;
    private String description;
    private String image;
    private Category category;
    private Price price;
    public Product genericProductToProduct(){
        return Product.builder()
                .category(this.getCategory())
                .price(this.getPrice())
                .image(this.getImage())
                .description(this.getDescription())
                .title(this.getTitle())
                .build();
    }
}
