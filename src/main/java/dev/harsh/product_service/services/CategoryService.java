package dev.harsh.product_service.services;

import dev.harsh.product_service.dtos.CategoryDto;
import dev.harsh.product_service.models.Category;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CategoryService {
    CategoryDto getCategory(String uuid);

    List<String> getProductTitles(List<String> uuids);

    List<String> getProductTitlesSelf(List<String> categories);
}
