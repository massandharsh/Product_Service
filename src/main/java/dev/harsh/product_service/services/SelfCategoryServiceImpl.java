package dev.harsh.product_service.services;

import dev.harsh.product_service.dtos.CategoryDto;
import dev.harsh.product_service.dtos.ProductDto;
import dev.harsh.product_service.exceptions.InvalidCategoryException;
import dev.harsh.product_service.models.Category;
import dev.harsh.product_service.models.Product;
import dev.harsh.product_service.repositories.CategoryRepository;
import dev.harsh.product_service.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("selfCategoryService")
@Primary
@AllArgsConstructor
public class SelfCategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    @Override
    public CategoryDto getCategory(String uuid) {
        Optional<Category> optionalCategory = categoryRepository.findById(UUID.fromString(uuid));
        if(optionalCategory.isEmpty()){
            throw new InvalidCategoryException("Invalid category id : " + uuid);
        }
        Category category = optionalCategory.get();
        var productDtoList = category.getProducts().stream().map((product -> {
            ProductDto productDto = new ProductDto();
            return productDto.setImage(product.getImage())
                    .setPrice(product.getPrice())
                    .setTitle(product.getTitle())
                    .setDescription(product.getDescription());
        })).toList();
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName())
                .setProductsDto(productDtoList);

        return categoryDto;
    }

    @Override
    public List<String> getProductTitles(List<String> uuids){
        var listUUids = uuids.stream().map(UUID::fromString)
                .toList();
        List<Category> categories = categoryRepository.findAllById(listUUids);
        return categories.stream().map(Category::getProducts)
                .flatMap(Collection::stream)
                .map(Product::getTitle)
                .toList();

    }

    @Override
    public List<String> getProductTitlesSelf(List<String> uuids) {
        var listUUids = uuids.stream().map(UUID::fromString)
                .toList();
        List<Category> categories = categoryRepository.findAllById(listUUids);
        List<Product> products = productRepository.findAllByCategoryIn(categories);
        return products.stream()
                .peek(product -> System.out.println(product.getPrice()))
                .map(Product::getTitle)
                .toList();
    }


}
