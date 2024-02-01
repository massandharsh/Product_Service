package dev.harsh.product_service.models;

import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Price extends BaseModel implements Serializable {
    private String currency;
    @Builder.Default
    private double price = 0;
}
