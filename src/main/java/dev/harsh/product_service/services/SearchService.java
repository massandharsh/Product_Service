package dev.harsh.product_service.services;

import dev.harsh.product_service.dtos.GenericProductDto;
import dev.harsh.product_service.dtos.SortHelperDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface SearchService {
    Page<GenericProductDto> searchProduct(String query, Pageable pageable);
}
