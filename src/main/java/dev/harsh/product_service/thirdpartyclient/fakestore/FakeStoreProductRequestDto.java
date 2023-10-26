package dev.harsh.product_service.thirdpartyclient.fakestore;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FakeStoreProductRequestDto {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
