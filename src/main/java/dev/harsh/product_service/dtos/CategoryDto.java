package dev.harsh.product_service.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.harsh.product_service.models.Product;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CategoryDto {
    private String name;
    private List<ProductDto> productsDto;
}
