package dev.harsh.product_service.thirdpartyclient.fakestore;

import dev.harsh.product_service.dtos.GenericProductDto;
import dev.harsh.product_service.exceptions.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * Wrapper for a third party service Fake Store API and should return FakeStoreProductDto instead of GenericProductDto
 *
 * */

@Service("fakeStoreThirdParty")
public class FakeStoreThirdParty implements InitializingBean {
    private final RestTemplateBuilder restTemplateBuilder;

    @Value("${fakeStore.api.url}")
    private String fakeStoreUrl;

    @Value("${fakeStore.api.paths.products}")
    private String fakeStoreApiProductsPath;
    private String specificProductPath;
    private String productRequestsBaseUrl;

    @Autowired
    public FakeStoreThirdParty(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreProductDto getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(specificProductPath, FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
        if(fakeStoreProductDto == null){
            throw new NotFoundException("Product not found");
        }
        return fakeStoreProductDto;
    }

    private FakeStoreProductRequestDto genericProductToFakeStoreRequestDto(GenericProductDto genericProductDto){
        return FakeStoreProductRequestDto.builder()
                .description(genericProductDto.getDescription())
                .title(genericProductDto.getTitle())
                .image(genericProductDto.getImage())
                .price(genericProductDto.getPrice().getPrice())
                .category(genericProductDto.getCategory().getName())
                .build();
    }

    public FakeStoreProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.postForEntity(productRequestsBaseUrl, genericProductToFakeStoreRequestDto(genericProductDto), FakeStoreProductDto.class);
        return responseEntity.getBody();
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(productRequestsBaseUrl,FakeStoreProductDto[].class);
        return new ArrayList<>(Arrays.asList(Objects.requireNonNull(responseEntity.getBody())));
    }

    public FakeStoreProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto>  responseEntity = restTemplate.execute(specificProductPath, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        assert responseEntity != null;
        return responseEntity.getBody();
    }

    public FakeStoreProductDto updateProductById(GenericProductDto genericProductDto, Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(genericProductToFakeStoreRequestDto(genericProductDto),FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto>  responseEntity = restTemplate.execute(specificProductPath, HttpMethod.PUT, requestCallback, responseExtractor, id);
        assert responseEntity != null;
        return responseEntity.getBody();
    }
    @Override
    @SneakyThrows
    public void afterPropertiesSet() {
        this.specificProductPath = fakeStoreUrl + fakeStoreApiProductsPath + "/{id}";
        this.productRequestsBaseUrl = fakeStoreUrl + fakeStoreApiProductsPath;
    }
}
