package dev.harsh.product_service.services;

import dev.harsh.product_service.dtos.GenericProductDto;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(String id);
    GenericProductDto createProduct(GenericProductDto genericProductDto);

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProductById(String id);

    GenericProductDto updateProductById(GenericProductDto genericProductDto,String id);

}