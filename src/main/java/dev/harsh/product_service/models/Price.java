package dev.harsh.product_service.models;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Price extends BaseModel{
    private String currency;
    @Builder.Default
    private double price = 0;
}
