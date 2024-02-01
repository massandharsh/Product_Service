package dev.harsh.product_service.thirdpartyclient.fakestore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FakeStoreRatingDto {
    private Integer rate;
    private Integer count;
}
