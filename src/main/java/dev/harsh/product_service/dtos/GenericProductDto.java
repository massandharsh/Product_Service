package dev.harsh.product_service.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.harsh.product_service.models.Category;
import dev.harsh.product_service.models.Price;
import dev.harsh.product_service.models.Product;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Accessors(chain = true)
@Builder
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GenericProductDto implements Serializable {
    private String id;
    private String title;
    private String description;
    private String image;
    private Category category;
    private Price price;
    private Long total;
    public Product genericProductToProduct(){
        return Product.builder()
                .category(this.getCategory())
                .price(this.getPrice())
                .image(this.getImage())
                .description(this.getDescription())
                .title(this.getTitle())
                .build();
    }

    public static GenericProductDto productToGenericProductDto(Product product){
        return GenericProductDto.builder()
                .title(product.getTitle())
                .price(product.getPrice())
                .id(product.getUuid().toString())
                .description(product.getDescription())
                .image(product.getImage())
                .category(product.getCategory())
                .build();
    }
}
