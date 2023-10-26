package dev.harsh.product_service.services;

import dev.harsh.product_service.dtos.GenericProductDto;
import dev.harsh.product_service.exceptions.NotFoundException;
import dev.harsh.product_service.models.Product;
import dev.harsh.product_service.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository("selfProductService")
@Slf4j
public class SelfProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    @Autowired
    public SelfProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    private UUID getId(String id){
        return UUID.fromString(id);
    }

    private GenericProductDto productToGenericProductDto(Product product){
        return GenericProductDto.builder()
                .title(product.getTitle())
                .price(product.getPrice())
                .id(product.getUuid().toString())
                .description(product.getDescription())
                .image(product.getImage())
                .category(product.getCategory())
                .build();
    }

    @Override
    public GenericProductDto getProductById(String id) {
        Optional<Product> optionalProduct = productRepository.findById(getId(id));
        if(optionalProduct.isEmpty()){
            //Throw exception
            log.error("Product with {} not found",id);
            throw new NotFoundException("Product with" + id + " not found");
        }
        return productToGenericProductDto(optionalProduct.get());
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        Product product = genericProductDto.genericProductToProduct();
        product = productRepository.save(product);
        log.info("Product {} has been saved to the repo",product);
        return productToGenericProductDto(product);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::productToGenericProductDto).collect(Collectors.toList());
    }

    @Override
    public GenericProductDto deleteProductById(String id) {
        GenericProductDto genericProductDto = this.getProductById(id);
        productRepository.deleteById(getId(id));
        return genericProductDto;
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto genericProductDto, String id) {
        Product product = genericProductDto.genericProductToProduct();
        product.setUuid(getId(id));
        product = productRepository.save(product);
        return productToGenericProductDto(product);
    }
}
