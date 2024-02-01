package dev.harsh.product_service.services;

import dev.harsh.product_service.dtos.GenericProductDto;
import dev.harsh.product_service.models.Product;
import dev.harsh.product_service.repositories.CustomQueries;
import dev.harsh.product_service.repositories.ElasticProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SelfSearchService implements SearchService{

    private final ElasticProductRepository elasticProductRepository;
    @Override
    public Page<GenericProductDto> searchProduct(String query, Pageable pageable) {
        Page<Product> products = elasticProductRepository.searchAllByTitle(query,pageable);
        List<GenericProductDto> genericProductDtoList =  products.stream().map(GenericProductDto::productToGenericProductDto)
                .toList();
        return new PageImpl<>(genericProductDtoList,products.getPageable(),products.getTotalElements());
    }
}
