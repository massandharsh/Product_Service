package dev.harsh.product_service;

import dev.harsh.product_service.models.Category;
import dev.harsh.product_service.models.Product;
import dev.harsh.product_service.repositories.CategoryRepository;
import dev.harsh.product_service.repositories.ElasticProductRepository;
import dev.harsh.product_service.repositories.ProductRepository;
import jakarta.persistence.Column;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@EnableElasticsearchRepositories(includeFilters = @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE,value = ElasticProductRepository.class))
@EnableJpaRepositories(excludeFilters =
@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ElasticProductRepository.class))
@SpringBootApplication(exclude = {ElasticsearchDataAutoConfiguration.class})
public class ProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}
