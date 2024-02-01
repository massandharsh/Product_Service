package dev.harsh.product_service.dtos;

import lombok.*;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SortHelperDto {
    private Sort.Direction direction;
    private String sortBy;
}
