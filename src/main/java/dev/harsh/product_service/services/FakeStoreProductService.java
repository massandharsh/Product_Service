package dev.harsh.product_service.services;

import dev.harsh.product_service.models.Category;
import dev.harsh.product_service.models.Price;
import dev.harsh.product_service.thirdpartyclient.fakestore.FakeStoreProductDto;
import dev.harsh.product_service.dtos.GenericProductDto;
import dev.harsh.product_service.exceptions.NotFoundException;
import dev.harsh.product_service.thirdpartyclient.fakestore.FakeStoreThirdParty;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("fakeStoreProductService")
@Getter
public class FakeStoreProductService implements ProductService{

    private final FakeStoreThirdParty fakeStoreThirdParty;

    @Autowired
    public FakeStoreProductService(FakeStoreThirdParty fakeStoreThirdParty){
        this.fakeStoreThirdParty = fakeStoreThirdParty;
    }
    private GenericProductDto fakeStoreToGenericProduct(FakeStoreProductDto fakeStoreProductDto){
        return GenericProductDto.builder()
                .title(fakeStoreProductDto.getTitle())
                .image(fakeStoreProductDto.getImage())
                .category(new Category(fakeStoreProductDto.getCategory()))
                .price(new Price("INR",fakeStoreProductDto.getPrice()))
                .id(fakeStoreProductDto.getId().toString())
                .description(fakeStoreProductDto.getDescription())
                .build();
    }

    private Long getId(String id){
        return Long.parseLong(id);
    }

    @Override
    public GenericProductDto getProductById(String id) {
        long parsedId = this.getId(id);
        return fakeStoreToGenericProduct(this.getFakeStoreThirdParty().getProductById(parsedId));
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return fakeStoreToGenericProduct(fakeStoreThirdParty.createProduct(genericProductDto));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return fakeStoreThirdParty.getAllProducts().stream().map(this::fakeStoreToGenericProduct).toList();
    }

    @Override
    public GenericProductDto deleteProductById(String id) {
        long parsedId = this.getId(id);
        return fakeStoreToGenericProduct(fakeStoreThirdParty.deleteProductById(parsedId));
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto genericProductDto, String id) {
        long parsedId = this.getId(id);
        return fakeStoreToGenericProduct(fakeStoreThirdParty.updateProductById(genericProductDto,parsedId));
    }
}
