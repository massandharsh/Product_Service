package dev.harsh.product_service;

import dev.harsh.product_service.models.Category;
import dev.harsh.product_service.models.Price;
import dev.harsh.product_service.models.Product;
import dev.harsh.product_service.repositories.CategoryRepository;
import dev.harsh.product_service.repositories.PriceRepository;
import dev.harsh.product_service.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class Runner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    private final PriceRepository priceRepository;

    @Autowired
    public Runner(CategoryRepository categoryRepository, ProductRepository productRepository, PriceRepository priceRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.priceRepository = priceRepository;
    }

    @Transactional
    public void run(String... args) throws Exception {
        Category category = Category.builder()
                .name("Mobile")
                .build();
        Price price = Price.builder()
                .price(10000)
                .currency("INR")
                .build();
        Product product = Product.builder()
                .title("Iphone 14")
                .description("This is an iphone")
                .image("www.xyz.com")
                .price(price)
                .category(category)
                .build();
        //As priceRepo expects the id as UUID type therefore we have to convert it to same
        productRepository.deleteById(UUID.fromString("35485866-28da-4598-b8d5-d9fa8eea1c8d"));
        System.out.println(productRepository.findByCustomTitle("Iphone 14"));
        log.info("Custom query products \n {} \n", productRepository.findByCustomTitle("Iphone 14"));
        System.out.println(productRepository.findByCustomTitleNonNative("Iphone 14"));
        product = productRepository.save(product);

        System.out.println(getProductsLazy(product.getCategory().getUuid()));
    }

    @Transactional
    public List<Product> getProductsLazy(UUID uuid){
        Optional<Category> category = categoryRepository.findById(UUID.fromString("3ed34ea3-e311-4a5e-9a25-82835cf08307"));
        if(category.isEmpty()){
            throw new RuntimeException("Category does not exist");
        }
        return category.get().getProducts();
    }
}
