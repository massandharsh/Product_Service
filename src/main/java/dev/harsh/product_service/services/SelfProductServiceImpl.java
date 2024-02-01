package dev.harsh.product_service.services;

import dev.harsh.product_service.dtos.GenericProductDto;
import dev.harsh.product_service.exceptions.NotFoundException;
import dev.harsh.product_service.models.Product;
import dev.harsh.product_service.repositories.CustomQueries;
import dev.harsh.product_service.repositories.ElasticProductRepository;
import dev.harsh.product_service.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("selfProductService")
@Slf4j
@Primary
@AllArgsConstructor
public class SelfProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ElasticProductRepository productElasticRepository;
    private final RedisTemplate<String,Object> redisTemplate;

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
        //First search at cache
        //KEY 1_29NOV_8AM can be made as a key based on time
        GenericProductDto genericProductDto = (GenericProductDto) redisTemplate.opsForHash().get("PRODUCTS", id+"SelfStore");
        if(genericProductDto != null){
            //It is found in the cache
            return genericProductDto;
        }
        //Search at elastic search
        Optional<Product> elasticProduct = productRepository.findById(getId(id));
        if(elasticProduct.isPresent()){
            //Generic product dto
            //Add to cache
            GenericProductDto result =  GenericProductDto.productToGenericProductDto(elasticProduct.get());
            redisTemplate.opsForHash().put("PRODUCTS",id + "SelfStore",result);
            return result;
        }

        //Search from database
        Optional<Product> optionalProduct = productRepository.findById(getId(id));
        if(optionalProduct.isEmpty()){
            //Throw exception
            log.error("Product with {} not found",id);
            throw new NotFoundException("Product with" + id + " not found");
        }
        GenericProductDto result =  productToGenericProductDto(optionalProduct.get());
        redisTemplate.opsForHash().put("PRODUCTS",id + "SelfStore",result);
        return result;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        Product product = genericProductDto.genericProductToProduct();
        product = productRepository.save(product);
        //Save this product to elastic search db as well
        productElasticRepository.save(product);
        log.info("Product {} has been saved to the repo",product);
        return productToGenericProductDto(product);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        long total = productRepository.count();
        return products.stream().map(this::productToGenericProductDto).map(genericProductDto -> genericProductDto.setTotal(total)).collect(Collectors.toList());
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
